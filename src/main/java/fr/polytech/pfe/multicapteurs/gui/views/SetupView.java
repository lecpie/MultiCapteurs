package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;

import javax.swing.*;
import java.util.*;

/**
 * Created by Louis on 25/02/2016.
 */
public class SetupView extends JPanel {
    private LinkedHashMap<InputComponent, List<InputComponent>> settings;
    private JPanel sensorManagementView;
    private JPanel measureManagementView;

    private AppControler controler;

    public SetupView(AppControler controler){
        settings = new LinkedHashMap<>();
        this.controler = controler;
        initSensorManagementView(controler);
        initMeasurManagementView();
    }

    public List<InputComponent> addLibComponent(InputComponent l){
        return settings.put(l, new ArrayList<>());
    }

    public List<InputComponent> removeComponent(InputComponent l){
        return settings.remove(l);
    }

    public void addMeasureComponent(InputComponent l, InputComponent m){
        List<InputComponent> tmp = settings.get(l);
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

    public LinkedHashMap<InputComponent, List<InputComponent>> getSettings() {
        return settings;
    }

    public void setSettings(LinkedHashMap<InputComponent, List<InputComponent>> settings) {
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
