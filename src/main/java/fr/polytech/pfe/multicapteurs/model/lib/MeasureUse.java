package fr.polytech.pfe.multicapteurs.model.lib;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.language.Expression;
import fr.polytech.pfe.multicapteurs.model.language.Global;
import fr.polytech.pfe.multicapteurs.model.language.Setupable;
import fr.polytech.pfe.multicapteurs.model.language.Updatable;
import fr.polytech.pfe.multicapteurs.model.structural.Frequency;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lecpie on 1/15/16.
 */
public class MeasureUse implements Global, Updatable, Expression, Setupable {

    private String name;
    private Measure measure;
    private LibraryUse libraryUse;
    private Frequency customFrequency;


    private Map <String, String> argsValues = new LinkedHashMap<>();

    public void loadDefaults() {
        for (String arg : measure.getDefaultArgs().keySet()) {
            // Do not override specified arguments
            if (argsValues.containsKey(arg)) continue;

            argsValues.put(arg, measure.getDefaultArgs().get(arg));
        }
    }

    @Override
    public void setup(Visitor visitor) {
        visitor.setup(this);
    }

    @Override
    public void global(Visitor visitor) {
        visitor.global(this);
    }

    @Override
    public void update(Visitor visitor) {
        visitor.update(this);
    }

    @Override
    public void expression(Visitor visitor) {
        visitor.expression(this);
    }

    @Override
    public Type getType() {
        return measure.getType();
    }

    public LibraryUse getLibraryUse() {
        return libraryUse;
    }

    public void setLibraryUse(LibraryUse libraryUse) {
        this.libraryUse = libraryUse;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Map<String, String> getArgsValues() {
        return argsValues;
    }

    public void setArgsValues(Map<String, String> argsValues) {
        this.argsValues = argsValues;
    }

    public Frequency getCustomFrequency() {
        return customFrequency;
    }

    public void setCustomFrequency(Frequency customFrequency) {
        this.customFrequency = customFrequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
