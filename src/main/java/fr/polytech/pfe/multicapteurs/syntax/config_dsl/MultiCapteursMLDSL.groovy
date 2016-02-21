package fr.polytech.pfe.multicapteurs.syntax.config_dsl

import org.codehaus.groovy.control.CompilerConfiguration

class MultiCapteursMLDSL {
    private GroovyShell shell
    private CompilerConfiguration configuration
    private MultiCapteursMLBinding binding

    MultiCapteursMLDSL() {
        binding = new MultiCapteursMLBinding()
        binding.setMultiCapteursMLModel(new MultiCapteursMLModel(binding));
        configuration = new CompilerConfiguration()
        configuration.setScriptBaseClass("main.java.fr.polytech.pfe.multicapteurs.syntax.config_dsl.MultiCapteursMLBasescript")
        shell = new GroovyShell(configuration)

        binding.setVariable("high", SIGNAL.HIGH)
        binding.setVariable("low", SIGNAL.LOW)
    }

    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)
        binding.setScript(script)
        script.setBinding(binding)
        script.run()
    }
}
