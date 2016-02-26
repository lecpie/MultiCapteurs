package fr.polytech.pfe.multicapteurs.gui.trash;

import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementView extends JPanel implements ActionListener,MouseListener,FocusListener, KeyListener, ChangeListener {
    //TODO : init measure avec measue uses
    //TODO: Measure preset
    //TODO: Measure setup
    //TODO: Param List (ParamView)

    private LibControler controler;
    private JTabbedPane measureMenu;
    private InputComponent addOnglet;
    private ArrayList<String> measuresTypes;
    private ArrayList<String> capturesTypes;

    private List<InputComponent> measures;

    public MeasureManagementView(LibControler controler){
        this.controler = controler;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.measuresTypes = new ArrayList<>();
        this.capturesTypes = new ArrayList<>(Arrays.asList("Frequency","ASAP","META"));
        this.measures = new ArrayList<>();
        initMeasureTabPanned();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void initMeasureTabPanned() {
        measureMenu = new JTabbedPane();
        addOnglet = new InputComponent();
        addOnglet.setComponentName("+");
        addOnglet.setBackground(Color.gray);
        addOnglet.setEnabled(false);
        measureMenu.addTab(addOnglet.getComponentName(), addOnglet);
        addOnglet.setTabId(measureMenu.getTabCount());
        measures.add(addOnglet);
        measureMenu.addChangeListener(this);
        measureMenu.addMouseListener(this);
        measureMenu.addKeyListener(this);
        this.add(measureMenu);
    }

    public JTabbedPane getMeasureMenu() {
        return measureMenu;
    }

    public JPanel getAddOnglet() {
        return addOnglet;
    }

    public void actionPerformed(ActionEvent evt)
    {
        for(InputComponent component : ((SetupView) this.getParent()).getSettings().keySet()) {
            if (component.isSelected()){
                if (evt.getSource().equals(((JPanel) component.getAllComponents().get("library_field")).getComponent(1))) {
                    System.out.println(((JComboBox)((JPanel) component.getAllComponents().get("library_field")).getComponent(1)).getSelectedItem());
                }
            }
        }

    }
    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        //TODO:TOUT doit être rempli pour le model
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("RECORD MESURE" + measureMenu.getSelectedIndex());
            /*
            for (InputComponent component : ((SetupView) this.getParent()).getSettings().keySet()) {
               for(InputComponent measure : ((SetupView) this.getParent()).getSettings().get(component)){
                    if(measure.isSelected()) {
                        System.out.println("RECORD MESURE" + measure.getTabId());
                    }
                }
            }*/
        }
    }

    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        if(measureMenu.getSelectedIndex() == 0 && ((SetupView) this.getParent()).getSettings().size()>0){
            InputComponent newPan = new InputComponent();
            //TODO : Name de la mesureUSE

            newPan.addComponent("measureName", addMeasureName());
            newPan.addComponent("measureType", addCombo(addLabelMeasureTonewTab("Type"), Arrays.asList("type1", "type2"), "comboBoxMeasureType" ));
            newPan.addComponent("measureCapture", addCombo(addLabelMeasureTonewTab("Capture"),capturesTypes, "comboBoxCaptureType" ));
            measureMenu.add(newPan,measureMenu.getTabCount());
            measureMenu.setSelectedComponent(newPan);
            measures.add(newPan);
        }
    }
    public void stateChanged(ChangeEvent e){
        if(e.getSource().equals(measureMenu)){
            setSelectedMeasure(measureMenu.getSelectedIndex());
        }
    }

    private JPanel addMeasureName(){
        JPanel container = new JPanel();

        JLabel newLab = addLabelMeasureTonewTab("Name");

        JTextField newTextField = new JTextField();
        newTextField.setName("textFieldMeasureName");
        newTextField.setPreferredSize(new Dimension(120, 20));
        newTextField.addKeyListener(this);

        container.add(newLab);
        container.add(newTextField);

        return container;
    }

    private JPanel addCombo(JLabel lab, List<String> params, String comboName){
        JPanel container = new JPanel();

        JComboBox type = new JComboBox();
        type.setName("comboBoxMeasureType");
        type.addActionListener(this);
        //TODO: à virer
        params.forEach(type::addItem);

        container.add(lab);
        container.add(type);

        return container;
    }

    public JLabel addLabelMeasureTonewTab(String labelName){
        JLabel newLabel = new JLabel(labelName);
        newLabel.setName(labelName);
        return newLabel;
    }

    /*public InputComponent addLabelMeasureTonewTab(InputComponent panel, String labelName){
        JLabel newLabel = new JLabel(labelName);
        newLabel.setName(labelName);
        panel.add(newLabel.getName(), newLabel);
        return panel;
    }
    public InputComponent addTextFieldMeasureTonewTab(InputComponent panel, String textFieldName){
        JTextField newTextField = new JTextField();
        newTextField.setName(textFieldName);
        newTextField.setPreferredSize(new Dimension(120, 20));
        newTextField.addKeyListener(this);
        panel.add(newTextField.getName(),newTextField);
        return panel;
    }

    public InputComponent addComboBoxMeasureTonewTab(InputComponent panel, List<String> types, String comBoxName){

        JComboBox type = new JComboBox();
        type.setName(comBoxName);
        type.addActionListener(this);
        //TODO: à virer
        types.forEach(type::addItem);
        panel.add(type);
        panel.addComponent(type.getName(), type);
        return panel;
    }*/
    public List<InputComponent> getMeasures() {
        return measures;
    }

    public void setMeasures(List<InputComponent> measures) {
        this.measures = measures;
    }

    public void setSelectedLibraryToController(String measureName) {
/*
        for (String mesureNames : controler.getMeasureNames()) {
            if (mesureNames == measureName) {
                controler.setParamViewControler(mesureNames);
            }
        }
        */

    }

    private InputComponent getSelectedMeasure(){
        for(InputComponent ic : measures){
            if(ic.isSelected()){
                return ic;
            }
        }
        return null;
    }

    private void setSelectedMeasure(int id){
        for(InputComponent ic : measures){
            if(ic.getTabId() == id){
                ic.select();
            }else{
                ic.unselect();
            }
        }
    }

    public LibControler getControler() {
        return controler;
    }
}
