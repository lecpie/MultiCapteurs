package fr.polytech.pfe.multicapteurs.gui.views.pannels;

import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import fr.polytech.pfe.multicapteurs.gui.tools.AddLib;

import javax.swing.*;

/**
 * Created by Louis on 26/02/2016.
 */
public class InputPannel extends JTabbedPane{

    LibControler controler;
    private AddLib addLib;

    public InputPannel(LibControler controler){
        this.controler = controler;

        addLib = new AddLib();
        this.addTab("+", addLib);

    }

    public LibControler getControler() {
        return controler;
    }
}
