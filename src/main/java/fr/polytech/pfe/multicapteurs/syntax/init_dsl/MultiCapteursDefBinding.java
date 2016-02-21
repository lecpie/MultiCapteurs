package fr.polytech.pfe.multicapteurs.syntax.init_dsl;

import fr.polytech.pfe.multicapteurs.syntax.main.MultiCapteursDef;
import groovy.lang.Binding;
import groovy.lang.Script;

import java.util.Map;

public class MultiCapteursDefBinding extends Binding {

    private Script script;
    private MultiCapteursDefModel model;

    public MultiCapteursDefBinding() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public MultiCapteursDefBinding(Map variables) {
        super(variables);
    }

    public MultiCapteursDefBinding(Script script) {
        super();
        this.script = script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setInitialisationModel(MultiCapteursDefModel model) {
        this.model = model;
    }

    public Object getVariable(String name) {
        return super.getVariable(name);
    }

    public void setVariable(String name, Object value) {
        super.setVariable(name, value);
    }

    public MultiCapteursDefModel getInitialisationModel() {
        return this.model;
    }
}
