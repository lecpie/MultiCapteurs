package fr.polytech.pfe.multicapteurs.model.language;


import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

public interface Setupable {
    void setup(Visitor visitor);
}
