package fr.polytech.pfe.multicapteurs.model.generator;


import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.Output;

public abstract class Visitor<T> {

	protected T result;

	public abstract void include (Library library);
	public abstract void visit(App app);
	public abstract void setup(LibraryUse libraryUse);
	public abstract void global(LibraryUse libraryUse);
	public abstract void output(Output output);
	public abstract void setup      (MeasureUse measureUse);
	public abstract void global     (MeasureUse measureUse);
	public abstract void update     (MeasureUse measureUse);
	public abstract void expression (MeasureUse measureUse);
	public T getResult() {
		return result;
	}
}

