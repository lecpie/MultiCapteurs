package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.InputComponent;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;
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
        addOnglet.setLibName("+");
        addOnglet.setBackground(Color.gray);
        addOnglet.setEnabled(false);
        sensorMenu.addTab(addOnglet.getLibName(), addOnglet);
        addOnglet.setTabId(sensorMenu.getTabCount());
        sensorMenu.addMouseListener(this);
        this.add(sensorMenu);
    }
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof JComboBox) {
            sensorMenu.setTitleAt(sensorMenu.getSelectedIndex(), ((JComboBox) evt.getSource()).getSelectedItem().toString().toLowerCase() + sensorMenu.getSelectedIndex());
            sensorMenu.setTitleAt(0, "+");
            setSelectedLibraryToController(((JComboBox) evt.getSource()).getSelectedItem().toString());
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
            libs.add(newPan);
            newPan.setTabId(sensorMenu.getTabCount());
            newPan.setLibName("newPan" + sensorMenu.getTabCount());
            sensorMenu.add(newPan, sensorMenu.getTabCount());
            newPan = addLabelLibsTonewTab(newPan);
            newPan = addComboBoxLibsTonewTab(newPan);
            sensorMenu.setSelectedComponent(newPan);
        }
    }

    public void stateChanged(ChangeEvent e) {
    }

    public InputComponent addLabelLibsTonewTab(InputComponent panel) {
        panel.add("libraryLabel", new JLabel("Library"));
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

    public void setSelectedLibraryToController(String LibName) {

        for (String libnames : controler.getLibNames()) {
            if (libnames == LibName) {
                controler.setParamViewControler(libnames);
            }
        }
    }
}
