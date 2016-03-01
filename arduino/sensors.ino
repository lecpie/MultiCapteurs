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

// GPS distance calculation

double distance_to (double lat1, double long1, double lat2, double long2)
{
    double delta = radians(long1-long2);
    double sdlong = sin(delta);
    double cdlong = cos(delta);
    lat1 = radians(lat1);
    lat2 = radians(lat2);
    double slat1 = sin(lat1);
    double clat1 = cos(lat1);
    double slat2 = sin(lat2);
    double clat2 = cos(lat2);
    delta = (clat1 * slat2) - (slat1 * clat2 * cdlong);
    delta = sq(delta);
    delta += sq(clat2 * sdlong);
    delta = sqrt(delta);
    double denom = (slat1 * slat2) + (clat1 * clat2 * cdlong);
    delta = atan2(delta, denom);
    return delta * 6372795;
}

/* SD card configuration */

#define SDCHIPSELECT 4
#define SDPIN        10

char output[] = "measures.txt";
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

float latitude;
float longitude;

/* DHT temperature / Humidity sensor */

#define DHTPIN     A0
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

// Frequencies

long last_f1;
long last_f2;

double prev_long1 = 0.0, prev_lat1 = 0.0;
double prev_long2 = 0.0, prev_lat2 = 0.0;

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

#if SDOUTPUT
    // Init CSV file
    uint16_t ifile = 0;
    do {
        sprintf(output, "LOG%d.CSV", ifile++);
    } while (SD.exists(output));

    // Write csv header
    datafile = SD.open(output, FILE_WRITE);
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

    // init frequency

    last_f1 = last_f2 = 0;
}

void loop()
{
    long now = millis();

    // Forced GPS update because of capture with distance condition
    latitude  = gps.getLatitude();
    longitude = gps.getLongitude();

    uint8_t update_f1 = now > last_f1 + 60000;
    uint8_t update_f2 = now > last_f2 + 30000;
    uint8_t update_d1 = distance_to(prev_lat1, prev_long1, latitude, longitude) > 5.0;
    uint8_t update_d2 = distance_to(prev_lat2, prev_long2, latitude, longitude) > 10.0;

    if (update_f1 || update_f2 || update_d1 || update_d2)
    {
        #if SDOUTPUT
        // Write them on output
        datafile = SD.open(output, FILE_WRITE);
        #endif

        // Metadata

        put_float(latitude);
        put_separator();

        put_float(longitude);
        put_separator();

        if (update_f1) {
            put_int(dht.readTemperature());
            last_f1 = now;
        }

        put_separator();

        if (update_f1) {
            put_int(dht.readHumidity());
            last_f1 = now;
        }

        put_separator();

        if (update_f2) {
            put_int(TSL2561.readVisibleLux());
            last_f2 = now;
        }

        put_separator();

        if (update_d1) {
            put_float(HP20x.ReadTemperature() / 100.0);
            prev_lat1  = latitude;
            prev_long1 = longitude;
        }

        put_separator();

        if (update_d1) {
            put_float(HP20x.ReadPressure   () / 100.0);
            prev_lat1  = latitude;
            prev_long1 = longitude;
        }

        put_separator();

        if (update_d2) {
            put_float(HP20x.ReadAltitude   () / 100.0);
            prev_lat2  = latitude;
            prev_long2 = longitude;
        }

        put_separator();

        put_endl();

        #if SDOUTPUT
        datafile.close();
        #endif
    }
}
