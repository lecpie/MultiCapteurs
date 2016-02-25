importlib "scripts/lib/DHT.groovy"

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"

measure "humidity"    captured meta
measure "temperature" captured periodically every 4.s named "temp_celc"
measure "temperature" named "temp_fahr" captured periodically every 10.s with "format" valued "fahrenheit"


export "PeriodicDHT"