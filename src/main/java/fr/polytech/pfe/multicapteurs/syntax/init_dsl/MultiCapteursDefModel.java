package fr.polytech.pfe.multicapteurs.syntax.init_dsl;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.syntax.main.MultiCapteursDef;
import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.List;

public class MultiCapteursDefModel {


    private List<Library> libraries;
    private List <Measure> measures;
    private MultiCapteursDefBinding binding;

    public MultiCapteursDefModel(MultiCapteursDefBinding binding){
        this.libraries = new ArrayList<>();
        this.measures = new ArrayList<>();
        this.binding = binding;
    }

    public void createLibrary(Library library){
        this.libraries.add(library);
    }

    public void createMeasure(Measure measure){
        this.measures.add(measure);
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}
