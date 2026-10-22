using Azure.Messaging.EventHubs.Consumer;

var connectionString = "Endpoint=sb://germanywestcentraldedns008.servicebus.windows.net/;SharedAccessKeyName=iothubowner;SharedAccessKey=Ed5eyzly7gTGR4PqRioiEISMh5FVidS31AIoTNv9CmQ=;EntityPath=iothub-ehub-jermaine-i-59436220-40a5c8908a";

await using var comsumer = new EventHubConsumerClient(
    EventHubConsumerClient.DefaultConsumerGroupName,
    connectionString);


try
{
   await foreach (var receivedeEvent in comsumer.ReadEventsAsync(false))
    {
        var json = receivedeEvent.Data.EventBody.ToString();
        Console.WriteLine($"Received event: {json}");
    }

}
catch (Exception ex)
{
    Console.WriteLine($"Error receiving events: {ex.Message}");
}