package fr.polytech.pfe.multicapteurs.syntax.config_dsl;

import groovy.lang.Binding;
import groovy.lang.Script;

import java.util.Map;

/**
 * Created by lecpie on 2/21/16.
 */
public class MultiCapteursMLBinding extends Binding {

    private Script script;

    private MultiCapteursMLModel model;

    public MultiCapteursMLBinding() {
        super();
    }

    @SuppressWarnings("rawtypes")
    public MultiCapteursMLBinding(Map variables) {
        super(variables);
    }

    public MultiCapteursMLBinding(Script script) {
        super();
        this.script = script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public void setMultiCapteursMLModel(MultiCapteursMLModel model) {
        this.model = model;
    }

    public MultiCapteursMLModel getMultiCapteursMLModel() {
        return this.model;
    }
}
