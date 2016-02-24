importlib "scripts/lib/DHT.groovy"

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"

measure "humidity"    captured asap
measure "temperature" captured periodically every 3.s named "temp_celc"
measure "temperature" named "temp_fahr" captured asap with "format" valued "fahrenheit"


export "PeriodicDHT"