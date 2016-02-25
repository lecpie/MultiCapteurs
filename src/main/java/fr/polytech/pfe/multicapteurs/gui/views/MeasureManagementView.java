package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;
import jdk.internal.util.xml.impl.Input;

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
            newPan = addLabelMeasureTonewTab(newPan, "Name");
            newPan = addTextFieldMeasureTonewTab(newPan,"textFieldMeasureName");
            newPan = addLabelMeasureTonewTab(newPan, "Type");
            newPan = addComboBoxMeasureTonewTab(newPan,  Arrays.asList("arg1","arg2"),"comboBoxMeasureType");
            newPan = addLabelMeasureTonewTab(newPan, "Capture");
            newPan = addComboBoxMeasureTonewTab(newPan, Arrays.asList("arg1","arg2"),"comboBoxCaptureType");
            measureMenu.add(newPan,measureMenu.getTabCount());
            measureMenu.setSelectedComponent(newPan);
            measures.add(newPan);
        }
    }
    public InputComponent addLabelMeasureTonewTab(InputComponent panel, String labelName){
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
    }
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

}
