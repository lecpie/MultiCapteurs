package fr.polytech.pfe.multicapteurs.syntax.config_dsl

import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse
import fr.polytech.pfe.multicapteurs.model.structural.Period
import fr.polytech.pfe.multicapteurs.model.structural.Time
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.AsapCapture
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.DistancedCapture
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.MetadataCapture
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
            getMs  { -> new Period(delegate, Time.MS)   }
            getS   { -> new Period(delegate, Time.SEC)  }
            getMin { -> new Period(delegate, Time.MIN)  }
            getH   { -> new Period(delegate, Time.HOUR) }
            getM   { -> delegate                        }
            getMM  { -> delegate * 1000                 }
        }


        binding.setVariable("periodically", new PeriodicCapture())
        binding.setVariable("asap",         new AsapCapture())
        binding.setVariable("meta",         new MetadataCapture())
        binding.setVariable("distanced",    new DistancedCapture())
    }

    void eval(File scriptFile) {
        Script script = shell.parse(scriptFile)
        binding.setScript(script)
        script.setBinding(binding)
        script.run()
    }
}
