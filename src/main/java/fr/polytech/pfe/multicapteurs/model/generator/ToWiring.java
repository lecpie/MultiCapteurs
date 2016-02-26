package fr.polytech.pfe.multicapteurs.model.generator;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.CompilationError;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.*;
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.*;

import java.util.*;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {

    private static final boolean SDOUTPUT     = true;
    private static final boolean SERIALOUTPUT = true;

    private VariableGenerator variableGenerator = new IncrementalVariableGenerator();

    private Map <TriggeredCapture, String> measureConditionalVariable;
    private Map <PeriodicCapture, String>  periodicQueryVariable;
    private Map <DistancedCapture, String> lastLatitudeVariable;
    private Map <DistancedCapture, String> lastLongitudeVariable;

    private Map <MeasureUse, Boolean> updatedMeasures;
    private Map <String, Boolean>     updatedCaptureVariable;

    MeasureUse latitude = null;
    MeasureUse longitude = null;

    boolean requiresGPS = false;

    public ToWiring() {
        this.result = new StringBuffer();
    }

    private void add(String s) {
        result.append(s);
    }

    private void w(String s) {
        result.append(String.format("%s\n",s));
    }

    private void def (String name) {
        add("#define " + name);
    }

    private void def (String name, String val) {
        def(name);
        w(" " + val);
    }

    private void undef (String name) {
        w("#undef " + name);
    }


    private void include(String included) {
        w("#include <" + included + ">");
    }

    private void comment(String commented) {
        w("// " + commented);
    }

	@Override
	public void include(Library library) {
        for (String included : library.getIncludes()) {
            include(included);
        }
	}

	@Override
	public void visit(App app) {
        measureConditionalVariable = new HashMap<>();
        periodicQueryVariable      = new HashMap<>();
        lastLatitudeVariable       = new HashMap<>();
        lastLongitudeVariable      = new HashMap<>();
        updatedMeasures            = new HashMap<>();
        updatedCaptureVariable     = new HashMap<>();

        include("Arduino.h");
        include("SD.h");

        if (SDOUTPUT) {
            comment("SD card configuration");

            def("SDCHIPSELECT", "4");
            def("SDPIN",        "10");

            w("char output[] = \"" + app.getOutput().getPath() + "\";");
            w("File datafile;");
        }

        w("const char separator = ',';");
        w("const char endline   = '\\n';");

        def("DATABUFSIZE", "128");

        w("uint8_t databuf[DATABUFSIZE];");
        w("uint8_t idatabuf = 0;");

        w("void put_data(const char * buf, uint8_t sz) {");
        w("\twhile (sz > 0) {");

        w("\t\tuint8_t writable = DATABUFSIZE - idatabuf;");
        w("\t\tuint8_t towrite  = (sz > writable) ? writable : sz;");
        w("\t\tmemcpy(databuf + idatabuf, buf, towrite);");
        w("\t\tidatabuf += towrite;");
        w("\t\tbuf      += towrite;");
        w("\t\tsz       -= towrite;");

        w("\t\tif (idatabuf >= DATABUFSIZE) {");

        if (SERIALOUTPUT) {
            w("\t\t\tSerial.write(databuf, DATABUFSIZE);");
        }

        if (SDOUTPUT) {
            w("\t\t\tdatafile.write(databuf, DATABUFSIZE);");
        }

        w("\t\t\tidatabuf = 0;");
        w("\t\t}");
        w("\t}");
        w("}");

        w("char conversionbuffer[64];");

        w("void put_char (char val) {");
        w("\tput_data(&val, sizeof(char));");
        w("}");

        w("void put_int (int val) {");
        w("\tput_data(conversionbuffer, sprintf(conversionbuffer, \"%d\", val));");
        w("}");

        w("void put_float (float val) {");
        w("\tint i = 0;");
        w("\tdtostrf(val, 0, 6, conversionbuffer);");
        w("\twhile (conversionbuffer[++i] != '\\0');");
        w("\tput_data(conversionbuffer, i);");
        w("}");

        w("void put_separator() { put_char(separator); }");
        w("void put_endl     () { put_char(endline);   }");

        for (LibraryUse usedlib : app.getUsedLibraries()) {
            Library lib = usedlib.getLibrary();

            usedlib.loadDefaults();

            // Check required args
            for (String arg : usedlib.getLibrary().getRequiredArgs()) {
                if (!usedlib.getArgsValues().containsKey(arg)) {
                    throw new CompilationError("Argument " + arg + " expected for library " + usedlib.getLibrary().getName());
                }
            }

            for (String var : lib.getVariables()) {
                usedlib.getArgsValues().put(var, variableGenerator.genName());
            }

            lib.include(this);
        }

        // Global

        for (LibraryUse usedlib : app.getUsedLibraries()) {
            usedlib.global(this);
        }

        for (MeasureUse measureUse : app.getOutput().getPrintedMeasures().values()) {
            Measure measure = measureUse.getMeasure();

            measureUse.loadDefaults();

            // Check required args
            for (String arg : measure.getRequiredArgs()) {
                if (!measureUse.getArgsValues().containsKey(arg)) {
                    throw new CompilationError("Argument " + arg + " expected for measure " + measure.getName());
                }
            }

            for (String var : measure.getVariables()) {
                measureUse.getArgsValues().put(var, variableGenerator.genName());
            }

            if ("latitude".equals(measureUse.getName())) {
                latitude = measureUse;
            }
            else if ("longitude".equals(measureUse.getName())) {
                longitude = measureUse;
            }

        }

        for (MeasureUse measureUse : app.getOutput().getPrintedMeasures().values()) {
            measureUse.global(this);
        }

        for (MeasureUse measureUse : app.getOutput().getPrintedMeasures().values()) {
            if (measureUse.getCaptureMethod() instanceof TriggeredCapture) {
                TriggeredCapture triggeredCapture = (TriggeredCapture) measureUse.getCaptureMethod();

                if (!measureConditionalVariable.containsKey(triggeredCapture)) {
                    measureConditionalVariable.put(triggeredCapture, variableGenerator.genName());
                }
            }

            if (measureUse.getCaptureMethod() instanceof DistancedCapture) {
                DistancedCapture distancedCapture = (DistancedCapture) measureUse.getCaptureMethod();

                if (!lastLatitudeVariable.containsKey(distancedCapture)) {
                    lastLatitudeVariable.put(distancedCapture, variableGenerator.genName());
                }

                if (!lastLongitudeVariable.containsKey(distancedCapture)) {
                    lastLongitudeVariable.put(distancedCapture, variableGenerator.genName());
                }

                requiresGPS = true;
            }
        }

        comment("Measure write conditions");

        for (TriggeredCapture triggeredCapture : measureConditionalVariable.keySet()) {
            triggeredCapture.global(this);
        }

        if (requiresGPS) {
            w("double distance_to (double lat1, double long1, double lat2, double long2) {");
            w("\tdouble delta = radians(long1-long2);");
            w("\tdouble sdlong = sin(delta);");
            w("\tdouble cdlong = cos(delta);");
            w("\tlat1 = radians(lat1);");
            w("\tlat2 = radians(lat2);lat2 = radians(lat2);");
            w("\tdouble slat1 = sin(lat1);");
            w("\tdouble clat1 = cos(lat1);");
            w("\tdouble slat2 = sin(lat2);");
            w("\tdouble clat2 = cos(lat2);");
            w("\tdelta = (clat1 * slat2) - (slat1 * clat2 * cdlong);");
            w("\tdelta = sq(delta);");
            w("\tdelta += sq(clat2 * sdlong);");
            w("\tdelta = sqrt(delta);");
            w("\tdouble denom = (slat1 * slat2) + (clat1 * clat2 * cdlong);");
            w("\tdelta = atan2(delta, denom);");
            w("\treturn delta * 6372795;");
            w("}");
        }

        w("void setup() {");

        // SD setup

        if (SDOUTPUT) {
            comment("SD setup");
            w("\tpinMode(SDPIN, OUTPUT);");
            w("\tSD.begin(SDCHIPSELECT);");
        }

        if (SERIALOUTPUT) {
            comment("Serial setup");
            w("\tSerial.begin(115200);");
        }

        for (LibraryUse libraryUse : app.getUsedLibraries()) {
            libraryUse.setup(this);
        }

        for(MeasureUse measureUse: app.getOutput().getPrintedMeasures().values()){
            measureUse.setup(this);
        }

        if (SDOUTPUT) {
            w("\tif (SD.exists(output)) {");
            w("\t\tSD.remove(output);");
            w("}");
            w("\tdatafile = SD.open(output, FILE_WRITE);");
        }

        for (String measureName : app.getOutput().getPrintedMeasures().keySet()) {
            w("\tput_data(\"" + measureName + "\", " + measureName.length() + ");");
            w("\tput_separator();");
        }

        w("\tput_endl();");

        if (SDOUTPUT) {
            w("\tdatafile.close();");
        }

        w("}");


        w("void loop() {");

        w("\tlong now = millis();");

        if (requiresGPS) {
            latitude.update(this);
            longitude.update(this);

            updatedMeasures.put(latitude,  true);
            updatedMeasures.put(longitude, true);
        }

        for (TriggeredCapture triggeredCapture : measureConditionalVariable.keySet()) {
            String variable = measureConditionalVariable.get(triggeredCapture);
            if (!updatedCaptureVariable.containsKey(variable) || !updatedCaptureVariable.get(variable)) {
                triggeredCapture.update(this);
                updatedCaptureVariable.put(variable, true);
            }
        }

        if (measureConditionalVariable.size() > 0) {
            List <String> conditionVariableList = new ArrayList<>(measureConditionalVariable.values());

            add("\tif (");

            add(conditionVariableList.get(0));

            for (int i = 1; i < conditionVariableList.size(); ++i) {
                add(" || " + conditionVariableList.get(i));
            }

            w(")");
            w("\t{");
        }

        if (SDOUTPUT) {
            w("\tdatafile = SD.open(output, FILE_WRITE);");
        }

        for (MeasureUse measureUse : app.getOutput().getPrintedMeasures().values()) {

            if (measureUse.getCaptureMethod() instanceof TriggeredCapture) {
                TriggeredCapture triggeredCapture = (TriggeredCapture) measureUse.getCaptureMethod();
                w("\tif (" + measureConditionalVariable.get(triggeredCapture) +  ")");
            }
            w("\t{");

            String ctype = getCType(measureUse.getType());

            if (ctype == null) {
                throw new CompilationError("measure " + measureUse.getName() + " has type " + measureUse.getType()
                                           + "but is not implemented for output writing");
            }

            if (!updatedMeasures.containsKey(measureUse) || !updatedMeasures.get(measureUse)) {
                measureUse.update(this);
                updatedMeasures.put(measureUse, true);
            }
            add("\tput_" + ctype + "(" );
            expression(measureUse);
            w(");");

            CaptureMethod captureMethod = measureUse.getCaptureMethod();
            if (measureUse.getCaptureMethod() instanceof PeriodicCapture) {
                w("\t\t" + periodicQueryVariable.get((PeriodicCapture) captureMethod) + " = now;");
            }
            else if (measureUse.getCaptureMethod() instanceof DistancedCapture) {
                DistancedCapture distancedCapture = (DistancedCapture) captureMethod;

                w("\t\t" + lastLatitudeVariable.get(distancedCapture) + " = ");
                latitude.expression(this);
                w(";");

                w("\t\t" + lastLongitudeVariable.get(distancedCapture) + " = ");
                longitude.expression(this);
                w(";");
            }

            w("\t}");
            w("\tput_separator();");
        }

        w("\tput_endl();");

        if (SDOUTPUT) {
            w("\tdatafile.close();");
        }

        if (measureConditionalVariable.size() > 0) {
            w("\t}");
        }

        w("}");
	}

    @Override
    public void expression(AsapCapture captureMethod) {
        add("true");
    }

    @Override
    public void expression(PeriodicCapture captureMethod) {
        add("now > " + periodicQueryVariable.get(captureMethod) + " + " + captureMethod.getCapturePeriod().getRateintoMS());
    }

    @Override
    public void expression(DistancedCapture captureMethod) {
        w("distance_to(" + lastLatitudeVariable.get(captureMethod) + "," + lastLongitudeVariable.get(captureMethod) + ",");

        latitude.expression(this);

        w(",");

        longitude.expression(this);

        w(") > " + captureMethod.getDistance());
    }

    @Override
    public void global(CaptureMethod captureMethod) {
    }

    @Override
    public void global(AsapCapture captureMethod) {
    }

    @Override
    public void global(TriggeredCapture captureMethod) {
        w("uint8_t " + measureConditionalVariable.get(captureMethod) + ";");
    }

    @Override
    public void global(PeriodicCapture captureMethod) {
        if (!periodicQueryVariable.containsKey(captureMethod)) {
            periodicQueryVariable.put(captureMethod, variableGenerator.genName());
        }

        w("long " + periodicQueryVariable.get(captureMethod) + ";");
    }

    @Override
    public void global(DistancedCapture captureMethod) {
        String latitudeVar  = variableGenerator.genName();
        String longitudeVar = variableGenerator.genName();

        lastLatitudeVariable .put(captureMethod, latitudeVar);
        lastLongitudeVariable.put(captureMethod, longitudeVar);

        w("float " + latitudeVar  + ";");
        w("float " + longitudeVar + ";");
    }

    @Override
    public void update(TriggeredCapture captureMethod) {
        w(measureConditionalVariable.get(captureMethod) + " = ");

        captureMethod.expression(this);

        w(";");
    }

    private static String getCType(Type type) {
        switch (type) {
            case INTEGER:
                return "int";
            case REAL:
                return "float";
        }

        return null;
    }

    @SafeVarargs
    private final void loadArgs(Map <String, String> ... args) {
        w("");
        for (Map <String, String> priorityArgs: args) {
            for (String arg : priorityArgs.keySet()) {
                def(arg, priorityArgs.get(arg));
            }
        }
    }
    @SafeVarargs
    private final void unloadArgs( Map <String, String> ... args) {
        for (Map <String, String> priorityArgs: args) {
            for (String arg : priorityArgs.keySet()) {
                undef(arg);
            }
        }
    }

    @SafeVarargs
    private final void instructions(List<String> instructions , Map <String, String>  ... args) {
        // Try to pollute a bit less
        if (instructions.size() == 0) return;

        loadArgs(args);

        for (String instruction : instructions) {
            w(instruction);
        }

        unloadArgs(args);
    }

	@Override
	public void setup(LibraryUse libraryUse) {
        instructions(libraryUse.getLibrary().getSetupInstructions(), libraryUse.getArgsValues());
	}

	@Override
	public void global(LibraryUse libraryUse) {
        instructions(libraryUse.getLibrary().getGlobalInstructions(), libraryUse.getArgsValues());
	}

	@Override
	public void output(Output output) {

	}

	@Override
	public void setup(MeasureUse measureUse) {
        instructions(measureUse.getMeasure().getSetupInstructions(), measureUse.getLibraryUse().getArgsValues(),
                                                                     measureUse                .getArgsValues());
	}

	@Override
	public void global(MeasureUse measureUse) {
        instructions(measureUse.getMeasure().getGlobalInstructions(), measureUse.getLibraryUse().getArgsValues(),
                                                                      measureUse                .getArgsValues());
	}

	@Override
	public void update(MeasureUse measureUse) {
        instructions(measureUse.getMeasure().getUpdateInstructions(), measureUse.getLibraryUse().getArgsValues(),
                                                                      measureUse                .getArgsValues());
	}

	@Override
	public void expression(MeasureUse measureUse) {
        loadArgs(measureUse.getLibraryUse().getArgsValues(),
                 measureUse                .getArgsValues());

        w(measureUse.getMeasure().getReadExpressionString());

        unloadArgs(measureUse.getLibraryUse().getArgsValues(),
                   measureUse                .getArgsValues());
	}
}
