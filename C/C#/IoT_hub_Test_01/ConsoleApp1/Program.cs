using Microsoft.Azure.Devices.Client;
using System.Net.Mime;
using System.Text.Json;

var connectionString = "HostName=Jermaine-IoT-hub.azure-devices.net;DeviceId=Client_Test_01;SharedAccessKey=YJSfnO8yQLRknleecsXhd1+pkXIOAiqrWt+i+eXwJBo=";

using var client = DeviceClient.CreateFromConnectionString(connectionString, TransportType.Mqtt);

await client.OpenAsync();

try
{
    var mesurment = new
    {
        deviceId = "test-device-01",
        temperature = 25.0,
        unit = "c",
        messuredAt = DateTime.Now
    };


    var json  = JsonSerializer.Serialize(mesurment);

    using var message = new Message(System.Text.Encoding.UTF8.GetBytes(json))
    {
        ContentType = "application/json",
        ContentEncoding = "utf-8"
    };

    await client.SendEventAsync(message);
    Console.WriteLine($"Sent message: {json}");
}
catch (Exception ex)
{
    Console.WriteLine($"Error sending message: {ex.Message}");
}
