package fr.polytech.pfe.multicapteurs.gui.views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementView extends JPanel {

    //TODO: Dropdown presets
    //TODO: Dropdown lib
    //TODO: Param List (ParamView)

    public SensorManagementView(){
        this.add(new JLabel("sensorManagement"));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setVisible(true);
    }
}
