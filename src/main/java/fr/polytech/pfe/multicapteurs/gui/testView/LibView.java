package fr.polytech.pfe.multicapteurs.gui.testView;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 26/02/2016.
 */
public class LibView extends InputView{

    private GridBagLayout layout;
    private GridBagConstraints c;

    private LibControler controler;

    private JLabel libraryLabel;
    private JComboBox librarySelector;

    private InputView args;

    public LibView(LibControler controler){
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;

        this.controler = controler;

        libraryLabel = new JLabel("Library");

        librarySelector = new JComboBox();
        setCombo();

        System.out.println(librarySelector.getSelectedItem());

        this.setLayout(layout);
    }

    public void addComponent(Component comp, boolean newLine){
        if(newLine){
            c.gridx = 0;
            c.gridy++;
        }else{
            c.gridx++;
        }
       // requiredArgs.add(comp);
        this.add(comp, c);
    }

    /*public Component getComponentByName(String s){
        for(Component c : requiredArgs){
            if(c.getName().equals(s)){
                return c;
            }
        }
        return null;
    }*/

    private void setCombo(){
        for(String s : controler.getLibNames()){
            librarySelector.addItem(s);
        }
    }

 /*   public List<Component> getAllComponents() {
        return requiredArgs;
    }
*/


    public GridBagConstraints getC() {
        return c;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(800, 600));

        App app = new App();

        LibView lv = new LibView(new LibControler(app.getLoadedLibraries()));

        JLabel lab = new JLabel("Library");

        JComboBox combo = new JComboBox();
        combo.addItem("DHT");

        JComboBox combo2 = new JComboBox();
        combo2.addItem("DHT2");

        JComboBox combo3 = new JComboBox();
        combo3.addItem("DHT3");

        System.out.println("x : " + lv.getC().gridx + "y : " + lv.getC().gridy);
        lv.addComponent(lab, true);
        System.out.println("x : " + lv.getC().gridx + "y : " + lv.getC().gridy);
        lv.addComponent(combo, false);
        System.out.println("x : " + lv.getC().gridx + "y : " + lv.getC().gridy);
        lv.addComponent(combo2, true);
        System.out.println("x : " + lv.getC().gridx + "y : " + lv.getC().gridy);
        lv.addComponent(combo3, false);
        System.out.println("x : " + lv.getC().gridx + "y : " + lv.getC().gridy);



        jf.setContentPane(lv);
        jf.setVisible(true);


    }
}
