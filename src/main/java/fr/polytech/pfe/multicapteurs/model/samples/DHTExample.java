package fr.polytech.pfe.multicapteurs.model.samples;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.generator.ToWiring;
import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.*;
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.PeriodicCapture;

import java.util.*;

/**
 * Created by Louis on 21/02/2016.
 */
public class DHTExample {
    public static void main(String[] args) {

        // Declaring libraries
        Library libdht = new Library();

        List<String> dhtvar = Arrays.asList("dht");

        Map<String, String> dhtdefaultargs = new LinkedHashMap<>();

        List <String> dhtrequired = Arrays.asList("dht_pin", "dht_type");

        List <String> dhtincludes = Arrays.asList("DHT.h");
        List <String> dhtglobal = Arrays.asList("DHT dht(dht_pin, dht_type);");
        List <String> dhtsetup = Arrays.asList("dht.begin();");

        libdht.setIncludes(dhtincludes);
        libdht.setVariables(dhtvar);
        libdht.setDefaultArgs(dhtdefaultargs);
        libdht.setGlobalInstructions(dhtglobal);
        libdht.setSetupInstructions(dhtsetup);
        libdht.setRequiredArgs(dhtrequired);

        // Measures for dht

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

        libdht.getMeasures().put(dhttemp.getName(), dhttemp);
        //FIXME : Random values to test freq
        dhttemp.setSensorFrequency(new Period(50, Time.SEC));

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

        libdht.getMeasures().put(dhthum.getName(), dhthum);
        //FIXME : Random values to test freq
        dhthum.setSensorFrequency(new Period(60, Time.SEC));

        // Transition from library definition to ArduinoML
        Map <String, Library> librariestoload = new HashMap<>();
        librariestoload.put("DHT", libdht);

        // ArduinoML configuration
        App dhtapp = new App();
        dhtapp.setLoadedLibraries(librariestoload);

        Library dhtloaded = dhtapp.getLoadedLibraries().get("DHT");

        LibraryUse usedht = new LibraryUse();
        usedht.setLibrary(dhtloaded);
        dhtapp.getUsedLibraries().add(usedht);

        // Type and pin for one dht sensor using one instance of the DHT library

        usedht.getArgsValues().put("dht_pin", "A0");
        usedht.getArgsValues().put("dht_type", "DHT11");

        //For output management
        Output out = new Output("measures.csv");

        // Used measures for this dht
        MeasureUse tempcelc = new MeasureUse();
        tempcelc.setName("temperature_celcius");
        tempcelc.setLibraryUse(usedht);
        tempcelc.setMeasure(dhtloaded.getMeasures().get("temperature"));
        tempcelc.setCaptureMethod(new PeriodicCapture(new Period(4, Time.SEC)));
        out.addMeasureUse("temperature_celcius", tempcelc);

        MeasureUse tempfahr = new MeasureUse();
        tempfahr.setName("temperature_fahr");
        tempfahr.setLibraryUse(usedht);
        tempfahr.setMeasure(dhtloaded.getMeasures().get("temperature"));
        tempfahr.getArgsValues().put("format", "dht_temp_format_fahr");
        tempfahr.setCaptureMethod(new PeriodicCapture(new Period(5, Time.SEC)));
        out.addMeasureUse("temperature_fahr", tempfahr);

        MeasureUse hum = new MeasureUse();
        hum.setName("humidity");
        hum.setLibraryUse(usedht);
        hum.setMeasure(dhtloaded.getMeasures().get("humidity"));
        hum.setCaptureMethod(new PeriodicCapture(new Period(7, Time.SEC)));
        out.addMeasureUse("humidity", hum);

        // Building the App
        dhtapp.setName("DHTApp");
        dhtapp.setOutput(out);

        // Generating Code
        Visitor codeGenerator = new ToWiring();
        dhtapp.accept(codeGenerator);

        //Fill Output object

        // Printing the generated code on the console
        System.out.println(codeGenerator.getResult());
    }
}
