package fr.polytech.pfe.multicapteurs.gui.controlers;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.generator.ToWiring;
import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import groovyjarjarantlr.CodeGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppControler {

    //eval du script + get model pour avoir les lib
    private App app;

    public AppControler(){
        this.app = new App();
    }

    public String  generateCode(Library lib, LibraryUse libUse, MeasureUse measureUse){
        app = new App();

        List<Library> libs = new ArrayList<>();
        libs.add(lib);

        Visitor codeGenerator = new ToWiring();
        app.accept(codeGenerator);

        return codeGenerator.getResult().toString();

    }
/*
    public List<String> getLibNames(){
        List<String> libNames = new ArrayList<>();
        for(Library l : libraries){
            libNames.add(l.getName());
        }
        return libNames;
    }*/

}
