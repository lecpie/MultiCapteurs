#include <Arduino.h>
#include <SD.h>

#include <SoftwareSerial.h>
#include <BufferedGPS.h>
#include <DHT.h>
#include <Wire.h>
#include <Digital_Light_TSL2561.h>
#include <HP20x_dev.h>

#define SERIALOUTPUT 1
#define SDOUTPUT     1


#if SDOUTPUT

/* SD card configuration */

#define SDCHIPSELECT 4
#define SDPIN        10

#define OUTPUTFILE "measures.txt"

File datafile;

#endif

/* Data buffering output on output */

const char separator = ',';
const char endline   = '\n';

#define DATABUFSIZE 128

uint8_t databuf[DATABUFSIZE];
uint8_t idatabuf = 0;

void put_data(const char * buf, uint8_t sz)
{
    // While there is still something to write
    while (sz > 0) {
        uint8_t writable = DATABUFSIZE - idatabuf;
        uint8_t towrite  = (sz > writable) ? writable : sz;

        memcpy(databuf + idatabuf, buf, towrite);
        idatabuf += towrite;
        buf      += towrite;
        sz       -= towrite;

        // If the data buffer is full, flush it
        if (idatabuf >= DATABUFSIZE) {
            #if SERIALOUTPUT
            Serial  .write(databuf, DATABUFSIZE);
            #endif

            #if SDOUTPUT
            datafile.write(databuf, DATABUFSIZE);
            #endif

            idatabuf = 0;
        }
    }
}

char conversionbuffer[64];

void put_char (char val)
{
    put_data(&val, sizeof(char));
}

void put_int (int val)
{
    put_data(conversionbuffer, sprintf(conversionbuffer, "%d", val));
}

void put_float (float val)
{
    int i = 0;

    dtostrf(val, 0, 6, conversionbuffer);
    while (conversionbuffer[++i] != '\0');

    put_data(conversionbuffer, i);
}

void put_separator() { put_char(separator); }
void put_endl     () { put_char(endline);   }

/* GPS */

#define RX              2
#define TX              1
#define GPSBAUD      4800
#define REFRESHDELAY   50

BufferedGPS gps(RX, TX, REFRESHDELAY);

float longitude;
float latitude;

/* Light sensor */

int light;

/* DHT temperature / Humidity sensor */

#define DHTPIN     A0
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

int temperature;
int humidity;


// HP20x Barometer

float hp_temperature;
float hp_pressure;
float hp_altitude;


void setup()
{
    #if SDOUTPUT
    // SD Card setup
    pinMode(SDPIN, OUTPUT);
    SD.begin(SDCHIPSELECT);
    #endif

    #if SERIALOUTPUT
    // Serial setup
    Serial.begin(115200);
    #endif

    // GPS setup
    gps.begin(GPSBAUD);

    // DHT setup
    dht.begin();

    // Light sensor setup
    Wire.begin();
    TSL2561.init();

    // Baro setup
    delay(150);
    /* Reset HP20x_dev */
    HP20x.begin();
    delay(100);

    // Init CSV
    if (not SD.exists(OUTPUTFILE))
    {
        #if SDOUTPUT
        // Write csv header
        datafile = SD.open(OUTPUTFILE, FILE_WRITE);
        #endif

        put_data("latitude",     8);
        put_separator();

        put_data("longitude",    9);
        put_separator();

        put_data("temperature", 11);
        put_separator();

        put_data("humidity",     8);
        put_separator();

        put_data("light",        5);
        put_separator();

        put_data("hp_temperature", 14);
        put_separator();

        put_data("hp_pressure",    11);
        put_separator();

        put_data("hp_altitude",    11);
        put_separator();

        put_endl();

        #if SDOUTPUT
        datafile.close();
        #endif
    }
}

void loop()
{
    // Update all measures

    latitude    = gps.getLatitude();
    longitude   = gps.getLongitude();

    light       = TSL2561.readVisibleLux();

    temperature = dht.readTemperature();
    humidity    = dht.readHumidity();

    hp_temperature = HP20x.ReadTemperature() / 100.0;
    hp_pressure    = HP20x.ReadPressure   () / 100.0;
    hp_altitude    = HP20x.ReadAltitude   () / 100.0;

    #if SDOUTPUT
    // Write them on output
    datafile = SD.open(OUTPUTFILE, FILE_WRITE);
    #endif

    put_float(latitude);
    put_separator();

    put_float(longitude);
    put_separator();

    put_int(temperature);
    put_separator();

    put_int(humidity);
    put_separator();

    put_int(light);
    put_separator();

    put_float(hp_temperature);
    put_endl();

    put_float(hp_pressure);
    put_endl();

    put_float(hp_altitude);
    put_endl();


    put_endl();

    #if SDOUTPUT
    datafile.close();
    #endif
}
