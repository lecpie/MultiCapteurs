importlib "scripts/lib/DHT.groovy"
importlib "scripts/lib/TSL2561.groovy"
importlib "scripts/lib/MQ5.groovy"
importlib "scripts/lib/HP20x.groovy"

sensor "HP20x"
measure "altitude" captured meta
measure "pressure" captured meta
measure "temperature" captured periodically every 3.s

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"
measure "humidity"    captured periodically every 2.s

sensor "TSL2561"
measure "light" captured periodically every 1.s

sensor "MQ5" with "pin" valued "A1"
measure "Gas" named "Gaz" captured periodically every 1.s

export "IndoorFullExample"