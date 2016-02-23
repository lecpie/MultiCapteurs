package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementView extends JPanel {

    //TODO: Dropdown presets

    private SensorManagementControler controler;

    private JTabbedPane sensorMenu;
    private ParamView paramView;
    private JPanel addOnglet;
    private JPanel defaultOnglet;
    private GridBagLayout layout;
    private GridBagConstraints c;


    public SensorManagementView(SensorManagementControler controler){
        this.controler = controler;

        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1,1,1,1);
        this.setLayout(layout);

        initTabPanned();
        initParamView();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setVisible(true);
    }

    public void initTabPanned(){
        sensorMenu = new JTabbedPane();
        addOnglet = new JPanel();
        defaultOnglet = new JPanel();
        sensorMenu.addTab("+", addOnglet);
        sensorMenu.addTab("defaultPanInit", defaultOnglet);
        sensorMenu.setSelectedComponent(defaultOnglet);

        sensorMenu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sensorMenu.getSelectedIndex() == 0){
                    JPanel newPan = new JPanel();
                    newPan.setName("newPan"+sensorMenu.getTabCount());
                    addBlockToPanel(newPan);
                    sensorMenu.add(newPan,sensorMenu.getTabCount());
                }
            }
        });
        this.add(sensorMenu, c);
    }
    public void addBlockToPanel(JPanel panel){

    }

    public void initParamView(){
        paramView = new ParamView(new ParamViewControler());
        this.add(paramView, c);
    }
}
