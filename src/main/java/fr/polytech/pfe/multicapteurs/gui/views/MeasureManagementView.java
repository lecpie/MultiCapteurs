package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementView extends JPanel implements ActionListener,MouseListener,FocusListener, KeyListener {
    //TODO : init measure avec measue uses
    //TODO: Measure preset
    //TODO: Measure setup
    //TODO: Param List (ParamView)

    private MeasureManagementControler controler;
    private JTabbedPane measureMenu;
    private InputComponent addOnglet;
    private ParamView paramView;
    private ArrayList<String> measuresTypes;
    private ArrayList<String> capturesTypes;

    private List<InputComponent> measures;


    public MeasureManagementView(MeasureManagementControler controler){
        this.controler = controler;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.measuresTypes = new ArrayList<>();
        this.capturesTypes = new ArrayList<>();
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
        measureMenu.addMouseListener(this);
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
        if(evt.getSource() instanceof JComboBox){
           String text = ((JComboBox)(evt.getSource())).getSelectedItem().toString();
                Component[] components = ((JPanel)measureMenu.getSelectedComponent()).getComponents();
            for (Component component : components) {
                if (component.getClass().equals(JTextField.class)) {
                    ((JTextField)component).setText(text);
                }
            }
        }

    }
    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() instanceof JTextField && ((Component)e.getSource()).isFocusable()) {
                measureMenu.setTitleAt(measureMenu.getSelectedIndex(), ((JTextField)e.getSource()).getText().toLowerCase()+measureMenu.getSelectedIndex());
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
        if(measureMenu.getSelectedIndex() == 0){
            InputComponent newPan = new InputComponent();
            //TODO : Name de la mesureUSE

            newPan.addComponent("measureName", addMeasureName());
            newPan.addComponent("measureType", addCombo(addLabelMeasureTonewTab("Type"), Arrays.asList("arg1", "arg2"), "comboBoxMeasureType" ));
            newPan.addComponent("measureCapture", addCombo(addLabelMeasureTonewTab("Capture"), Arrays.asList("arg1", "arg2"), "comboBoxCaptureType" ));

           /* addLabelMeasureTonewTab(newPan, "Name");
            addTextFieldMeasureTonewTab(newPan, "textFieldMeasureName");

            addLabelMeasureTonewTab(newPan, "Type");
            addComboBoxMeasureTonewTab(newPan, Arrays.asList("arg1", "arg2"), "comboBoxMeasureType");

            addLabelMeasureTonewTab(newPan, "Capture");
            addComboBoxMeasureTonewTab(newPan, Arrays.asList("arg1", "arg2"), "comboBoxCaptureType");*/

            measureMenu.add(newPan,measureMenu.getTabCount());
            measureMenu.setSelectedComponent(newPan);
            measures.add(newPan);
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

    private void setSelectedMeasure(String id){
        for(InputComponent ic : measures){
            if(Integer.toString(ic.getTabId()).equals(id)){
                //System.out.println("selecting" + Integer.toString(ic.getTabId()));
                ic.select();
            }else{
                //System.out.println("unselecting" + Integer.toString(ic.getTabId()));
                ic.unselect();
            }
        }
    }

}
