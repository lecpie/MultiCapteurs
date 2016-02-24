package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.generator.ToWiring;
import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.Frequency;
import fr.polytech.pfe.multicapteurs.model.structural.Time;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.*;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppControler {

    private Map<String, Library> librariestoload;
    private Map<String,LibraryUse> usedLibraries;
    private App app;

    public AppControler(){
        this.librariestoload = new HashMap<>();
        this.usedLibraries = new HashMap<>();

        //Mock for lib loading
        mock_DHT();
        mock_Light();
        mock_HP20X();
        mock_GPS();

        this.app = new App();
    }

    public String  generateCode(Library lib, LibraryUse libUse, MeasureUse measureUse){


        List<Library> libs = new ArrayList<>();
        libs.add(lib);

        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);

        return codeGenerator.getResult().toString();

    }

    private void mock_DHT(){
         //Lib Decl
        Library libdht = new Library();
        List<String> dhtvar = Arrays.asList("dht");
        Map<String, String> dhtdefaultargs = new LinkedHashMap<>();
        List <String> dhtrequired = Arrays.asList("dht_pin", "dht_type");
        List <String> dhtincludes = Arrays.asList("DHT.h");
        List <String> dhtglobal = Arrays.asList("DHT dht(dht_pin, dht_type);");
        List <String> dhtsetup = Arrays.asList("dht.begin();");
        libdht.setName("DHT");
        libdht.setIncludes(dhtincludes);
        libdht.setVariables(dhtvar);
        libdht.setDefaultArgs(dhtdefaultargs);
        libdht.setGlobalInstructions(dhtglobal);
        libdht.setSetupInstructions(dhtsetup);
        libdht.setRequiredArgs(dhtrequired);

        // Temperature
        Measure dhttemp = new Measure();
        List <String> dhttempvar = Arrays.asList("temp");
        Map <String, String> dhttempdefaultargs = new LinkedHashMap<>();
        dhttempdefaultargs.put("dht_temp_format_fahr", "true");
        dhttempdefaultargs.put("dht_temp_format_celc", "false");
        dhttempdefaultargs.put("dht_temp_format", "dht_temp_format_celc");
        List <String> dhttempglobal = new ArrayList<>();
        dhttempglobal.add("int temp;");
        List <String> dhttempupdate = new ArrayList<>();
        dhttempupdate.add("temp = (int) dht.readTemperature(dht_temp_format);");
        String dhttempread = "temp";
        dhttemp.setName("temperature");
        dhttemp.setLibrary(libdht);
        dhttemp.setType(Type.INTEGER);
        dhttemp.setVariables(dhttempvar);
        dhttemp.setDefaultArgs(dhttempdefaultargs);
        dhttemp.setGlobalInstructions(dhttempglobal);
        dhttemp.setUpdateInstructions(dhttempupdate);
        dhttemp.setReadExpressionString(dhttempread);
        dhttemp.setSensorFrequency(new Frequency(50, Time.SEC));
        libdht.getMeasures().put(dhttemp.getName(), dhttemp);

        // Humidity
        Measure dhthum = new Measure();
        List <String> dhthumvar = Arrays.asList("temp");
        List <String> dhthumglobal = new ArrayList<>();
        dhthumglobal.add("int temp;");
        List <String> dhthumupdate = new ArrayList<>();
        dhthumupdate.add("temp = (int) dht.readHumidity();");
        String dhthumread = "temp";
        dhthum.setName("humidity");
        dhthum.setLibrary(libdht);
        dhthum.setType(Type.INTEGER);
        dhthum.setVariables(dhttempvar);
        dhthum.setGlobalInstructions(dhthumglobal);
        dhthum.setUpdateInstructions(dhthumupdate);
        dhthum.setReadExpressionString(dhthumread);
        dhthum.setSensorFrequency(new Frequency(60, Time.SEC));
        libdht.getMeasures().put(dhthum.getName(), dhthum);

        librariestoload.put(libdht.getName(), libdht);
    }

    private void mock_Light(){
        Library groovelightsensor = new Library();
        groovelightsensor.setIncludes(Arrays.asList("Wire.h", "Digital_Light_TSL2561.h"));
        groovelightsensor.setName("GROOVELIGHTSENSOR");
        groovelightsensor.setSetupInstructions(Arrays.asList("Wire.begin();"));

        Measure groovelight = new Measure();
        groovelight.setLibrary(groovelightsensor);
        groovelight.setVariables(Arrays.asList("light"));
        groovelight.setType(Type.INTEGER);
        groovelight.setGlobalInstructions(Arrays.asList("int light;"));
        groovelight.setUpdateInstructions(Arrays.asList("light = (int) TSL2561.readVisibleLux();"));
        groovelight.setReadExpressionString("light");
        groovelight.setName("light");
        groovelightsensor.getMeasures().put("light", groovelight);

        librariestoload.put(groovelightsensor.getName(), groovelightsensor);
    }

    private void mock_HP20X(){
        Library libhp20x = new Library();
        libhp20x.setName("HP20x");
        libhp20x.setIncludes(Arrays.asList("HP20x_dev.h","Arduino.h","Wire.h"));
        libhp20x.setSetupInstructions(Arrays.asList("delay(150);", "HP20x.begin();", "delay(100);"));

        Measure hp20temp = new Measure();
        hp20temp.setName("temperature");
        hp20temp.setLibrary(libhp20x);
        hp20temp.setType(Type.INTEGER);
        hp20temp.setVariables(Arrays.asList("temp"));
        hp20temp.setGlobalInstructions(Arrays.asList("int temp;"));
        hp20temp.setUpdateInstructions(Arrays.asList("temp = (int) HP20x.ReadTemperature();"));
        hp20temp.setReadExpressionString("temp");
        libhp20x.getMeasures().put(hp20temp.getName(), hp20temp);

        Measure hp20alt = new Measure();
        hp20alt.setLibrary(libhp20x);
        hp20alt.setName("altitude");
        hp20alt.setType(Type.INTEGER);
        hp20alt.setVariables(Arrays.asList("alt"));
        hp20alt.setGlobalInstructions(Arrays.asList("int alt;"));
        hp20alt.setUpdateInstructions(Arrays.asList("alt = (int) HP20x.ReadAltitude();"));
        hp20alt.setReadExpressionString("alt");
        libhp20x.getMeasures().put(hp20alt.getName(), hp20alt);

        Measure hp20pres = new Measure();
        hp20pres.setLibrary(libhp20x);
        hp20pres.setName("pressure");
        hp20pres.setType(Type.INTEGER);
        hp20pres.setVariables(Arrays.asList("pres"));
        hp20pres.setGlobalInstructions(Arrays.asList("int pres;"));
        hp20pres.setUpdateInstructions(Arrays.asList("pres = (int) HP20x.ReadPressure();"));
        hp20pres.setReadExpressionString("pres");
        libhp20x.getMeasures().put(hp20pres.getName(), hp20pres);

        librariestoload.put(libhp20x.getName(), libhp20x);
    }

    private void mock_GPS(){
        Library GPS = new Library();
        GPS.setName("GPS");
        GPS.setIncludes(Arrays.asList("SoftwareSerial.h", "BufferedGPS.h"));
        GPS.setRequiredArgs(Arrays.asList("rx", "tx"));
        GPS.setVariables(Arrays.asList("gps"));
        Map<String, String> defaultArgs = new LinkedHashMap<>();
        defaultArgs.put("baudrate", "4800");
        defaultArgs.put("refreshrate", "50");
        GPS.setDefaultArgs(defaultArgs);
        GPS.setGlobalInstructions(Arrays.asList("BufferedGPS gps(rx, tx, refreshrate);"));
        GPS.setSetupInstructions(Arrays.asList("gps.begin(baudrate);"));

        Measure lat = new Measure();
        lat.setName("latitude");
        lat.setType(Type.REAL);
        lat.getVariables().add("l");
        lat.getGlobalInstructions().add(lat.getType().toString() + " " + "l" + ";");
        lat.setReadExpressionString("l");
        lat.getUpdateInstructions().add("l" + "=" + "gps.getLatitude();");

        Measure lon = new Measure();
        lon.setName("longitude");
        lon.setType(Type.REAL);
        lon.getVariables().add("l");
        lon.getGlobalInstructions().add(lon.getType().toString() + " " + "l" + ";");
        lon.setReadExpressionString("l");
        lon.getUpdateInstructions().add("l" + "=" + "gps.getLongitude();");

        librariestoload.put(GPS.getName(), GPS);
    }
/*

    public List<String> getLibNames(){
        List<String> libNames = new ArrayList<>();
        for(Library l : libraries){
            libNames.add(l.getName());
        }
        return libNames;
    }*/

    public static void main(String[] args) {
        Type t = Type.REAL;
        System.out.println(t);
    }

    public Map<String, Library> getLibrariestoload() {
        return librariestoload;
    }

    public void setLibrariestoload(Map<String, Library> librariestoload) {
        this.librariestoload = librariestoload;
    }

    public Map<String, LibraryUse> getUsedLibraries() {
        return usedLibraries;
    }

    public void setUsedLibraries(Map<String, LibraryUse> usedLibraries) {
        this.usedLibraries = usedLibraries;
    }

}
