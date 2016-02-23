package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementControler {

    List<Library> libs;
    List<LibraryUse> libUses;

    public SensorManagementControler(){
        libs = new ArrayList<>();
        libUses = new ArrayList<>();
        initLibs();
        initLibUses();
    }

    private void initLibs(){

    }

    private void initLibUses(){

    }

    public List<Library> getLibs() {
        return libs;
    }

    public void setLibs(List<Library> libs) {
        this.libs = libs;
    }

    public List<LibraryUse> getLibUses() {
        return libUses;
    }

    public void setLibUses(List<LibraryUse> libUses) {
        this.libUses = libUses;
    }
}
