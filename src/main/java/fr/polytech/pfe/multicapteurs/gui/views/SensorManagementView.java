package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementView extends JPanel implements ActionListener, FocusListener, MouseListener, ChangeListener {

    //TODO: Dropdown presets
    private SensorManagementControler controler;

    private JTabbedPane sensorMenu;
    private InputComponent addOnglet;
    private BoxLayout layout;
    private List<String> libTypes;

    private List<InputComponent> libs;

    public SensorManagementView(SensorManagementControler controler) {
        this.controler = controler;
        this.layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(layout);
        this.libs = new ArrayList<>();
        this.libTypes = new ArrayList<>();
        this.libTypes.addAll(controler.getLibNames().stream().collect(Collectors.toList()));
        initTabPanned();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void initTabPanned() {
        sensorMenu = new JTabbedPane();
        addOnglet = new InputComponent();
        addOnglet.setComponentName("+");
        addOnglet.setBackground(Color.gray);
        addOnglet.setEnabled(false);
        sensorMenu.addTab(addOnglet.getComponentName(), addOnglet);
        addOnglet.setTabId(sensorMenu.getTabCount());
        libs.add(addOnglet);
        sensorMenu.addMouseListener(this);
        this.add(sensorMenu);
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JComboBox) {
            String libname = ((JComboBox) evt.getSource()).getSelectedItem().toString();
            sensorMenu.setTitleAt(sensorMenu.getSelectedIndex(), libname.toLowerCase() + sensorMenu.getSelectedIndex());
            sensorMenu.setTitleAt(0, "+");
            controler.setSelectedLib(libname);

        }
    }

    public void focusGained(FocusEvent e) {

    }

    public void focusLost(FocusEvent e) {

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
        if (sensorMenu.getSelectedIndex() == 0) {
            InputComponent newPan = new InputComponent();
            newPan.setTabId(sensorMenu.getTabCount());
            newPan.setComponentName("newPan" + sensorMenu.getTabCount());
            sensorMenu.add(newPan, sensorMenu.getTabCount());
            addLabelLibsTonewTab(newPan);
            addComboBoxLibsTonewTab(newPan);
            sensorMenu.setSelectedComponent(newPan);
            newPan.setParams(new ParamView(controler.getRequiredArgs("DHT")));
            //System.out.println(e.getSource());
            //System.out.println(e.getSource().getSe);
            //newPan.setParams(Arrays.asList("DHT", "DHT2"));
            // newPan.add()
            libs.add(newPan);
            setSelectedComponent(Integer.toString(newPan.getTabId()));
        }else{
            //TODO: set selected component to corresponding tab
        }
    }

    public void stateChanged(ChangeEvent e) {
    }

    public InputComponent addLabelLibsTonewTab(InputComponent panel) {
        JLabel label = new JLabel();
        label.setName("libraryLabel");
        panel.addComponent(label.getName(), label);
        return panel;
    }

    public InputComponent addComboBoxLibsTonewTab(InputComponent panel) {
        JComboBox libType = new JComboBox();
        libType.setName("comboBoxLibType");
        libTypes.forEach(libType::addItem);
        libType.addActionListener(this);

        libType.addActionListener((MeasureManagementView) ((SetupView) getParent()).getMeasureManagementView());
        panel.add(libType);
        panel.addComponent(libType.getName(), libType);
        return panel;
    }

    public InputComponent setNewTabName(InputComponent panel, String name) {
        panel.setName(name);
        return panel;
    }

    public List<String> getLibTypes() {
        return libTypes;
    }

    @Override
    public BoxLayout getLayout() {
        return layout;
    }

    public InputComponent getAddOnglet() {
        return addOnglet;
    }

    public JTabbedPane getSensorMenu() {
        return sensorMenu;
    }

    private InputComponent getSelectedComponent(){
        for(InputComponent ic : libs){
            if(ic.isSelected()){
                return ic;
            }
        }
        return null;
    }

    private void setSelectedComponent(String id){
        for(InputComponent ic : libs){
            if(Integer.toString(ic.getTabId()).equals(id)){
                ic.select();
            }else{
                ic.unselect();
            }
        }
    }


}
