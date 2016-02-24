package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamViewControler {

    private List<String> params;

    public ParamViewControler(){
    }

    public ParamViewControler(List <String> params){
        this.params = params;
    }

    public List<String> getReqParams(){
        List<String> params = new ArrayList<>();
        params.add("dht_pin");
        params.add("dht_type");
        return params;
    }
}
