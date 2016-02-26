package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.gui.tools.LibraryLoader;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.Period;
import fr.polytech.pfe.multicapteurs.model.structural.Time;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppControler {

    //private Map<String, Library> loadedLibraries;
    private Map<String,LibraryUse> usedLibraries;
    //private LibraryUse currentLibUse;

    private LibraryLoader libraryLoader = new LibraryLoader();

    private App app;

    private LibControler libControler;

    public AppControler(){
        this.usedLibraries = new HashMap<>();

        app = new App();

        loadLibs("scripts/lib");

        this.libControler = new LibControler(app.getLoadedLibraries());
    }

    void loadLibs(String folderPath) {
        Map <String, Library> libraryMap = new HashMap<>();

        try {
            List <Library> libraries = libraryLoader.loadFolder(folderPath);

            for (Library library : libraries) {
                libraryMap.put(library.getName(), library);
            }

            app.setLoadedLibraries(libraryMap);
        }
        catch (FileNotFoundException e) {
            System.err.println("Library folder : " + folderPath + " not found");
        }
    }


    public LibraryUse createLibraryUse(){
        LibraryUse newLibUse = new LibraryUse();
        newLibUse.setLibrary(libControler.getSelectedLib());
       // newLibUse.getArgsValues().put()
        app.getUsedLibraries().add(newLibUse);

        return null;
    }

    public String  generateCode(){


        //List<Library> libs = new ArrayList<>();
        //libs.add(lib);

        //Visitor codeGenerator = new ToWiring();
        //app.accept(codeGenerator);

        //return codeGenerator.getResult().toString();
        return "coming soon";
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
        dhttemp.setSensorFrequency(new Period(50, Time.SEC));
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
        dhthum.setSensorFrequency(new Period(60, Time.SEC));
        libdht.getMeasures().put(dhthum.getName(), dhthum);

        app.getLoadedLibraries().put(libdht.getName(), libdht);
    }

    private void mock_Light(){
        Library groovelightsensor = new Library();
        groovelightsensor.setIncludes(Arrays.asList("Wire.h", "Digital_Light_TSL2561.h"));
        groovelightsensor.setName("GROVELIGHTSENSOR");
        groovelightsensor.setSetupInstructions(Arrays.asList("Wire.begin();"));

        Measure groovelight = new Measure();
        groovelight.setLibrary(groovelightsensor);
        groovelight.setVariables(Arrays.asList("light"));
        groovelight.setType(Type.INTEGER);
        groovelight.setGlobalInstructions(Arrays.asList("int light;"));
        groovelight.setUpdateInstructions(Arrays.asList("light = (int) TSL2561.readVisibleLux();"));
        groovelight.setReadExpressionString("light");
        groovelight.setName("light");
        groovelight.setSensorFrequency(new Period(50, Time.SEC));
        groovelightsensor.getMeasures().put("light", groovelight);

        app.getLoadedLibraries().put(groovelightsensor.getName(), groovelightsensor);
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
        hp20temp.setSensorFrequency(new Period(70, Time.SEC));
        libhp20x.getMeasures().put(hp20temp.getName(), hp20temp);

        Measure hp20alt = new Measure();
        hp20alt.setLibrary(libhp20x);
        hp20alt.setName("altitude");
        hp20alt.setType(Type.INTEGER);
        hp20alt.setVariables(Arrays.asList("alt"));
        hp20alt.setGlobalInstructions(Arrays.asList("int alt;"));
        hp20alt.setUpdateInstructions(Arrays.asList("alt = (int) HP20x.ReadAltitude();"));
        hp20alt.setReadExpressionString("alt");
        hp20alt.setSensorFrequency(new Period(70, Time.SEC));
        libhp20x.getMeasures().put(hp20alt.getName(), hp20alt);

        Measure hp20pres = new Measure();
        hp20pres.setLibrary(libhp20x);
        hp20pres.setName("pressure");
        hp20pres.setType(Type.INTEGER);
        hp20pres.setVariables(Arrays.asList("pres"));
        hp20pres.setGlobalInstructions(Arrays.asList("int pres;"));
        hp20pres.setUpdateInstructions(Arrays.asList("pres = (int) HP20x.ReadPressure();"));
        hp20pres.setReadExpressionString("pres");
        hp20pres.setSensorFrequency(new Period(70, Time.SEC));
        libhp20x.getMeasures().put(hp20pres.getName(), hp20pres);

        app.getLoadedLibraries().put(libhp20x.getName(), libhp20x);
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
        lat.setSensorFrequency(new Period(30, Time.SEC));
        lat.getUpdateInstructions().add("l" + "=" + "gps.getLatitude();");

        Measure lon = new Measure();
        lon.setName("longitude");
        lon.setType(Type.REAL);
        lon.getVariables().add("l");
        lon.getGlobalInstructions().add(lon.getType().toString() + " " + "l" + ";");
        lon.setReadExpressionString("l");
        lon.setSensorFrequency(new Period(30, Time.SEC));
        lon.getUpdateInstructions().add("l" + "=" + "gps.getLongitude();");

        app.getLoadedLibraries().put(GPS.getName(), GPS);
    }

    public Map<String, LibraryUse> getUsedLibraries() {
        return usedLibraries;
    }

    public void setUsedLibraries(Map<String, LibraryUse> usedLibraries) {
        this.usedLibraries = usedLibraries;
    }

    public LibControler getLibControler() {
        return libControler;
    }

    public App getApp() {
        return app;
    }

    public static void main(String[] args) {
        AppControler appc = new AppControler();
        System.out.println(appc.getApp().getLoadedLibraries());
    }
}
