importlib "scripts/lib/DHT_def.groovy"

sensor "DHT" with "type" valued "DHT11" and "pin" valued "A0"

measure "humidity"    captured periodically every 1.s
measure "temperature" captured periodically every 3.s
measure "temperature" named "temp_fahr" captured periodically every 10.s with "format" valued "fahrenheit"


export "my_DHT_use"