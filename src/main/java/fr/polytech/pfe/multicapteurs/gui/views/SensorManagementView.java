package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementView extends JPanel implements ActionListener, FocusListener, MouseListener, ChangeListener{

    //TODO: Dropdown presets

    private SensorManagementControler controler;

    private JTabbedPane sensorMenu;
    private ParamView paramView;
    private JPanel addOnglet;
    private JPanel defaultOnglet;
    private BoxLayout layout;
    private ArrayList<String> libTypes;

    public SensorManagementView(SensorManagementControler controler){
        this.controler = controler;
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        libTypes = new ArrayList<>();
        for(Library libs : controler.getLoadedLibraries().values()){
            libTypes.add(libs.getName());
        }
        initTabPanned();
        initParamView();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initTabPanned(){
        sensorMenu = new JTabbedPane();
        addOnglet = new JPanel();
        addOnglet.setBackground(Color.gray);
        addOnglet.setEnabled(false);

        defaultOnglet = new JPanel();
        defaultOnglet = addLabelLibsTonewTab(defaultOnglet);
        defaultOnglet = addComboBoxLibsTonewTab(defaultOnglet, libTypes);

        sensorMenu.addTab("+", addOnglet);
        sensorMenu.addTab("defaultPanInit", defaultOnglet);
        sensorMenu.setSelectedComponent(defaultOnglet);
        sensorMenu.addMouseListener(this);
        sensorMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(sensorMenu);
    }

    public void initParamView(){
        paramView = new ParamView(new ParamViewControler());
        paramView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(paramView);
    }

    public void actionPerformed(ActionEvent evt)
    {
    }

    public void focusGained(FocusEvent e) {
        if(e.getSource() instanceof JComboBox){

            System.out.println("lol");
            sensorMenu.getComponentAt(0).setName("blabl");
        }
    }

    public void focusLost(FocusEvent e) {
        if(e.getSource() instanceof JComboBox){

            System.out.println("lolexit");
            sensorMenu.getComponentAt(0).setName("blabl");
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
        if(sensorMenu.getSelectedIndex() == 0){
            JPanel newPan = new JPanel();
            //TODO : Name de la libUSE
            newPan = addLabelLibsTonewTab(newPan);
            newPan = addComboBoxLibsTonewTab(newPan, libTypes);
            sensorMenu.add(newPan,sensorMenu.getTabCount());
        }
    }
    public void stateChanged(ChangeEvent e) {
    }
    public JPanel addLabelLibsTonewTab(JPanel panel){
        panel.add("libraryLabel",new JLabel("Library"));
        return panel;
    }

    public JPanel addComboBoxLibsTonewTab(JPanel panel, ArrayList<String> types){

        JComboBox libType = new JComboBox();
        libType.addFocusListener(this);
        for(String type : types){
            libType.addItem(type);
        }
        panel.add("libraryType",libType);
        //Next line à viré quand on aura le nom
        panel.setName("newPan" + sensorMenu.getTabCount());
        return panel;
    }
    public JPanel setNewTabName(JPanel panel, String name){
        panel.setName(name);
        return panel;
    }
    public ArrayList<String> getLibTypes() {
        return libTypes;
    }

    public ParamView getParamView() {
        return paramView;
    }

    @Override
    public BoxLayout getLayout() {
        return layout;
    }

    public JPanel getDefaultOnglet() {
        return defaultOnglet;
    }

    public JPanel getAddOnglet() {
        return addOnglet;
    }

    public JTabbedPane getSensorMenu() {
        return sensorMenu;
    }

}
