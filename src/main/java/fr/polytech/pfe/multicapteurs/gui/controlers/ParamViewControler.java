package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamViewControler {

    Library selectedLib;
   // List<MeasureSetupComponent> msp;

    public Library getSelectedLib() {
        return selectedLib;
    }

    public void setSelectedLib(Library selectedLib) {
        this.selectedLib = selectedLib;
    }

    public List<String> getReqParams(){
        List<String> params = new ArrayList<>();
        /*
        for(String arg : selectedLib.getRequiredArgs()){
            params.add(arg.toString());
        }*/
        params.add("dht_pin");
        params.add("dht_type");

        return params;
    }
}
