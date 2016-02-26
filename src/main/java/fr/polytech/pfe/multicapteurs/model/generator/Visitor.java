package fr.polytech.pfe.multicapteurs.model.generator;


import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;
import fr.polytech.pfe.multicapteurs.model.structural.*;
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.*;

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

	public abstract void expression(AsapCapture      captureMethod);
	public abstract void expression(PeriodicCapture  captureMethod);
	public abstract void expression(DistancedCapture captureMethod);

	public abstract void global(CaptureMethod    captureMethod);
	public abstract void global(TriggeredCapture captureMethod);
	public abstract void global(AsapCapture      captureMethod);
	public abstract void global(PeriodicCapture  captureMethod);
	public abstract void global(DistancedCapture captureMethod);

	public abstract void update(TriggeredCapture captureMethod);

	public T getResult() {
		return result;
	}
}

