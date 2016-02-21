package fr.polytech.pfe.multicapteurs.model.lib;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.language.Global;
import fr.polytech.pfe.multicapteurs.model.language.Setupable;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lecpie on 1/15/16.
 */
public class LibraryUse implements Global, Setupable {
    private Library library;
    private Map <String, String> argsValues = new LinkedHashMap<>();

    public void loadDefaults() {
        for (String arg : library.getDefaultArgs().keySet()) {
            // Do not override specified arguments
            if (argsValues.containsKey(arg)) continue;

            argsValues.put(arg, library.getDefaultArgs().get(arg));
        }
    }

    public Map<String, String> getArgsValues() {
        return argsValues;
    }

    public void setArgsValues(Map<String, String> argsValues) {
        this.argsValues = argsValues;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public void global(Visitor visitor) {
        visitor.global(this);
    }

    @Override
    public void setup(Visitor visitor) {
        visitor.setup(this);
    }
}
