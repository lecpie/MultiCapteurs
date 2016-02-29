importlib "scripts/lib/MQ5.groovy"

sensor "MQ5" with "pin" valued "A1"

measure "GasDensity" named "gaz" captured periodically every 1.s

export "GasExample"