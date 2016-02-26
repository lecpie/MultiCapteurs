package fr.polytech.pfe.multicapteurs.syntax.config_dsl

import fr.polytech.pfe.multicapteurs.model.lib.Library
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse
import fr.polytech.pfe.multicapteurs.model.structural.Period
import fr.polytech.pfe.multicapteurs.model.structural.Time
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.CaptureMethod
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.DistancedCapture
import fr.polytech.pfe.multicapteurs.model.structural.capturemethods.PeriodicCapture

abstract class MultiCapteursMLBasescript extends Script {

    private LibraryUse current = null

    def importlib(String path) {
        ((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel().importlib(path)
    }

    def sensor(String libName) {
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

    def measure(String measureName) {
        MultiCapteursMLModel model = ((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel()

        MeasureUse measureUse = new MeasureUse();
        measureUse.setName(measureName)
        measureUse.setLibraryUse(current)
        measureUse.setMeasure(current.getLibrary().getMeasures().get(measureName))

        Map<String, String> args = measureUse.getArgsValues()

        model.getUsedMeasure().add(measureUse)

        def argsclosure
        def captureClosure
        def nameClosure

        String previousname = measureName
        binding.setVariable(previousname, measureUse);

        [captured: captureClosure = {
            CaptureMethod captureMethod ->
                measureUse.setCaptureMethod(captureMethod)

                [every: {
                    what ->
                        if (captureMethod instanceof PeriodicCapture) {
                            measureUse.setCaptureMethod(new PeriodicCapture(what));
                        }
                        else if (captureMethod instanceof DistancedCapture) {
                            System.err.println("lol")
                            measureUse.setCaptureMethod(new DistancedCapture(what))
                        }

                        [with: argsclosure, named: nameClosure]
                },
                        with: argsclosure,
                        named: nameClosure
                ]

        }, named: nameClosure = {
            String name ->
                measureUse.setName(name)
                binding.getVariables().remove(previousname)
                previousname = name
                binding.setVariable(previousname, measureUse)

                [captured : captureClosure, with: argsclosure]
        },
         with : argsclosure = {
             String key ->
                 [valued: {
                     String val ->
                         args.put(key, val)
                         [and: argsclosure]
                 }]


         }]
    }


    // export name
    def export(String name) {
        println(((MultiCapteursMLBinding) this.getBinding()).getMultiCapteursMLModel().generateCode(name).toString())
    }


}
