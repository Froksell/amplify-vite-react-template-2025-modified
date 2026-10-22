namespace OpcUaClient.Api.OpcUa;

/// <summary>Snapshot of one OPC UA variable and its latest value.</summary>
public record NodeInfo(
    string Name,
    string NodeId,
    object? Value,
    string Status,
    DateTime? Timestamp);
