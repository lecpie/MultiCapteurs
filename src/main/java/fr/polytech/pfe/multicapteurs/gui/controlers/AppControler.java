package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppControler {

    //eval du script + get model pour avoir les lib
    List<Library> libraries;

    public AppControler(){
    }

    public List<String> getLibNames(){
        List<String> libNames = new ArrayList<>();
        for(Library l : libraries){
            libNames.add(l.getName());
        }
        return libNames;
    }

}
