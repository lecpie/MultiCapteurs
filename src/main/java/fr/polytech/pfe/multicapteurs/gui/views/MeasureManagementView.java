package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementView extends JPanel implements ActionListener,MouseListener,FocusListener {
    //TODO : init measure avec measue uses
    //TODO: Measure preset
    //TODO: Measure setup
    //TODO: Param List (ParamView)

    private MeasureManagementControler controler;
    private JTabbedPane measureMenu;
    private JPanel addOnglet;
    private MeasureInitView measureInit;
    private ParamView paramView;
    private ArrayList<String> measuresTypes;
    private ArrayList<String> capturesTypes;


    public MeasureManagementView(MeasureManagementControler controler){
        this.controler = controler;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        measuresTypes = new ArrayList<>();
        capturesTypes = new ArrayList<>();
        initMeasureTabPanned();
        initParamView();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void initMeasureTabPanned() {
        measureMenu = new JTabbedPane();
        addOnglet = new JPanel();
        addOnglet.setBackground(Color.gray);
        addOnglet.setEnabled(false);

        measureMenu.addTab("+", addOnglet);
        measureMenu.addMouseListener(this);
        measureMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(measureMenu);
    }

    private void initParamView(){
        paramView = new ParamView(new ParamViewControler());
        this.add(paramView);
    }

    public JTabbedPane getMeasureMenu() {
        return measureMenu;
    }

    public JPanel getAddOnglet() {
        return addOnglet;
    }

    public MeasureInitView getMeasureInit() {
        return measureInit;
    }

    public ParamView getParamView() {
        return paramView;
    }
    public void actionPerformed(ActionEvent evt)
    {
    }
    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {
        if(e.getSource() instanceof JTextField){
            measureMenu.setTitleAt(measureMenu.getSelectedIndex(), ((JTextField)e.getSource()).getText().toLowerCase()+measureMenu.getSelectedIndex());
        }
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
            JPanel newPan = new JPanel();
            //TODO : Name de la mesureUSE
            newPan = addLabelMeasureTonewTab(newPan, "Name","measureName");
            newPan = addTextFieldMeasureTonewTab(newPan,"","measureName");
            newPan = addLabelMeasureTonewTab(newPan, "Type","measureTypes");
            newPan = addComboBoxMeasureTonewTab(newPan, measuresTypes, "measureTypeCB");
            newPan = addLabelMeasureTonewTab(newPan, "Capture","CaptureTypes");
            newPan = addComboBoxMeasureTonewTab(newPan, capturesTypes, "captureTypeCB");
            measureMenu.add(newPan,measureMenu.getTabCount());
            measureMenu.setSelectedComponent(newPan);
        }
    }
    public JPanel addLabelMeasureTonewTab(JPanel panel, String labelName, String panelName){
        panel.add(panelName, new JLabel(labelName));
        return panel;
    }
    public JPanel addTextFieldMeasureTonewTab(JPanel panel, String labelName, String panelName){
        JTextField newTextField = new JTextField(labelName);
        newTextField.setPreferredSize(new Dimension(120,20));
        newTextField.addFocusListener(this);
        panel.add(panelName,newTextField);
        return panel;
    }

    public JPanel addComboBoxMeasureTonewTab(JPanel panel, ArrayList<String> types, String panelName){

        JComboBox type = new JComboBox();
        type.addActionListener(this);
        //TODO: à virer
        type.addItem("FUCK");
        /*for(String type : types){
            libType.addItem(type);
        }*/
        panel.add(panelName,type);
        return panel;
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

    public void updateParamView(String measureName){
       /* //TODO add tab
        this.remove(paramView);
        this.paramView.setControler(controler.setParamViewControler(measureName));
        this.add(paramView);
        this.repaint();*/
    }

}
