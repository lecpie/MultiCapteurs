package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.gui.components.LibComponent;
import fr.polytech.pfe.multicapteurs.gui.components.MeasureComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.model.lib.Measure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Louis on 25/02/2016.
 */
public class SetupView extends JPanel {
    private Map<LibComponent, List<MeasureComponent>> settings;
    private JPanel sensorManagementView;
    private JPanel measureManagementView;

    private AppControler controler;



    public SetupView(AppControler controler){
        settings = new HashMap<>();
        this.controler = controler;
        initSensorManagementView(controler);
        initMeasurManagementView();
    }

    public List<MeasureComponent> addLibComponent(LibComponent l){
        return settings.put(l, new ArrayList<>());
    }

    public List<MeasureComponent> removeComponent(LibComponent l){
        return settings.remove(l);
    }

    public void addMeasureComponent(LibComponent l, MeasureComponent m){
        List<MeasureComponent> tmp = settings.get(l);
        tmp.add(m);
        settings.put(l,tmp);
    }

    private void initSensorManagementView(AppControler controler){
        sensorManagementView = new SensorManagementView(controler.getSmc());
        this.add(sensorManagementView);
    }

    private void initMeasurManagementView(){
        measureManagementView = new MeasureManagementView(new MeasureManagementControler());
        this.add(measureManagementView);
    }

    public Map<LibComponent, List<MeasureComponent>> getSettings() {
        return settings;
    }

    public void setSettings(Map<LibComponent, List<MeasureComponent>> settings) {
        this.settings = settings;
    }

    public JPanel getSensorManagementView() {
        return sensorManagementView;
    }

    public void setSensorManagementView(JPanel sensorManagementView) {
        this.sensorManagementView = sensorManagementView;
    }

    public JPanel getMeasureManagementView() {
        return measureManagementView;
    }

    public void setMeasureManagementView(JPanel measureManagementView) {
        this.measureManagementView = measureManagementView;
    }

    public AppControler getControler() {
        return controler;
    }

    public void setControler(AppControler controler) {
        this.controler = controler;
    }

}
