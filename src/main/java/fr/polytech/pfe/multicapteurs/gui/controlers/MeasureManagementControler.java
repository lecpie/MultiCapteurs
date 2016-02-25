package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;

import java.util.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementControler {

    private Library lib;
    private Measure currentMeasure;

    public MeasureManagementControler() {

    }

    public MeasureManagementControler(Library lib){
        this.lib = lib;
    }

    /**
     * For combobox display
     */
    public List<String> getMeasureNames(){
        List<String> measureNames = new ArrayList<>();
        for(String name : lib.getMeasures().keySet()){
            measureNames.add(name);
        }
        return measureNames;
    }

    /**
     *
     */
    public Measure setCurrentMeasure(String measureName){
        for(Measure m : lib.getMeasures().values()){
           if(m.getName().equals(measureName)){
               System.out.println(m.getName());
               setCurrentMeasure(m);
               return m;
           }
        }
        return null;
    }

    public Library getLib() {
        return lib;
    }

    public void setLib(Library lib) {
        this.lib = lib;
    }

    public Measure getCurrentMeasure() {
        return currentMeasure;
    }

    public void setCurrentMeasure(Measure currentMeasure) {
        this.currentMeasure = currentMeasure;
    }

    public static void main(String[] args) {


    }
}
