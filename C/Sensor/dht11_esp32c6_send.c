// DHT11 Temperature-Only Sensor -> IntegrationService
// Board: ESP32-C6
// Reads temperature from a DHT11 and POSTs it as JSON to your
// IntegrationService's POST /api/measurements/test endpoint.

#include <WiFi.h>
#include <HTTPClient.h>
#include <time.h>

#define DHT_PIN 4          // GPIO connected to DHT11 data line - check your board's pinout
#define LED_PIN 8          // Status LED pin - many ESP32-C6 boards use GPIO8 for the onboard LED
#define TIMEOUT 10000UL    // Microsecond timeout for pulse reads

// ---- Edit these for your setup ----
const char* WIFI_SSID     = "YOUR_WIFI_NAME";
const char* WIFI_PASSWORD = "YOUR_WIFI_PASSWORD";

// Use your PC's LAN IP (not "localhost") and the HTTP port IS is listening on.
// e.g. "http://192.168.1.50:5080/api/measurements/test"
const char* IS_URL = "http://192.168.1.50:5080/api/measurements/test";

const char* DEVICE_ID = "esp32-c6-001";
const char* LOCATION  = "Room 1";
// ------------------------------------

uint8_t dhtData[5];

unsigned long waitForState(int pin, int state) {
  unsigned long start = micros();
  while (digitalRead(pin) != state) {
    if (micros() - start > TIMEOUT) return 0;
  }
  return micros() - start;
}

int readDHT11(float *temperatureC) {
  uint8_t bitIdx = 7, byteIdx = 0;
  for (int i = 0; i < 5; i++) dhtData[i] = 0;

  pinMode(DHT_PIN, OUTPUT);
  digitalWrite(DHT_PIN, LOW);
  delay(18);
  digitalWrite(DHT_PIN, HIGH);
  delayMicroseconds(40);
  pinMode(DHT_PIN, INPUT);

  if (waitForState(DHT_PIN, LOW) == 0)  return -1;
  if (waitForState(DHT_PIN, HIGH) == 0) return -1;
  if (waitForState(DHT_PIN, LOW) == 0)  return -1;

  for (int i = 0; i < 40; i++) {
    if (waitForState(DHT_PIN, HIGH) == 0) return -1;
    unsigned long highTime = waitForState(DHT_PIN, LOW);
    if (highTime == 0) return -1;

    if (highTime > 40) {
      dhtData[byteIdx] |= (1 << bitIdx);
    }

    if (bitIdx == 0) {
      bitIdx = 7;
      byteIdx++;
    } else {
      bitIdx--;
    }
  }

  uint8_t checksum = dhtData[0] + dhtData[1] + dhtData[2] + dhtData[3];
  if (checksum != dhtData[4]) return -2;

  // Humidity (dhtData[0..1]) intentionally ignored - temperature only.
  *temperatureC = (float)dhtData[2];
  return 0;
}

void connectWiFi() {
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println();
  Serial.print("Connected, IP: ");
  Serial.println(WiFi.localIP());
}

// Returns current UTC time as an ISO 8601 string, e.g. "2026-09-22T14:03:00Z"
// Requires NTP sync to have completed in setup().
String getIsoTimestamp() {
  time_t now;
  time(&now);
  struct tm timeinfo;
  gmtime_r(&now, &timeinfo);
  char buf[25];
  strftime(buf, sizeof(buf), "%Y-%m-%dT%H:%M:%SZ", &timeinfo);
  return String(buf);
}

void sendMeasurement(float tempC) {
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("WiFi not connected, skipping send");
    return;
  }

  HTTPClient http;
  http.begin(IS_URL);
  http.addHeader("Content-Type", "application/json");

  // Field names match MeasurementRequest (measurementType, measuredAtUtc,
  // deviceId, location, value, unit) as camelCase JSON.
  String payload = "{";
  payload += "\"measurementType\":\"Temperature\",";
  payload += "\"measuredAtUtc\":\"" + getIsoTimestamp() + "\",";
  payload += "\"deviceId\":\"" + String(DEVICE_ID) + "\",";
  payload += "\"location\":\"" + String(LOCATION) + "\",";
  payload += "\"value\":" + String(tempC, 1) + ",";
  payload += "\"unit\":\"C\"";
  payload += "}";

  int httpCode = http.POST(payload);

  if (httpCode > 0) {
    Serial.print("IS responded with status: ");
    Serial.println(httpCode);
    Serial.println(http.getString());
  } else {
    Serial.print("POST failed: ");
    Serial.println(http.errorToString(httpCode));
  }

  http.end();
}

void setup() {
  Serial.begin(115200);
  pinMode(DHT_PIN, INPUT);
  pinMode(LED_PIN, OUTPUT);

  connectWiFi();

  // Sync real UTC time over NTP so MeasuredAtUtc is accurate
  configTime(0, 0, "pool.ntp.org", "time.nist.gov");
  Serial.print("Waiting for NTP time sync");
  time_t now = time(nullptr);
  while (now < 100000) {
    delay(500);
    Serial.print(".");
    now = time(nullptr);
  }
  Serial.println();
  Serial.println("Time synced.");
}

void loop() {
  float tempC;
  int result = readDHT11(&tempC);

  if (result == 0) {
    Serial.print("Temperature: ");
    Serial.print(tempC);
    Serial.println(" C");
    sendMeasurement(tempC);
  } else if (result == -1) {
    Serial.println("Error: sensor timeout / not responding - check wiring");
  } else if (result == -2) {
    Serial.println("Error: checksum mismatch - reading discarded");
  }

  digitalWrite(LED_PIN, HIGH);
  delay(100);
  digitalWrite(LED_PIN, LOW);

  delay(1900); // total ~2s between reads
}
