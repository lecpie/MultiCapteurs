package fr.polytech.pfe.multicapteurs.gui.views;

import fr.polytech.pfe.multicapteurs.gui.controlers.ParamViewControler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Louis on 22/02/2016.
 */
public class ParamView extends JPanel{

    private ParamViewControler controler;
    private GridBagLayout layout;
    private GridBagConstraints c;
    private Map<JLabel, JTextField> fields;

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

        for(String field : controler.getRequiredArgs()){
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

    public ParamViewControler getControler() {
        return controler;
    }

    public void setControler(ParamViewControler controler) {
        this.controler = controler;
        this.removeAll();
        for(String field : controler.getRequiredArgs()){
            this.add(createInput(field), c);
        }
    }

    public Map<String, String> getRequiredArgs(){
        Map<String, String> args = new HashMap<>();
        Iterator it = fields.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, String> field = (Map.Entry<String, String>)it.next();
            args.put(field.getKey(), field.getValue());
            it.remove();
        }
        return args;
    }
}
