// https://github.com/Seeed-Studio/Grove_Gas_Sensor

library "MQ5"
requires "pin"

measure "Gas" typed real
reads "((float) analogRead(pin)) / 1024"
