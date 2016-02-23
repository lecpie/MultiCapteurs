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

    private JTabbedPane measureMenu;
    private ParamView paramView;

    public MeasureManagementView(){
       // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(2,1));
        initMeasureMenu();
        initParamView();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setVisible(true);
    }

    public void initMeasureMenu(){
        measureMenu = new JTabbedPane();
        measureMenu.addTab("temperature", new MeasureInit());
        measureMenu.addTab("humidity", new MeasureInit());
        this.add(measureMenu);
    }

    public void initParamView(){

    }
}
