package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.components.ParamElement;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamView extends JPanel{

    private List<String> labels;
    private List<ParamElement> fields;

    private GridBagLayout layout;
    private GridBagConstraints c;

    public ParamView(){
        this.labels = labels;
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 100.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1,1,1,1);
        this.setLayout(new GridBagLayout());
        this.fields = new ArrayList<>();
    }

    public ParamView(List<String> labels){
        this.labels = labels;
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 100.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1,1,1,1);
        this.setLayout(new GridBagLayout());
        this.fields = new ArrayList<>();

        for(String label : labels){
           createInput(label);
        }
    }

    public ParamElement createInput(String field){
        ParamElement newParam = new ParamElement(field);
        fields.add(newParam);
        return newParam;
    }

    public void reDraw(){
        this.removeAll();
        for(ParamElement param : fields){
            this.add(param, c);
        }
        this.repaint();
    }

}
