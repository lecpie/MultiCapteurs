package fr.polytech.pfe.multicapteurs.gui;

import javax.swing.*;

/**
 * Created by Louis on 22/02/2016.
 */
public class View extends JFrame {

    private JPanel container = new JPanel();

    private Controler controler;

    public View(Controler controler){
        this.setSize(1920, 1080);
        this.setTitle("Auto-Drone-Multisensors");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //initComposant();
        this.controler = controler;
        this.setContentPane(container);
        this.setVisible(true);
    }
}
