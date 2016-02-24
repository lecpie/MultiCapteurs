package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementControler {

    private List<Library> libs;
    private List<LibraryUse> libUses;

    public SensorManagementControler(){
        libs = new ArrayList<>();
        libUses = new ArrayList<>();
        //Gets all available libs
        initLibs();
    }

    private void initLibs(){

    }


    public List<Library> getLibs() {
        return libs;
    }

    public void setLibs(List<Library> libs) {
        this.libs = libs;
    }

    public void addLib(Library lib){
        libs.add(lib);
    }

    public void removeLib(Library lib){
        libs.remove(lib);
    }

    public void addLibUse(LibraryUse libUse){
        libUses.add(libUse);
    }

    public void removeLibUse(LibraryUse libUse){
        libUses.remove(libUse);
    }

    public static void main(String[] args) {
        SensorManagementControler smc = new SensorManagementControler();
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


        smc.addLib(libdht);

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

    }

}
