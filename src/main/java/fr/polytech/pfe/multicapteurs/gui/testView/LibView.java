package fr.polytech.pfe.multicapteurs.gui.testView;

import fr.polytech.pfe.multicapteurs.App;
import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import fr.polytech.pfe.multicapteurs.model.lib.Library;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Louis on 26/02/2016.
 */
public class LibView extends InputView {

    private LibControler controler;

    private GridBagLayout layout;
    private GridBagConstraints c;

    private String currentLib;

    private JLabel libraryLabel;
    private JComboBox librarySelector;

    private InputView args;

    public LibView(LibControler controler){
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.weightx = 15.0;
        c.gridx = 0;
        c.gridy = 0;
        this.setLayout(layout);

        this.controler = controler;

        libraryLabel = new JLabel("Library");
        addComponent(this, c, libraryLabel, false);

        librarySelector = new JComboBox();
        setCombo();
        addComponent(this, c, librarySelector, false);

        currentLib = librarySelector.getItemAt(librarySelector.getSelectedIndex()+1).toString();

        args = new InputView();
        args.addParams(controler.getRequiredArgs(currentLib));
        args.addParams(controler.getAccessibleArgs(currentLib));
        args.addParams(controler.getDefaultdArgs(currentLib));

        addComponent(this, c, args, true);


    }

    private void setCombo(){
        for(String s : controler.getLibNames()){
            librarySelector.addItem(s);
        }
    }

    public GridBagConstraints getC() {
        return c;
    }

    public String getCurrentLib() {
        return currentLib;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(800, 600));

        AppControler appc = new AppControler();


        LibView lv = new LibView(new LibControler(appc.getApp().getLoadedLibraries()));



        jf.setContentPane(lv);
        jf.setVisible(true);


    }

}
