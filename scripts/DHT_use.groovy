importlib "scripts/DHT_def.groovy"

uselib "DHT" with "dht_type" valued "DHT11" and "pin" valued "A2"

//Simple def
sensor "temperature" freq "3"

//Detailed def, precising which sampling rate to use
sensor "humidity" named "theHumidity" freq "2" rate "second"

export "my_DHT_use"