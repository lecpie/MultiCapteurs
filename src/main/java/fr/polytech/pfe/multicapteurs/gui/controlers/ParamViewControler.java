package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamViewControler {

    private List<String> requiredArgs;

    public ParamViewControler(){
        this.requiredArgs = new ArrayList<>();
    }

    public ParamViewControler(Library l){
        this.requiredArgs = l.getRequiredArgs();
    }

    public ParamViewControler(Measure m){
        this.requiredArgs = m.getRequiredArgs();
    }

    public List<String> getRequiredArgs(){
        return requiredArgs;
    }

    public List<String> updateArgs(Library l){
        requiredArgs = l.getRequiredArgs();
        return requiredArgs;
    }

    public List<String> updateArgs(Measure m){
        requiredArgs = m.getRequiredArgs();
        return requiredArgs;
    }

}

