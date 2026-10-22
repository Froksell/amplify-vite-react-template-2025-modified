using System.Collections.Concurrent;
using Opc.Ua;
using Opc.Ua.Client;
using Opc.Ua.Configuration;

namespace OpcUaClient.Api.OpcUa.Services;

/// <summary>
/// Connects to the OpcUaGateway server (SecurityPolicy None, anonymous), browses the
/// configured folder, subscribes to every variable in it and keeps the latest values.
/// Reconnects automatically if the connection is lost.
/// </summary>
public class OpcUaClientService(OpcUaOptions options, ILogger<OpcUaClientService> logger) : BackgroundService
{
    private readonly OpcUaOptions _options = options;
    private readonly ConcurrentDictionary<string, NodeInfo> _nodes = new(StringComparer.OrdinalIgnoreCase);
    private ApplicationConfiguration? _config;

    public bool IsConnected { get; private set; }

    public IReadOnlyCollection<NodeInfo> GetNodes() => _nodes.Values.OrderBy(n => n.Name).ToList();

    public NodeInfo? GetNode(string name) => _nodes.TryGetValue(name, out var node) ? node : null;

    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        while (!stoppingToken.IsCancellationRequested)
        {
            try
            {
                await ConnectAndRunAsync(stoppingToken);
            }
            catch (OperationCanceledException) when (stoppingToken.IsCancellationRequested)
            {
                break;
            }
            catch (Exception ex)
            {
                logger.LogWarning(ex, "OPC UA connection failed");
            }

            IsConnected = false;

            try { await Task.Delay(_options.ReconnectDelayMs, stoppingToken); }
            catch (OperationCanceledException) { break; }
        }
    }

    private async Task ConnectAndRunAsync(CancellationToken ct)
    {
        _config ??= await CreateConfigurationAsync();

        var endpointDescription = CoreClientUtils.SelectEndpoint(_config, _options.ServerUrl, useSecurity: false);
        var endpoint = new ConfiguredEndpoint(null, endpointDescription, EndpointConfiguration.Create(_config));

        using ISession session = await Session.Create(
            _config,
            endpoint,
            updateBeforeConnect: false,
            sessionName: "OpcUaClimateClient",
            sessionTimeout: (uint)_options.SessionTimeoutMs,
            identity: new UserIdentity(new AnonymousIdentityToken()),
            preferredLocales: null,
            ct: ct);

        logger.LogInformation("Connected to {Endpoint}", session.Endpoint.EndpointUrl);

        // Signals that the connection is gone so we can reconnect.
        var connectionLost = new TaskCompletionSource();
        session.KeepAlive += (_, e) =>
        {
            if (ServiceResult.IsBad(e.Status))
                connectionLost.TrySetResult();
        };

        var variables = FindVariables(session);
        _nodes.Clear();
        foreach (var (name, id) in variables)
            _nodes[name] = new NodeInfo(name, id.ToString(), null, "Waiting for data", null);

        var subscription = new Subscription(session.DefaultSubscription)
        {
            DisplayName = _options.FolderName,
            PublishingInterval = _options.PublishingIntervalMs,
            PublishingEnabled = true
        };

        foreach (var (name, id) in variables)
        {
            var item = new MonitoredItem(subscription.DefaultItem)
            {
                DisplayName = name,
                StartNodeId = id,
                AttributeId = Attributes.Value,
                SamplingInterval = _options.PublishingIntervalMs,
                QueueSize = 1,
                DiscardOldest = true
            };
            item.Notification += OnNotification;
            subscription.AddItem(item);
        }

        session.AddSubscription(subscription);
        await subscription.CreateAsync(ct);

        IsConnected = true;
        logger.LogInformation("Subscribed to {Count} nodes under {Folder}", variables.Count, _options.FolderName);

        try
        {
            await connectionLost.Task.WaitAsync(ct);
            logger.LogWarning("OPC UA connection lost, reconnecting...");
        }
        finally
        {
            IsConnected = false;
            try { await session.CloseAsync(CancellationToken.None); } catch { /* connection already gone */ }
        }
    }

    private void OnNotification(MonitoredItem item, MonitoredItemNotificationEventArgs e)
    {
        foreach (var value in item.DequeueValues())
        {
            if (!_nodes.TryGetValue(item.DisplayName, out var current))
                continue;

            _nodes[item.DisplayName] = current with
            {
                Value = value.Value,
                Status = value.StatusCode.ToString(),
                Timestamp = value.SourceTimestamp == DateTime.MinValue ? null : value.SourceTimestamp
            };
        }
    }

    private List<(string Name, NodeId Id)> FindVariables(ISession session)
    {
        ushort ns = (ushort)session.NamespaceUris.GetIndex(_options.NamespaceUri);
        if (ns == ushort.MaxValue)
            throw new InvalidOperationException($"Namespace '{_options.NamespaceUri}' not found on the server.");

        var folder = BrowseChildren(session, ObjectIds.ObjectsFolder)
            .FirstOrDefault(c => c.Name == _options.FolderName && c.Id.NamespaceIndex == ns);

        if (folder.Id is null)
            throw new InvalidOperationException($"Folder '{_options.FolderName}' not found under Objects.");

        return BrowseChildren(session, folder.Id);
    }

    private static List<(string Name, NodeId Id)> BrowseChildren(ISession session, NodeId parent)
    {
        session.Browse(
            null, null, parent, 0u,
            BrowseDirection.Forward, ReferenceTypeIds.HierarchicalReferences,
            true, (uint)NodeClass.Variable | (uint)NodeClass.Object,
            out _, out ReferenceDescriptionCollection refs);

        return refs
            .Select(r => (r.BrowseName.Name, ExpandedNodeId.ToNodeId(r.NodeId, session.NamespaceUris)))
            .ToList();
    }

    private static async Task<ApplicationConfiguration> CreateConfigurationAsync()
    {
        var pki = Path.Combine(AppContext.BaseDirectory, "pki");

        var config = new ApplicationConfiguration
        {
            ApplicationName = "OpcUaClimateClient",
            ApplicationUri = "urn:avanoria:OpcUaClimateClient",
            ApplicationType = ApplicationType.Client,
            SecurityConfiguration = new SecurityConfiguration
            {
                ApplicationCertificate = new CertificateIdentifier
                {
                    StoreType = "Directory",
                    StorePath = Path.Combine(pki, "own"),
                    SubjectName = "CN=OpcUaClimateClient"
                },
                TrustedPeerCertificates = new CertificateTrustList
                {
                    StoreType = "Directory",
                    StorePath = Path.Combine(pki, "trusted")
                },
                TrustedIssuerCertificates = new CertificateTrustList
                {
                    StoreType = "Directory",
                    StorePath = Path.Combine(pki, "issuer")
                },
                RejectedCertificateStore = new CertificateTrustList
                {
                    StoreType = "Directory",
                    StorePath = Path.Combine(pki, "rejected")
                },
                AutoAcceptUntrustedCertificates = true,
                AddAppCertToTrustedStore = true
            },
            TransportConfigurations = new TransportConfigurationCollection(),
            TransportQuotas = new TransportQuotas { OperationTimeout = 15000 },
            ClientConfiguration = new ClientConfiguration { DefaultSessionTimeout = 60000 }
        };

        await config.ValidateAsync(ApplicationType.Client);
        return config;
    }
}
