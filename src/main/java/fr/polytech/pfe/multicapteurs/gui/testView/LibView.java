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
public class LibView /* extends InputView */{

    /*
    private LibControler controler;

    private JLabel libraryLabel;
    private JComboBox librarySelector;


    public LibView (LibControler controler){


        this.controler = controler;

        libraryLabel = new JLabel("Library");


        librarySelector = new JComboBox();
        setCombo();

        //requiredArgs = new ArrayList<>();

    }



    public Component getComponentByName(String s){
        for(Component c : requiredArgs){
            if(c.getName().equals(s)){
                return c;
            }
        }
        return null;
    }

    private void setCombo(){
        for(String s : controler.getLibNames()){
            librarySelector.addItem(s);
        }
    }

    public List<Component> getAllComponents() {
        return requiredArgs;
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



        jf.setContentPane(lv);
        jf.setVisible(true);


    }
        */

}
