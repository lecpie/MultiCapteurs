package fr.polytech.pfe.multicapteurs.gui.views.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 26/02/2016.
 */
public class GridBagUtils {

    public static void addComponent(JPanel to, GridBagConstraints constraints, Component comp, boolean newLine){
        if(newLine) {
            constraints.gridx = 0;
            constraints.gridy++;

        } else {
            constraints.gridx++;
        }
        to.add(comp, constraints);
    }
}
