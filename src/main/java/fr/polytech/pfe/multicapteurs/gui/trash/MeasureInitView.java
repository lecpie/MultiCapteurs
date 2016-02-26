package fr.polytech.pfe.multicapteurs.gui.trash;

import fr.polytech.pfe.multicapteurs.model.lib.Measure;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Louis on 23/02/2016.
 */
public class MeasureInitView extends JPanel {


    JPanel name, type, capture;
    GridBagLayout layout;
    GridBagConstraints c;

    //FIXME: align textfield with textbox
    public MeasureInitView(){
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1,1,1,1);

        this.setLayout(layout);

        name = new JPanel();
        name.add(new JLabel("Name"));
        JTextField nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(120,20));
        name.add(nameTextField);


        type = new JPanel();
        type.add(new JLabel("Type"));
        JTextField typeTextField = new JTextField();
        typeTextField.setPreferredSize(new Dimension(120,20));
        type.add(typeTextField);

        //TODO: add action listener to display/hide frequency input textfield
        capture = new JPanel();
        capture.add(new JLabel("Capture"));
        String[] modes ={"ASAP", "Frequency", "Metadata"};
        JComboBox mode = new JComboBox(modes);
        capture.add(mode);

        this.add(name, c);
        this.add(type, c);
        this.add(capture, c);
    }

    public JPanel getType() {
        return type;
    }

    public JPanel getCapture() {
        return capture;
    }

    @Override
    public GridBagLayout getLayout() {
        return layout;
    }

    public GridBagConstraints getC() {
        return c;
    }

}
