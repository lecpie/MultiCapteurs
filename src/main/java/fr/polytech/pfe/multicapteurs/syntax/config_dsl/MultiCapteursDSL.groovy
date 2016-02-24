package fr.polytech.pfe.multicapteurs.syntax.config_dsl

import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse
import fr.polytech.pfe.multicapteurs.model.structural.Period
import fr.polytech.pfe.multicapteurs.model.structural.Time
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.AsapCapture
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.PeriodicCapture
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

        Number.metaClass {
            getMs { -> new Period(delegate, Time.MS)   }
            getS  { -> new Period(delegate, Time.SEC)  }
            //{ getM  -> new Period(delegate, Time.MIN)  }
            //{ getH  -> new Period(delegate, Time.HOUR) }
        }

        //binding.setVariable("ms", Duration(1, TimeUnit.ms))


        /*
        binding.setVariable("s",  Time.SEC)
        binding.setVariable("m",  Time.MIN)
        binding.setVariable("h",  Time.HOUR)
        binding.setVariable("ms", Time.MS)
        */

        binding.setVariable("periodically", new PeriodicCapture())
        binding.setVariable("asap",         new AsapCapture())
    }

    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)
        binding.setScript(script)
        script.setBinding(binding)
        script.run()
    }
}
