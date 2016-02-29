// https://github.com/Seeed-Studio/Grove_Gas_Sensor

library "MQ5"
requires "pin"

measure "GasDensity" typed real
reads "((float) analogRead(pin)) / 1024"
