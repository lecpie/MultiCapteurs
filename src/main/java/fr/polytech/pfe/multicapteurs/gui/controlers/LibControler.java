package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.views.ParamView;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class LibControler {

    private Map<String, Library> loadedLibraries;
    private Library selectedLib;

    public LibControler(Map<String, Library> loadedLibraries){
        selectedLib = new Library();
        this.loadedLibraries = loadedLibraries;
    }

    public Map<String, Library> getLoadedLibraries() {
        return loadedLibraries;
    }


    public Library getSelectedLib() {
        return selectedLib;
    }


    public List<String> getLibNames(){
        List<String> libNames = new ArrayList<>();
        for(String key : loadedLibraries.keySet()){
            libNames.add(key);
        }
        return libNames;
    }

    public String getSelectedLibName(){
        return selectedLib.getName();
    }

    public List<String> getRequiredArgs(String libName){
        for(String lib : loadedLibraries.keySet()){
            if(lib.equals(libName)){
                return loadedLibraries.get(lib).getRequiredArgs();
            }
        }
        return null;
    }
/*

    public void setSelectedLib (String libname){
        for(String lib : loadedLibraries.keySet()){
            if(lib.equals(libname)){
                selectedLib = loadedLibraries.get(lib);
            }
        }
    }
*/


}
