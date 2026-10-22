using OpcUaClient.Api.Endpoints;
using OpcUaClient.Api.OpcUa;
using OpcUaClient.Api.OpcUa.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddCors();

var options = builder.Configuration
    .GetRequiredSection(OpcUaOptions.SectionName)
    .Get<OpcUaOptions>()
    ?? throw new InvalidOperationException("OPC UA-konfiguration saknas.");

if (string.IsNullOrWhiteSpace(options.ServerUrl))
    throw new InvalidOperationException("OpcUa:ServerUrl måste anges.");

builder.Services.AddSingleton(options);
builder.Services.AddProblemDetails();

builder.Services.AddSingleton<OpcUaClientService>();
builder.Services.AddHostedService(sp => sp.GetRequiredService<OpcUaClientService>());

await using var app = builder.Build();

app.UseExceptionHandler();

app.UseCors(x => x.AllowAnyHeader().AllowAnyOrigin().AllowAnyMethod());

app.MapOpcUaEndpoints();

await app.RunAsync();
