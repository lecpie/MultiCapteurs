package fr.polytech.pfe.multicapteurs.model.language;


import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

public interface Expression extends Typed {
    void expression(Visitor visitor);
}
