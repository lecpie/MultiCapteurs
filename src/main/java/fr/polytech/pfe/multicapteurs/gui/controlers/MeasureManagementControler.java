package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.Output;
import fr.polytech.pfe.multicapteurs.model.structural.Time;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementControler {

    private Library lib;
    private Measure currentMeasure;
    private ParamViewControler measurUseArgs;

    public MeasureManagementControler(){

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

    public ParamViewControler getMeasurUseArgs() {
        return measurUseArgs;
    }

    public void setMeasurUseArgs(ParamViewControler measurUseArgs) {
        this.measurUseArgs = measurUseArgs;
    }

    public static void main(String[] args) {


    }
}
