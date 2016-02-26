/**
 * Created by fofo on 26/02/16.
 */
library "HP20x"
includes "Wire.h"
variables "HP20x"
setup "HP20x.begin();"

//Detailed measure def
measure "temperature" typed real
global "float t;"
variables "t"
update "t=(HP20x.ReadTemperature())/100.0;"
reads "t"

measure "pressure" typed real
global "float t;"
variables "t"
update "t=(HP20x.ReadPressure())/100.0;"
reads "t"

measure "altitude" typed real
global "float t;"
variables "t"
update "t=(HP20x.ReadAltitude())/100.0;"
reads "t"