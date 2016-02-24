package fr.polytech.pfe.multicapteurs.syntax.config_dsl

import fr.polytech.pfe.multicapteurs.model.lib.Library
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse
import fr.polytech.pfe.multicapteurs.model.structural.Period
import fr.polytech.pfe.multicapteurs.model.structural.Time

abstract class MultiCapteursMLBasescript extends Script {

    private LibraryUse current = null

    def importlib(String path) {
        ((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel().importlib(path)
    }

    def uselib(String libName) {
        MultiCapteursMLModel model = ((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel()

        LibraryUse libraryUse = new LibraryUse();
        Library usedLibrary = model.getLoaded_librairies().get(libName)
        libraryUse.setLibrary(usedLibrary)
        Map<String, String> args = usedLibrary.getDefaultArgs()

        model.getUsedLibraries().add(libraryUse)
        current = libraryUse

        def closure
        [with: closure = {
            String key ->
                [valued: {
                    String val ->
                        args.put(key, val)
                        [and: closure]
                }]
        }]
    }

    def sensor(String measureName) {
        MultiCapteursMLModel model = ((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel()

        Map<String, String> args = new LinkedHashMap<String, String>()

        MeasureUse measureUse = new MeasureUse();
        measureUse.setName(measureName)
        measureUse.setLibraryUse(current)
        measureUse.setMeasure(current.getLibrary().getMeasures().get(measureName))

        model.getUsedMeasure().add(measureUse)

        def nameclosure
        def argsclosure

        String previousname = measureName
        binding.setVariable(previousname, measureUse)

        [named : {
            String name ->
                measureUse.setName(name)
                binding.getVariables().remove(previousname)
                previousname = name
                binding.setVariable(previousname, measureUse)
                [freq: freqClosure]
        }, freq: freqClosure = {
            freqVal ->
                measureUse.setCustomFrequency(new Period(freqVal, Time.SEC))
                [rate: rateClosure]
        }, rate: rateClosure = {
            String unit ->
                measureUse.setTimeUnit(unit)
        }]
    }

    // export name
    def export(String name) {
        println(((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel().generateCode(name).toString())
    }


}
