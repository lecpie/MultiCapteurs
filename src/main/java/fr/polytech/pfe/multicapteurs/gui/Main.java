package fr.polytech.pfe.multicapteurs.gui;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.views.AppView;

/**
 * Created by Louis on 22/02/2016.
 */
public class Main {
    public static void main(String[] args) {
        AppControler controler = new AppControler();
        AppView appView = new AppView(controler);
    }
}
