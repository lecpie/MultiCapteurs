package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.Output;
import fr.polytech.pfe.multicapteurs.model.structural.Time;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementControler {
    //TODO: bind with measureUses instead of static measureUSes
    private List<MeasureUse> measureUses;

    public MeasureManagementControler() {
        this.measureUses = new ArrayList<>();
    }

    public List<MeasureUse> getMeasureUses(){
        return measureUses;
    }


}
