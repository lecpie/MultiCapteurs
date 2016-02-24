package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamView extends JPanel{

    private ParamViewControler controler;
    private GridBagLayout layout;
    GridBagConstraints c;

    public ParamView(ParamViewControler controler){
        this.controler = controler;

        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 100.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1,1,1,1);
        this.setLayout(new GridBagLayout());

        for(String field : controler.getReqParams()){
            this.add(createInput(field), c);
        }
    }

    private JPanel createInput(String field){
        JPanel newParam = new JPanel();
        JLabel newParamName = new JLabel(field);
        newParam.add(newParamName);
        JTextField newParamTextField = new JTextField();
        newParamTextField.setPreferredSize(new Dimension(120, 20));
        newParam.add(newParamTextField);
        return newParam;
    }
    @Override
    public GridBagLayout getLayout() {
        return layout;
    }


}
