package fr.polytech.pfe.multicapteurs.gui.tools;

import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;
import fr.polytech.pfe.multicapteurs.syntax.init_dsl.MultiCapteursDefDSL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lecpie on 2/25/16.
 */
public class LibraryLoader {

    public LibraryLoader() {

    }

    public List <Library> loadFolder(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new FileNotFoundException(folderPath);
        }

        List <Library> libraries = new ArrayList<>();

        for (File file : folder.listFiles()) {
            String path = file.getAbsolutePath();
            String[] dotTokens = path.split("\\.");

            if (dotTokens.length == 0) {
                continue;
            }

            String extention = dotTokens[dotTokens.length - 1];

            if (!"groovy".equals(extention)) {
                continue;
            }

            try {
                List<Library> librariesInside = loadFile(path);

                if (librariesInside != null && librariesInside.size() > 0) {
                    libraries.addAll(librariesInside);
                }
            }
            catch (IOException e) {
                System.err.println("Could not load file " + path);
            }
        }

        return libraries;
    }

    public List<Library> loadFile(String path) throws IOException {
        MultiCapteursDefDSL dsl = new MultiCapteursDefDSL();
        dsl.eval(new File(path));

        return dsl.getModel().getLibraries();
    }


    public static void main (String[] args) throws FileNotFoundException {
        LibraryLoader libraryLoader = new LibraryLoader();

        String scriptFolder = "scripts/lib/";

        if (args.length > 0) {
            scriptFolder = args[0];
        }

        List <Library> libraries = libraryLoader.loadFolder(scriptFolder);

        System.out.println("loaded " + libraries.size() + " libraries");

        for (Library library : libraries) {
            System.out.println("lib " + library.getName());

            for (String measureName : library.getMeasures().keySet()) {
                System.out.println("measure " + measureName);
            }
        }
    }
}
