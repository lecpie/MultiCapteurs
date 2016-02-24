package fr.polytech.pfe.multicapteurs.syntax.config_dsl

import org.codehaus.groovy.control.CompilerConfiguration

class MultiCapteursDSL {
    private GroovyShell shell
    private CompilerConfiguration configuration
    private MultiCapteursMLBinding binding

    MultiCapteursDSL() {
        binding = new MultiCapteursMLBinding()
        binding.setMultiCapteursMLModel(new MultiCapteursMLModel(binding));
        configuration = new CompilerConfiguration()
        configuration.setScriptBaseClass("fr.polytech.pfe.multicapteurs.syntax.config_dsl.MultiCapteursMLBasescript")
        shell = new GroovyShell(configuration)
    }

    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)
        binding.setScript(script)
        script.setBinding(binding)
        script.run()
    }
}
