importlib "scripts/lib/DHT.groovy"
importlib "scripts/lib/GPS.groovy"
importlib "scripts/lib/TSL2561.groovy"

sensor "GPS" with "rx" valued "2" and "tx" valued "1"

measure "latitude" captured meta
measure "longitude" captured meta

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"

measure "humidity"    captured distanced every 10.m
measure "temperature" captured distanced every 7.m
measure "temperature" captured distanced every 7.m named "fahr" with "format" valued "fahrenheit"

sensor "TSL2561"

measure "light" captured distanced every 4.m

export "distancedExample"