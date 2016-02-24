//Library setup
library "DHT"
includes "DHT.h"
requires "pin" and "type"
variables "dht"
global "DHT dht(pin, type);"
setup "dht.begin();"

//Simple measure def
measure "temperature" typed integer
args "celcius" valued "false" and "fahrenheit" valued "true" and "format" valued "celcius"
variable_based "t" reads "dht.readTemperature(format);"

//Detailed measure def
measure "humidity" typed integer
global "int h;"
variables "h"
update "h = (int) dht.readHumidity();"
reads "h"

