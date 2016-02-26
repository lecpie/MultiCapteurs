package fr.polytech.pfe.multicapteurs.gui.views.inputs;

import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Louis on 26/02/2016.
 */
public class SuperView extends JPanel {

    protected LibControler controler;

    protected InputView args;
    protected GridBagLayout layout;
    protected GridBagConstraints c;

    public SuperView(LibControler controler){
        this.controler = controler;
        args = new InputView();
    }

}
