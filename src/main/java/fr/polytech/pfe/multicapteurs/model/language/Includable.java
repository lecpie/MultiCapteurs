package fr.polytech.pfe.multicapteurs.model.language;


import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

/**
 * Created by lecpie on 1/17/16.
 */
public interface Includable {
    void include(Visitor visitor);
}
