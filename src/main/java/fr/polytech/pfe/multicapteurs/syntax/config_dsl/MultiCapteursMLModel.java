package fr.polytech.pfe.multicapteurs.syntax.config_dsl;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.generator.ToWiring;
import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.syntax.init_dsl.MultiCapteursDefDSL;
import groovy.lang.Binding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lecpie on 2/21/16.
 */
public class MultiCapteursMLModel {


    private Map<String, Library> loaded_librairies = new HashMap<>();
    private Map<String, Measure> loaded_measures = new HashMap<>();
    private List<LibraryUse> usedLibraries;
    private List<MeasureUse> usedMeasure;
    private Binding binding;

    public Map<String, Measure> getLoaded_measures() {
        return loaded_measures;
    }

    public void setLoaded_measures(Map<String, Measure> loaded_measures) {
        this.loaded_measures = loaded_measures;
    }
    public Map<String, Library> getLoaded_librairies() {
        return loaded_librairies;
    }

    public void setLoaded_librairies(Map<String, Library> loaded_librairies) {
        this.loaded_librairies = loaded_librairies;
    }

    public List<LibraryUse> getUsedLibraries() {
        return usedLibraries;
    }

    public void setUsedLibraries(List<LibraryUse> usedLibraries) {
        this.usedLibraries = usedLibraries;
    }

    public List<MeasureUse> getUsedMeasure() {
        return usedMeasure;
    }

    public void setUsedMeasure(List<MeasureUse> usedMeasure) {
        this.usedMeasure = usedMeasure;
    }


    public MultiCapteursMLModel(Binding binding) {
        this.usedLibraries = new ArrayList<LibraryUse>();
        this.usedMeasure = new ArrayList<MeasureUse>();
        this.binding = binding;
    }

    public void createLibraryUse(String libName, Map<String, String> argsValues) {
        LibraryUse libraryUse = new LibraryUse();
        libraryUse.setArgsValues(argsValues);
        if (loaded_librairies.get(libName) != null) {
            libraryUse.setLibrary(loaded_librairies.get(libName));
            this.usedLibraries.add(libraryUse);
        }
    }



    public void createMeasureUse(String libUseName, String measureName, Map<String, String> argsValues) {
        MeasureUse measureUse = new MeasureUse();
        for(LibraryUse libUse : usedLibraries){
            Library currentLib = libUse.getLibrary();
            if(currentLib.getName().equals(libUseName)){
                measureUse.setLibraryUse(libUse);
                Measure currentMeasure = currentLib.getMeasures().get(measureName);
                if(currentMeasure != null) {
                    measureUse.setMeasure(currentMeasure);
                    measureUse.setArgsValues(argsValues);
                    this.usedMeasure.add(measureUse);
                }
            }

        }

    }

    @SuppressWarnings("rawtypes")
    public Object generateCode(String appName) {
        App app = new App();
        app.setName(appName);
        app.setUsedLibraries(usedLibraries);

        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);

        return codeGenerator.getResult();
    }



    public void importlib(String path) throws IOException {
        MultiCapteursDefDSL initdsl = new MultiCapteursDefDSL();

        initdsl.eval(new File(path));

        for (Library lib : initdsl.getModel().getLibraries()) {
            loaded_librairies.put(lib.getName(), lib);
        }
        for (Measure measures : initdsl.getModel().getMeasures()) {
            loaded_measures.put(measures.getName(), measures);
        }
    }
}
