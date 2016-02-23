package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppView extends JFrame {

    private JPanel mainContainer = new JPanel();
    private JPanel centralContainer = new JPanel();
    /*private JPanel sensorManagementView;
    private JPanel measureManagementView;*/

    private AppControler controler;

    public AppView(AppControler controler){
        this.setSize(960, 540);
        this.setTitle("Auto-Drone-Multisensors");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        initComposant();
        this.controler = controler;
        this.setContentPane(mainContainer);
        this.setVisible(true);
    }

    private void initComposant(){
        BorderLayout mainLayout = new BorderLayout();
        mainContainer.setLayout(mainLayout);


        GridLayout centralLayout = new GridLayout(1,2);
        centralContainer.setLayout(centralLayout);

        initHeader();
        initSensorManagementView();
        initMeasurManagementView();
        initFooter();
        mainContainer.add(centralContainer, BorderLayout.CENTER);
    }

    private void initHeader(){
        JPanel header = new JPanel();
        JLabel title = new JLabel("Auto-Drone-Multisensors");
        header.add(title);
        mainContainer.add(header, BorderLayout.NORTH);
    }

    private void initSensorManagementView(){
        SensorManagementView smv = new SensorManagementView(new SensorManagementControler());
        centralContainer.add(smv);
    }

    private void initMeasurManagementView(){
        MeasureManagementView mmv = new MeasureManagementView(new MeasureManagementControler());
        centralContainer.add(mmv);
    }

    private void initFooter(){
        JPanel footer = new JPanel();
        JButton generate = new JButton("Generate Code");
        generate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JDialog dialog = new JDialog();
                dialog.add(new TextArea(" aaaaaa "));
                dialog.setTitle("ArduinoCode");
                dialog.setSize(new Dimension(250,250));
                dialog.setVisible(true);
            }
        });
        footer.add(generate);
        mainContainer.add(footer, BorderLayout.SOUTH);
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
        mainContainer.add(measurePanel, BorderLayout.WEST);
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
