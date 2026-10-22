using OpcUaClient.Api.OpcUa.Services;

namespace OpcUaClient.Api.Endpoints;

public static class OpcUaEndpoints
{
    public static IEndpointRouteBuilder MapOpcUaEndpoints(this IEndpointRouteBuilder app)
    {
        app.MapGet("/health", (OpcUaClientService opcUa) =>
            Results.Ok(new { connected = opcUa.IsConnected }));

        // All monitored nodes with their latest values
        app.MapGet("/climate", (OpcUaClientService opcUa) =>
            Results.Ok(opcUa.GetNodes()));

        // One node by name: /climate/Temperature, /climate/Humidity, /climate/Unit, /climate/DeviceId
        app.MapGet("/climate/{name}", (string name, OpcUaClientService opcUa) =>
            opcUa.GetNode(name) is { } node
                ? Results.Ok(node)
                : Results.NotFound(new { error = $"Node '{name}' not found" }));

        return app;
    }
}
