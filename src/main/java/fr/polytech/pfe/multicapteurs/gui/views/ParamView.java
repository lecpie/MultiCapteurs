package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamView extends JFrame{

    private ParamViewControler controler;

    public ParamView(ParamViewControler controler){
        this.controler = controler;
    }

    private JPanel paramsFrame(){
        JPanel paramsPanel = new JPanel();
        paramsPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Sensor params");
        paramsPanel.add(title, BorderLayout.NORTH);

        JPanel paramsListPanel = new JPanel();
        List<String> paramsList = controler.getReqParams();
        paramsListPanel.setLayout(new GridLayout(paramsList.size(), 2));

        for(String param : paramsList){
            paramsListPanel.add(new JLabel(param));
            paramsListPanel.add(new JTextField());
        }

        paramsPanel.add(paramsListPanel, BorderLayout.CENTER);
        return paramsPanel;
    }


}
