//https://github.com/Seeed-Studio/Grove_Digital_Light_Sensor

library "TSL2561"
includes "Wire.h" and "Digital_Light_TSL2561.h"
setup "Wire.begin();" and "TSL2561.init();"

measure "light" typed integer
variables "light"
global "int light;"
update "light = (int) TSL2561.readVisibleLux();"
reads "light"
