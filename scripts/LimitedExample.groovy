importlib "scripts/lib/DHT.groovy"
importlib "scripts/lib/GPS.groovy"
importlib "scripts/lib/TSL2561.groovy"

sensor "GPS" with "rx" valued "2" and "tx" valued "1" and "baudrate" valued "4800"

measure "latitude" captured meta
measure "longitude" captured meta

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"

measure "humidity"    captured periodically every 10.s
measure "temperature" captured periodically every 5.s

sensor "TSL2561"

measure "light" captured periodically every 500.ms

export "LimitedExample"