package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementView extends JPanel {
//TODO : init measure avec measue uses
    //TODO: Measure preset
    //TODO: Measure setup
    //TODO: Param List (ParamView)

    private JTabbedPane measureMenu;
    private JPanel addOnglet;
    private MeasureInit measureInit;
    private ParamView paramView;
    private MeasureManagementControler controler;

    public MeasureManagementView(MeasureManagementControler controler){
        this.controler = controler;
       // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new GridLayout(2,1));
        initMeasureTabPanned();
        initParamView();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initMeasureTabPanned(){
        measureMenu = new JTabbedPane();
        addOnglet = new JPanel();
        measureInit = new MeasureInit();
        measureMenu.addTab("+", addOnglet);
        measureMenu.addTab("temperature", measureInit);
        measureMenu.setSelectedComponent(measureInit);

        measureMenu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(measureMenu.getSelectedIndex() == 0){
                    //newPan.setName("newPan"+measureMenu.getTabCount());
                    MeasureInit newMeasure = new MeasureInit();
                    //TODO: link with measureuse name
                    newMeasure.setName("TODO");
                    measureMenu.add(newMeasure, measureMenu.getTabCount());
                }
            }
        });
        this.add(measureMenu);
    }

    public void initParamView(){
        paramView = new ParamView(new ParamViewControler());
        this.add(paramView);
    }
}
