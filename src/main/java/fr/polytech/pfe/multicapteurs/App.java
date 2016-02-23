package fr.polytech.pfe.multicapteurs;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.language.Visitable;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.structural.Output;

import java.util.*;

/**
 * Created by fofo on 21/02/16.
 */
public class App implements NamedElement, Visitable {

    private String name;
    private Map<String, Library> loadedLibraries = new LinkedHashMap<>();
    private List <LibraryUse> usedLibraries = new ArrayList<>();
    private Output output;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Map <String, Library> getLoadedLibraries() {
        return loadedLibraries;
    }

    public void setLoadedLibraries(Map <String, Library> loadedLibraries) {
        this.loadedLibraries = loadedLibraries;
    }

    public List<LibraryUse> getUsedLibraries() {
        return usedLibraries;
    }

    public void setUsedLibraries(List<LibraryUse> usedLibraries) {
        this.usedLibraries = usedLibraries;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

}