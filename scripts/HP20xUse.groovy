/**
 * Created by fofo on 26/02/16.
 */
importlib "scripts/HP20x.groovy"
sensor "HpSensor"

mesure "pressure" capture meta
measure "temperature" captured periodically every 4.s named "temp_hp20"
measure "altitude" captured periodically every 6.s named "alti_hp20"