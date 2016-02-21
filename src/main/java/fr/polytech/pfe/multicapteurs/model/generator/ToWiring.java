package fr.polytech.pfe.multicapteurs.model.generator;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {


	@Override
	public void include(Library library) {

	}

	@Override
	public void setup(LibraryUse libraryUse) {

	}

	@Override
	public void global(LibraryUse libraryUse) {

	}

	@Override
	public void setup(MeasureUse measureUse) {

	}

	@Override
	public void global(MeasureUse measureUse) {

	}

	@Override
	public void update(MeasureUse measureUse) {

	}

	@Override
	public void expression(MeasureUse measureUse) {

	}
}
