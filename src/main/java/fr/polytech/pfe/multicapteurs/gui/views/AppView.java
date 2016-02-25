package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.MeasureManagementControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.SensorManagementControler;
import fr.polytech.pfe.multicapteurs.model.lib.Library;
import fr.polytech.pfe.multicapteurs.model.lib.LibraryUse;
import fr.polytech.pfe.multicapteurs.model.lib.MeasureUse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Louis on 22/02/2016.
 */
public class AppView extends JPanel {

    private JFrame appFrame = new JFrame();

    private JPanel header = new JPanel();
    private JPanel setupView;
    private JPanel footer = new JPanel();

    private AppControler controler;

    private TextArea arduinoCode;

    public AppView(AppControler controler){
        appFrame.setSize(960, 540);
        appFrame.setTitle("Auto-Drone-Multisensors");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setLocationRelativeTo(null);
        appFrame.setResizable(true);
        this.controler = controler;
        this.setName("AppView");
        this.setupView = new SetupView(controler);
        initComposant();
        appFrame.setContentPane(this);
        appFrame.setVisible(true);
    }

    private void initComposant(){
        BorderLayout mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        initHeader();
        initSetupView();
        initFooter();
    }

    private void initHeader(){
        JLabel title = new JLabel("Auto-Drone-Multisensors");
        header.add(title);
        this.add(header, BorderLayout.NORTH);
    }

    private void initSetupView(){
        GridLayout layout = new GridLayout(2,1);
        setupView.setLayout(layout);
        this.add(setupView, BorderLayout.CENTER);
    }

    private void initFooter(){
        JButton generate = new JButton("Generate Code");
        generate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JDialog dialog = new JDialog();
                dialog.setTitle("Arduino Code");
                arduinoCode = new TextArea();
                arduinoCode.setText(generateCode());
                dialog.add(arduinoCode);
                dialog.setSize(new Dimension(250,250));
                dialog.setVisible(true);
            }
        });

        JButton save = new JButton("Save Settings");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //TODO: add listener

        footer.add(save);
        footer.add(generate);
        this.add(footer, BorderLayout.SOUTH);
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
        this.add(measurePanel, BorderLayout.WEST);
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

    private String generateCode(){
        Library lib = new Library();
        LibraryUse libUse = new LibraryUse();
        MeasureUse measureUse = new MeasureUse();

        return controler.generateCode(lib, libUse, measureUse);
    }

    public JPanel getHeader() {
        return header;
    }

    public JPanel getFooter() {
        return footer;
    }

    public AppControler getControler() {
        return controler;
    }

    public TextArea getArduinoCode() {
        return arduinoCode;
    }

    public JFrame getAppFrame() {
        return appFrame;
    }

    public void setAppFrame(JFrame appFrame) {
        this.appFrame = appFrame;
    }

    public void setHeader(JPanel header) {
        this.header = header;
    }

    public JPanel getSetupView() {
        return setupView;
    }

    public void setSetupView(JPanel setupView) {
        this.setupView = setupView;
    }

    public void setFooter(JPanel footer) {
        this.footer = footer;
    }

    public void setControler(AppControler controler) {
        this.controler = controler;
    }

    public void setArduinoCode(TextArea arduinoCode) {
        this.arduinoCode = arduinoCode;
    }
}
