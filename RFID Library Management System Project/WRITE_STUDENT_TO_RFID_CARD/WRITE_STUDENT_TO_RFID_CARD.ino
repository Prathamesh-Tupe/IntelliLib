#include <SPI.h>
#include <MFRC522.h>

#define RST_PIN         D2  // Configurable, see typical pin layout above
#define SS_PIN          D4  // Configurable, see typical pin layout above

MFRC522 mfrc522(SS_PIN, RST_PIN);  // Create MFRC522 instance

void setup() {
  Serial.begin(9600);         // Initialize serial communications with the PC
  while (!Serial);            // Do nothing if no serial port is opened (added for Arduinos based on ATMEGA32U4)
  SPI.begin();                // Init SPI bus
  mfrc522.PCD_Init();         // Init MFRC522
  Serial.println(F("Place your card to write data..."));
}

void loop() {
  // Reset the loop if no new card present on the sensor/reader
  if (!mfrc522.PICC_IsNewCardPresent()) {
    return;
  }

  // Select one of the cards
  if (!mfrc522.PICC_ReadCardSerial()) {
    return;
  }

  Serial.println(F("Card detected"));

  // Authenticate and write data
  if (authenticateAndWriteData(2, (byte *)"student")) { // Writing to Sector 0, Block 2
    Serial.println(F("Data written successfully!"));
  } else {
    Serial.println(F("Failed to write data."));
  }

  mfrc522.PICC_HaltA();        // Halt PICC
  mfrc522.PCD_StopCrypto1();   // Stop encryption on PCD
}

bool authenticateAndWriteData(byte block, byte *data) {
  // Authentication key (default key for new MIFARE cards)
  MFRC522::MIFARE_Key key;
  for (byte i = 0; i < 6; i++) {
    key.keyByte[i] = 0xFF; // Default key
  }

  // Authenticate
  if (mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid)) != MFRC522::STATUS_OK) {
    Serial.println(F("Authentication failed"));
    return false;
  }

  // Prepare data to write (16 bytes max)
  byte buffer[16] = {0}; // Clear buffer
  for (byte i = 0; i < 16 && data[i] != '\0'; i++) {
    buffer[i] = data[i];
  }

  // Write data
  MFRC522::StatusCode status = mfrc522.MIFARE_Write(block, buffer, 16);
  if (status != MFRC522::STATUS_OK) {
    Serial.print(F("Write failed: "));
    Serial.println(mfrc522.GetStatusCodeName(status));
    return false;
  }

  return true;
}
