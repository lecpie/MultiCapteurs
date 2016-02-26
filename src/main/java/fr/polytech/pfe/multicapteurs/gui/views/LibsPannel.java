package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import fr.polytech.pfe.multicapteurs.gui.tools.AddLib;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Louis on 26/02/2016.
 */
public class LibsPannel extends JTabbedPane {

    private LibControler controler;

    private AddLib addLib;
    private List<LibView> libViews;


    public LibsPannel(LibControler controler){
        this.controler = controler;
        this.libViews = new ArrayList<>();

        addLib = new AddLib();
        this.addTab("+", addLib);

        addLibView(new LibView(controler));
    }

    public void addLibView(LibView l){
        libViews.add(l);
        this.addTab(l.getCurrentLib(), l);
        //this.addTab(l.getName(), l);
    }

    /*public void addLibUse(Library l){
        this.addT
    }*/

    public List<LibView> getLibViews() {
        return libViews;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(800, 600));

        AppControler appc = new AppControler();


        LibsPannel lp = new LibsPannel(new LibControler(appc.getApp().getLoadedLibraries()));

        jf.setContentPane(lp);
        jf.setVisible(true);
    }
}
