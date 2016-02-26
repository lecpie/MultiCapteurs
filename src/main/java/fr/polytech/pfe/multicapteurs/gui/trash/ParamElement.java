package fr.polytech.pfe.multicapteurs.gui.trash;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 25/02/2016.
 */
public class ParamElement extends JPanel{
    private JLabel label;
    private JTextField input;

    public ParamElement(String name) {
        label = new JLabel(name);
        input = new JTextField();
        input.setPreferredSize(new Dimension(120, 20));
        this.add(label);
        this.add(input);
    }

    public String getLabelName(){
        return label.getName();
    }

    public JLabel getLabel(){
        return label;
    }

    public JTextField getInput(){
        return input;
    }
}
