namespace OpcUaClient.Api.OpcUa;

public class OpcUaOptions
{
    public const string SectionName = "OpcUa";

    /// <summary>Server endpoint, e.g. opc.tcp://192.168.1.10:4848</summary>
    public string ServerUrl { get; set; } = "opc.tcp://localhost:4848";

    /// <summary>Namespace URI used by the server's node manager.</summary>
    public string NamespaceUri { get; set; } = "urn:avanoria:devices";

    /// <summary>Folder (under Objects) whose variables are monitored.</summary>
    public string FolderName { get; set; } = "Climate";

    public int SessionTimeoutMs { get; set; } = 60000;
    public int PublishingIntervalMs { get; set; } = 1000;
    public int ReconnectDelayMs { get; set; } = 5000;
}
