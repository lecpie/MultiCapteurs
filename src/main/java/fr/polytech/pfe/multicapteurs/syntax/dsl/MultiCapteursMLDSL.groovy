package fr.polytech.pfe.multicapteurs.syntax.dsl

import org.codehaus.groovy.control.CompilerConfiguration

class MultiCapteursMLDSL {
    private GroovyShell shell
    private CompilerConfiguration configuration
    private MultiCapteursMLBinding binding

    MultiCapteursMLDSL() {
        binding = new MultiCapteursMLBinding()
        binding.setMultiCapteursMLModel(new MultiCapteursMLModel(binding));
        configuration = new CompilerConfiguration()
        configuration.setScriptBaseClass("main.groovy.groovuinoml.dsl.GroovuinoMLBasescript")
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
