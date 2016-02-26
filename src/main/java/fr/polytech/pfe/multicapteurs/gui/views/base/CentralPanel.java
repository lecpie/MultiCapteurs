package fr.polytech.pfe.multicapteurs.gui.views.base;

import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import fr.polytech.pfe.multicapteurs.gui.views.pannels.InputPannel;
import fr.polytech.pfe.multicapteurs.gui.views.pannels.LibsPannel;
import fr.polytech.pfe.multicapteurs.gui.views.pannels.MeasuresPannel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 26/02/2016.
 */
public class CentralPanel extends JPanel {

    private LibControler controler;
    private InputPannel libsPannel, measuresPannel;
    private GridLayout layout;

    public CentralPanel(LibControler controler){
        this.controler = controler;
        libsPannel = new LibsPannel(controler);
        measuresPannel = new MeasuresPannel(controler);

        layout = new GridLayout(1,2);
        this.setLayout(layout);

        this.add(libsPannel);
        this.add(measuresPannel);



    }

}
