package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.gui.views.ParamView;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementControler {

    private Map<String, Library> loadedLibraries;
    private Library selectedLib;
    private ParamViewControler libUseArgs;

    public SensorManagementControler(Map<String, Library> loadedLibraries){
        selectedLib = new Library();
        libUseArgs = new ParamViewControler();
        this.loadedLibraries = loadedLibraries;
    }

    public Map<String, Library> getLoadedLibraries() {
        return loadedLibraries;
    }

    public ParamViewControler getLibUseArgs() {
        return libUseArgs;
    }

    public Library getSelectedLib() {
        return selectedLib;
    }

    public ParamViewControler setParamViewControler(String libname){
        selectedLib = loadedLibraries.get(libname);
        libUseArgs.setLib(selectedLib);
        return libUseArgs;
    }

    public List<String> getLibNames(){
        List<String> libNames = new ArrayList<>();
        for(String key : loadedLibraries.keySet()){
            libNames.add(key);
        }
        return libNames;
    }

}
