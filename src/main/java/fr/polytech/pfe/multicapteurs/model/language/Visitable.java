package fr.polytech.pfe.multicapteurs.model.language;


import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

public interface Visitable {

	public void accept(Visitor visitor);

}
