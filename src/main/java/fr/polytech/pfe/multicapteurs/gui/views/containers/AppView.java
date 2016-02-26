package fr.polytech.pfe.multicapteurs.gui.views.containers;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Louis on 26/02/2016.
 */
public class AppView extends JPanel {
    private JFrame appFrame = new JFrame();

    private JPanel header = new JPanel();
    private  CentralPanel centralPanel;
    private JPanel footer = new JPanel();

    private AppControler controler;

    private TextArea arduinoCode;

    public AppView(AppControler controler){
        appFrame.setSize(new Dimension(1600, 1200));
        appFrame.setTitle("Auto-Drone-Multisensors");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setResizable(true);

        this.controler = controler;
        this.setName("AppView");
        initComposant();
        appFrame.setContentPane(this);
        appFrame.setVisible(true);
    }

    private void initComposant(){
        BorderLayout mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        initHeader();
        initCentralPannel();
        initFooter();
    }

    private void initHeader(){
        JLabel title = new JLabel("Auto-Drone-Multisensors");
        header.add(title);
        this.add(header, BorderLayout.NORTH);
    }

    /*private void initLibsPannel(){
        libsPannel = new LibsPannel(controler.getLibControler());
        this.add(libsPannel, BorderLayout.WEST);
    }

    private void initMeasuresPannel(){
        measuresPannel = new MeasuresPannel(controler.getLibControler());
        this.add(measuresPannel, BorderLayout.EAST);
    }*/

    private void initCentralPannel(){
        centralPanel = new CentralPanel(controler.getLibControler());
        this.add(centralPanel, BorderLayout.CENTER);
    }


    private void initFooter(){
        JButton generate = new JButton("Generate Code");
        generate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                JDialog dialog = new JDialog();
                dialog.setTitle("Arduino Code");
                arduinoCode = new TextArea();
                arduinoCode.setText(controler.generateCode());
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
}
