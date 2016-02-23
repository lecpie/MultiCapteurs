package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppView extends JFrame {

    private JPanel container = new JPanel();
    private JPanel sensorManagementView;
    private JPanel measureManagementView;

    private AppControler controler;

    public AppView(AppControler controler){
        this.setSize(960, 540);
        this.setTitle("Auto-Drone-Multisensors");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        initComposant();
        this.controler = controler;
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void initComposant(){
        container.setLayout(new BorderLayout());
        initHeader();
        initSensorManagementView();
        initMeasurManagementView();
        //initMeasureList();
        initFooter();
    }

    private void initHeader(){
        JPanel header = new JPanel();
        JLabel title = new JLabel("Auto-Drone-Multisensors");
        header.add(title);
        container.add(header, BorderLayout.NORTH);
    }

    private void initSensorManagementView(){
        sensorManagementView = new SensorManagementView();
        container.add(sensorManagementView, BorderLayout.WEST);
    }

    private void initMeasurManagementView(){
        measureManagementView = new MeasureManagementView();
        container.add(measureManagementView, BorderLayout.EAST);
    }

    private void initFooter(){
        JPanel footer = new JPanel();
        JButton generate = new JButton("Generate Code");
        footer.add(generate);
        container.add(footer, BorderLayout.SOUTH);
    }

    //TODO: add/remove item in JList
    private void initMeasureList(){
        JPanel measurePanel = new JPanel();

        String[] content = {
            "DHT", "GPS", "LIGHT", "NEW SENSOR"
        };

        JList measuresList = new JList(content);
        measuresList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(measuresList);
        measurePanel.add(scrollPane);
        container.add(measurePanel, BorderLayout.WEST);
    }

    private JPanel sensorProp(String type, String pin){
        JPanel sensorPropsPanel = new JPanel();
        sensorPropsPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Sensor params");
        sensorPropsPanel.add(title, BorderLayout.NORTH);

        JPanel params = new JPanel();
        params.setLayout(new GridLayout(2,2));

        JLabel typeLabel = new JLabel("Type");
        params.add(typeLabel);

        JTextField typeTextField = new JTextField();
        params.add(typeTextField);

        JLabel pinLabel = new JLabel("Pin");
        params.add(pinLabel);

        JTextField pinTextField = new JTextField();
        params.add(pinTextField);
        return null;
    }
}
