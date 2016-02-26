package fr.polytech.pfe.multicapteurs.gui.testView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Louis on 26/02/2016.
 */
public class LibView extends JPanel{

    private GridBagLayout layout;
    private GridBagConstraints c;
    private List<Component> components;

    public LibView(){
        layout = new GridBagLayout();
        components = new ArrayList<>();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridy = 0;
        this.setLayout(layout);
    }

    public void addComponent(Component comp, boolean newLine){
        if(newLine){
            c.gridx = 0;
            c.gridy++;
        }else{
            c.gridx++;
        }
        components.add(comp);
        this.add(comp, c);
    }

    public Component getComponentByName(String s){
        for(Component c : components){
            if(c.getName().equals(s)){
                return c;
            }
        }
        return null;
    }

    public List<Component> getAllComponents() {
        return components;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(800, 600));

        LibView lv = new LibView();

        JLabel lab = new JLabel("Library");

        JComboBox combo = new JComboBox();
        combo.addItem("DHT");

        JComboBox combo2 = new JComboBox();
        combo2.addItem("DHT2");

        JComboBox combo3 = new JComboBox();
        combo3.addItem("DHT3");

        lv.addComponent(lab, false);
        lv.addComponent(combo, false);
        lv.addComponent(combo2, true);
        lv.addComponent(combo3, true);


        jf.setContentPane(lv);
        jf.setVisible(true);


    }
}
