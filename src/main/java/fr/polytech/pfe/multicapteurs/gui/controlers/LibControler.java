package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;

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

    public Map<String, String> getDefaultdArgs(String libName){
        for(String s : loadedLibraries.keySet()){
            if(s.equals(libName)){
                return loadedLibraries.get(s).getDefaultArgs();
            }
        }
        return null;
    }

    public List<String> getRequiredArgs(String libName){
        for(String s : loadedLibraries.keySet()){
            if(s.equals(libName)){
                return loadedLibraries.get(s).getRequiredArgs();
            }
        }
        return null;
    }

    public List<String> getAccessibleArgs(String libName){
        for(String s : loadedLibraries.keySet()){
            if(s.equals(libName)){
                return loadedLibraries.get(s).getAccessibleArgs();
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
