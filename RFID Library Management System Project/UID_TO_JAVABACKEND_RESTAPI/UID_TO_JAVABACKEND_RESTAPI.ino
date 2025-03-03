#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <SPI.h>
#include <MFRC522.h>

#define SERVER_IP "192.168.128.49"  // Replace with your server IP

#define STASSID "Prarthana"         // Replace with your Wi-Fi SSID
#define STAPSK "mh129029#"      // Replace with your Wi-Fi password

#define SS_PIN D4                   // Slave select pin for RFID
#define RST_PIN D2                  // Reset pin for RFID

MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance

void setup() {
  Serial.begin(115200);
  SPI.begin();                      // Init SPI bus
  mfrc522.PCD_Init();               // Init MFRC522 RFID reader

  Serial.println();
  Serial.println("Connecting to WiFi...");
  WiFi.begin(STASSID, STAPSK);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nConnected! IP address: ");
  Serial.println(WiFi.localIP());
}

void loop() {
  // Check if a new RFID card is present
  if (mfrc522.PICC_IsNewCardPresent() && mfrc522.PICC_ReadCardSerial()) {
    // Construct the UID from the RFID card
    String uid = "";
    for (byte i = 0; i < mfrc522.uid.size; i++) {
      uid += String(mfrc522.uid.uidByte[i], HEX);
      if (i < mfrc522.uid.size - 1) uid += ":";
    }
    uid.toUpperCase();
    Serial.print("Card UID: ");
    Serial.println(uid);

    // Send the UID to the server
    sendUIDToServer(uid);

    // Halt PICC
    mfrc522.PICC_HaltA();
  }
  delay(1000);  // Check for a new card every second
}

void sendUIDToServer(String uid) {
  if (WiFi.status() == WL_CONNECTED) {
    WiFiClient client;
    HTTPClient http;

    Serial.println("[HTTP] Begin...");
    http.begin(client, "http://" SERVER_IP ":8080/rfid/student/loginstudent");  // Configure server URL and port
    http.addHeader("Content-Type", "application/json");

    // Construct JSON payload with the UID
    String payload = "{\"student_uid\":\"" + uid + "\"}";
    Serial.print("Sending payload: ");
    Serial.println(payload);

    // Send POST request
    int httpCode = http.POST(payload);

    if (httpCode > 0) {
      Serial.printf("[HTTP] POST... code: %d\n", httpCode);
      if (httpCode == HTTP_CODE_OK) {
        const String& response = http.getString();
        Serial.println("Received payload:\n<<");
        Serial.println(response);
        Serial.println(">>");
      }
    } else {
      Serial.printf("[HTTP] POST... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }

    http.end();  // Close connection
  } else {
    Serial.println("[HTTP] Not connected to WiFi");
  }
}
