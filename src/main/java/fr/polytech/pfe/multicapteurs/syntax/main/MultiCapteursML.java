package fr.polytech.pfe.multicapteurs.syntax.main;

import fr.polytech.pfe.multicapteurs.syntax.config_dsl.MultiCapteursDSL;

import java.io.File;

/**
 * Created by lecpie on 2/21/16.
 */
public class MultiCapteursML {
    public static void main(String[] args) {
        MultiCapteursDSL dsl = new MultiCapteursDSL();
        if(args.length > 0) {
            dsl.eval(new File(args[0]));
        } else {
            System.out.println("/!\\ Missing arg: Please specify the path to a Groovy script file to execute");
        }
    }
}