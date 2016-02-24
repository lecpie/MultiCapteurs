package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamViewControler {

    private Library lib;

    public ParamViewControler(){
        this.lib = new Library();
    }

    public ParamViewControler(Library lib){
        this.lib = lib;
    }

    public List<String> getRequiredArgs(){
        return lib.getRequiredArgs();
    }

    public Library getLib() {
        return lib;
    }

    public void setLib(Library lib) {
        this.lib = lib;
    }
}

