package fr.polytech.pfe.multicapteurs.gui.views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementView extends JPanel {

    //TODO: Measure preset
    //TODO: Measure setup
    //TODO: Param List (ParamView)

    public MeasureManagementView(){
        this.add(new JLabel("measure management view"));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setVisible(true);
    }
}
