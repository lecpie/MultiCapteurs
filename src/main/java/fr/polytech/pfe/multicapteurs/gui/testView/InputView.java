package fr.polytech.pfe.multicapteurs.gui.testView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Louis on 26/02/2016.
 */
public class InputView extends JPanel {

    private GridBagLayout layout;
    private GridBagConstraints c;

    private List <String> paramKeys;

    private Map <String, JTextField> paramInputs;


    public InputView(List<String> paramKeys) {
        this.paramKeys = paramKeys;

        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridy = 0;

        setupParams();

        this.setLayout(layout);
    }

    private void setupParams() {
        paramInputs = new HashMap<>();
        removeAll();

        for (String key : paramKeys) {
            addParam(key);
        }
    }

    private void addParam(String key) {
        addParam(key, "");
    }

    private void addParam(String key, String defaultValue) {
        JLabel label = new JLabel(key);
        JTextField input = new JTextField(defaultValue);
        input.setPreferredSize(new Dimension(120, 20));

        paramInputs.put(key, input);

        addComponent(label, false);
        addComponent(input, true);
    }

    private void addComponent(Component comp, boolean newLine){
        if(newLine) {
            c.gridx = 0;
            c.gridy++;

        } else {
            c.gridx++;
        }
        //args.add(comp);
        this.add(comp, c);
    }

    Map <String, String> getInput() {
        Map <String, String> params = new HashMap<>();

        for (String key : paramInputs.keySet()) {
            params.put(key, paramInputs.get(key).getText());
        }

        return params;
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("InputViewTest");
        InputView inputView = new InputView(Arrays.asList("Hello", "world"));

        JButton button = new JButton("Get params");

        window.add(inputView);
        window.add(button, BorderLayout.SOUTH);
        
        button.addActionListener(actionEvent -> {
            Map <String, String> params = inputView.getInput();
            if (params == null) {
                System.err.println("Null param map");
            }
            else {
                for (String key : params.keySet()) {
                    System.out.println(key + " : " + params.get(key));
                }
            }
        });

        window.pack();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
