package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


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
    private BoxLayout layout;

    public SensorManagementView(SensorManagementControler controler){
        this.controler = controler;

        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);

        initTabPanned();
        initParamView();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initTabPanned(){
        sensorMenu = new JTabbedPane();
        addOnglet = new JPanel();
        addOnglet = addComboBox(addOnglet);
        defaultOnglet = new JPanel();
        defaultOnglet = addComboBox(defaultOnglet);

        sensorMenu.addTab("+", addOnglet);
        sensorMenu.addTab("defaultPanInit", defaultOnglet);
        sensorMenu.setSelectedComponent(defaultOnglet);

        sensorMenu.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sensorMenu.getSelectedIndex() == 0){
                    JPanel newPan = new JPanel();
                    newPan = addComboBox(newPan);
                    newPan.setName("newPan"+sensorMenu.getTabCount());
                    //addBlockToPanel(newPan);
                    sensorMenu.add(newPan,sensorMenu.getTabCount());
                }
            }
        });
        sensorMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(sensorMenu);
    }
    public void addBlockToPanel(JPanel panel){

    }
    public JPanel addComboBox(JPanel panel){

        panel.add(new JLabel("Library"));
        //TODO : LIst toutes les bibliothèques
        String[] modes = {"DHT","GPS"};
        JComboBox mode = new JComboBox(modes);
        panel.add(mode);
        return panel;
    }

    public void initParamView(){
        paramView = new ParamView(new ParamViewControler());
        paramView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(paramView);
    }
}
