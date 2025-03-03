#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <SPI.h>
#include <MFRC522.h>

#define SERVER_IP "192.168.128.49"  // Replace with your server IP

#define STASSID "Prarthana"         // Replace with your Wi-Fi SSID
#define STAPSK "mh129029#"         // Replace with your Wi-Fi password

#define SS_PIN D4                  // Slave select pin for RFID
#define RST_PIN D2                 // Reset pin for RFID

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

void setup() {
  Serial.begin(115200);
  SPI.begin();                     // Init SPI bus
  mfrc522.PCD_Init();              // Init MFRC522 RFID reader

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
    }
    uid.toUpperCase();
    Serial.print("Card UID: ");
    Serial.println(uid);

    // Authenticate and read the card's sector 0, block 2
    if (!readCardTextAndSend(uid)) {
      Serial.println("Error reading card text or sending data.");
    }

    // Halt PICC
    mfrc522.PICC_HaltA();
  }

  // Reset the RFID reader to continuously scan for new cards
  mfrc522.PCD_StopCrypto1();
}

bool readCardTextAndSend(String uid) {
  MFRC522::MIFARE_Key key;
  for (byte i = 0; i < 6; i++) key.keyByte[i] = 0xFF;  // Default key

  byte sector = 0;
  byte block = 2;
  byte buffer[18];
  byte size = sizeof(buffer);

  // Authenticate and read the block
  if (mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid)) == MFRC522::STATUS_OK) {
    if (mfrc522.MIFARE_Read(block, buffer, &size) == MFRC522::STATUS_OK) {
      // Convert the buffer to a readable string
      String cardText = "";
      for (byte i = 0; i < 16; i++) {
        if (buffer[i] != 0) cardText += (char)buffer[i];
      }
      cardText.trim();
      Serial.print("Card Text: ");
      Serial.println(cardText);

      // Determine URL and JSON payload based on card text
      String url, payload;
      if (cardText == "student") {
        url = "/library/librarian/borrow/getstudent";
        payload = "{\"student_uid\":\"" + uid + "\"}";
      } else if (cardText == "book") {
        url = "/library/librarian/borrow/getbook";
        payload = "{\"bookUid\":\"" + uid + "\"}";
      } else {
        Serial.println("Unknown card type.");
        return false;
      }

      // Send data to the server
      return sendToServer(url, payload);
    }
  }
  return false;  // Authentication or reading failed
}

bool sendToServer(String url, String payload) {
  if (WiFi.status() == WL_CONNECTED) {
    WiFiClient client;
    HTTPClient http;

    String fullUrl = "http://" + String(SERVER_IP) + ":8080" + url;
    Serial.println("[HTTP] Begin...");
    Serial.println("URL: " + fullUrl);
    http.begin(client, fullUrl);
    http.addHeader("Content-Type", "application/json");

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
        http.end();
        return true;
      }
    } else {
      Serial.printf("[HTTP] POST... failed, error: %s\n", http.errorToString(httpCode).c_str());
    }

    http.end();  // Close connection
  } else {
    Serial.println("[HTTP] Not connected to WiFi");
  }
  return false;
}
