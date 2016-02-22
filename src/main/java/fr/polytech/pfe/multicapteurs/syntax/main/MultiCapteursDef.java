package fr.polytech.pfe.multicapteurs.syntax.main;

import fr.polytech.pfe.multicapteurs.syntax.init_dsl.MultiCapteursDefDSL;

import java.io.File;
import java.io.IOException;

/**
 * Created by Louis on 21/02/2016.
 */
public class MultiCapteursDef {
    public static void main(String[] args) throws IOException {
        System.out.println("HEY\n");
        MultiCapteursDefDSL dsl = new MultiCapteursDefDSL();
        if(args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
        }
    }
}
