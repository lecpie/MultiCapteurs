importlib "scripts/lib/DHT.groovy"
importlib "scripts/lib/GPS.groovy"
importlib "scripts/lib/TSL2561.groovy"
importlib "scripts/lib/MQ5.groovy"
importlib "scripts/lib/HP20x.groovy"

sensor "GPS" with "rx" valued "2" and "tx" valued "1" and "baudrate" valued "4800"

measure "latitude" captured meta
measure "longitude" captured meta

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"

measure "humidity"    captured distanced every 7.m
measure "temperature" captured distanced every 10.m

sensor "TSL2561"

measure "light" captured distanced every 4.m

sensor "MQ5" with "pin" valued "A1"

measure "Gas" named "Gaz" captured distanced every 2.m

sensor "HP20x"

measure "pressure" captured distanced every 5.m
measure "temperature" named "hp_temp" captured distanced every 3.m
measure "altitude"    named "altitude" captured distanced every 6.m

export "FullExample"