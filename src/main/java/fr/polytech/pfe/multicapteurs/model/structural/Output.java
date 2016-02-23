package fr.polytech.pfe.multicapteurs.model.structural;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.language.Visitable;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by fofo on 21/02/16.
 */
public class Output implements Visitable {

    private String path;
    private Map <String, MeasureUse> printedMeasures ;


    public Output(String path){
        this.path = path;
        this.printedMeasures = new LinkedHashMap<>();
    }

    public Map <String, MeasureUse> getPrintedMeasures() {
        return printedMeasures;
    }

    public void setPrintedMeasures(Map <String, MeasureUse> printedMeasures) {
        this.printedMeasures = printedMeasures;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void addMeasureUse(String label, MeasureUse measureUse){
        printedMeasures.put(label, measureUse);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.output(this);
    }
}
