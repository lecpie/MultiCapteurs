package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureManagementView extends JPanel implements ActionListener,MouseListener {
    //TODO : init measure avec measue uses
    //TODO: Measure preset
    //TODO: Measure setup
    //TODO: Param List (ParamView)

    private MeasureManagementControler controler;
    private JTabbedPane measureMenu;
    private JPanel addOnglet;
    private MeasureInitView measureInit;
    private ParamView paramView;


    public MeasureManagementView(MeasureManagementControler controler){
        this.controler = controler;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
        if(evt.getSource() instanceof JComboBox){

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
            //newPan = addLabelLibsTonewTab(newPan);
            //newPan = addComboBoxLibsTonewTab(newPan, libTypes);
            measureMenu.add(newPan,measureMenu.getTabCount());
            measureMenu.setSelectedComponent(newPan);
        }
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
