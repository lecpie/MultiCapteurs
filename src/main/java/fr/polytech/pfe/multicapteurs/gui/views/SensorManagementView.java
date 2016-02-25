package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Louis on 23/02/2016.
 */
public class SensorManagementView extends JPanel implements ActionListener, FocusListener, MouseListener, ChangeListener {

    //TODO: Dropdown presets
    private LibControler controler;

    private JTabbedPane sensorMenu;
    private InputComponent addOnglet;
    private BoxLayout layout;
    private List<String> libTypes;

    private List<InputComponent> libs;

    public SensorManagementView(LibControler controler) {
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
        addOnglet.setTabId(0);
        libs.add(addOnglet);
        sensorMenu.addMouseListener(this);
        this.add(sensorMenu);
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JComboBox) {
            String libname = ((JComboBox) evt.getSource()).getSelectedItem().toString();
            sensorMenu.setTitleAt(sensorMenu.getSelectedIndex(), libname.toLowerCase() + sensorMenu.getSelectedIndex());
            sensorMenu.setTitleAt(0, "+");
            //TEST
            /*
            for(InputComponent component : ((SetupView) this.getParent()).getSettings().keySet()){
                if(component.getTabId() == getSelectedComponent().getTabId()){
                    getSelectedComponent().setComponentName(((JComboBox) evt.getSource()).getSelectedItem().toString().toLowerCase()+sensorMenu.getSelectedIndex());
                    System.out.println(((JComboBox)((JPanel) getSelectedComponent().searchComponentByName("library_field")).getComponent(1)).getSelectedItem());
                }

            }*/
            //
            setSelectedLib(sensorMenu.getSelectedIndex());
            System.out.println(getSelectedComponent());
            getSelectedComponent().setParams(new ParamView(controler.getRequiredArgs(libname)));
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
            newPan.addComponent("library_field", createLibComboBox());
            sensorMenu.setSelectedComponent(newPan);
            newPan.setParams(new ParamView(controler.getRequiredArgs("DHT")));
            libs.add(newPan);
            //TEST
            ((SetupView)this.getParent()).getSettings().put(newPan, new ArrayList<>());
        }
    }

    public void stateChanged(ChangeEvent e) {
       if(e.getSource().equals(sensorMenu)){
            setSelectedLib(sensorMenu.getSelectedIndex());
            System.out.println("Tab Updated : tab n°"+sensorMenu.getSelectedIndex());
        }
    }

    private Component createLibComboBox(){
        JPanel container = new JPanel();

        JLabel library = new JLabel("Library");
        library.setName("libraryLabel");

        JComboBox libType = new JComboBox();
        libType.setName("comboBoxLibType");
        libTypes.forEach(libType::addItem);
        libType.addActionListener(this);
        libType.addActionListener((MeasureManagementView) ((SetupView) getParent()).getMeasureManagementView());

        container.add(library);
        container.add(libType);

        return container;
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

    private void setSelectedLib(int id){
        for(InputComponent ic : libs){
            if(ic.getTabId()==id){
                //System.out.println("selecting" + Integer.toString(ic.getTabId()));
                ic.select();
            }else{
                //System.out.println("unselecting" + Integer.toString(ic.getTabId()));
                ic.unselect();
            }
        }
    }



}
