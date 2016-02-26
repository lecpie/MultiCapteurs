package fr.polytech.pfe.multicapteurs.gui.testView;

import fr.polytech.pfe.multicapteurs.gui.testView.tools.AddLib;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * Created by Louis on 26/02/2016.
 */
public class LibsPannel extends JTabbedPane {

    private AddLib addLib;
    private List<LibView> libViews;


    public LibsPannel(){
        addLib = new AddLib();
        this.addTab("+", addLib);
    }

    public void addLibView(LibView l){
        libViews.add(l);
        this.addTab(l.getName(), l);
    }

    public List<LibView> getLibViews() {
        return libViews;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(800, 600));

        LibsPannel lp = new LibsPannel();

        jf.setContentPane(lp);
        jf.setVisible(true);
    }
}
