//Library setup
library "DHT"
includes "DHT.h"
requires "dht_pin" and "dht_type"
variables "dht"
global "DHT dht(dht_pin, dht_type);"
setup "dht.begin();" and "Serial.begin(9600);"

//Simple measure def
measure "temperature" typed integer
args "celcius" valued "false" and "fahrenheit" valued "false" and "format" valued "celcius"
variable_based "t" reads "dht.readTemperature(format);"

//Detailed measure def
measure "humidity" typed integer
global "int h;"
variables "h"
update "h = (int) dht.readHumidity();"
reads "h"

