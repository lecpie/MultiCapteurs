package fr.polytech.pfe.multicapteurs.gui.views.inputs;

import fr.polytech.pfe.multicapteurs.gui.controlers.AppControler;
import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import fr.polytech.pfe.multicapteurs.gui.views.utils.GridBagUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 26/02/2016.
 */
public class LibView extends SuperView{

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
        GridBagUtils.addComponent(this, c, libraryLabel, false);

        librarySelector = new JComboBox();
        setCombo();
        GridBagUtils.addComponent(this, c, librarySelector, false);

        currentLib = librarySelector.getItemAt(librarySelector.getSelectedIndex()).toString();

        args = new InputView();
        args.addParams(controler.getRequiredArgs(currentLib));
        args.addParams(controler.getAccessibleArgs(currentLib));
        args.addParams(controler.getDefaultdArgs(currentLib));

        GridBagUtils.addComponent(this, c, args, true);


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
