package fr.polytech.pfe.multicapteurs.model.generator;


import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;

public abstract class Visitor<T> {

	public abstract void include (Library library);

	public abstract void setup(LibraryUse libraryUse);
	public abstract void global(LibraryUse libraryUse);

	public abstract void setup      (MeasureUse measureUse);
	public abstract void global     (MeasureUse measureUse);
	public abstract void update     (MeasureUse measureUse);
	public abstract void expression (MeasureUse measureUse);
}

