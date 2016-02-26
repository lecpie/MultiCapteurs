importlib "scripts/lib/HP20x.groovy"

sensor "HP20x"

measure "pressure" captured meta
measure "temperature" captured periodically every 4.s named "temp_hp20"
measure "altitude" captured periodically every 6.s named "alti_hp20"

export "HP20xTest"