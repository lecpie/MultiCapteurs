package fr.polytech.pfe.multicapteurs.syntax.init_dsl;

import fr.polytech.pfe.multicapteurs.model.structural.Type;
import fr.polytech.pfe.multicapteurs.syntax.main.MultiCapteursDef;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;

public class MultiCapteursDefDSL {

    private GroovyShell shell;
    private CompilerConfiguration configuration;
    private MultiCapteursDefBinding binding;
    private MultiCapteursDefModel model;

    public MultiCapteursDefDSL() {
        binding = new MultiCapteursDefBinding();
        binding.setInitialisationModel(model = new MultiCapteursDefModel(binding));
        configuration = new CompilerConfiguration();
        configuration.setScriptBaseClass("fr.polytech.pfe.multicapteurs.syntax.init_dsl.MultiCapteursDefBaseScript");
        shell = new GroovyShell(configuration);

        binding.setVariable("real",    Type.REAL)
        binding.setVariable("integer", Type.INTEGER)
        binding.setVariable("digital", Type.DIGITAL)
    }

    public void eval(File scriptFile) throws IOException {
        Script script = shell.parse(scriptFile);
        binding.setScript(script);
        script.setBinding(binding);
        script.run();
    }

    public MultiCapteursDefModel getModel() {
        return model;
    }
}
