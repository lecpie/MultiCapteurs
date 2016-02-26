package fr.polytech.pfe.multicapteurs.gui.testView;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Louis on 26/02/2016.
 */
public class InputView extends JPanel {

    private GridBagLayout layout;
    private GridBagConstraints c;

    private Map <String, JTextField> paramInputs;


    public InputView() {
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 5.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridy = 0;

        paramInputs = new HashMap<>();

        this.setLayout(layout);
    }

    public void addParams(List <String> keys) {
        for (String key : keys) {
            addParam(key);
        }
    }

    void addParams(Map <String, String> keysValues) {
        for (String key : keysValues.keySet()) {
            addParam(key, keysValues.get(key));
        }
    }

    public void cleanParams() {
        removeAll();
        paramInputs = new HashMap<>();
    }

    public void addParam(String key) {
        addParam(key, "");
    }

    public void addParam(String key, String defaultValue) {
        JLabel label = new JLabel(key);
        JTextField input = new JTextField(defaultValue);
        input.setPreferredSize(new Dimension(120, 20));

        paramInputs.put(key, input);

        addComponent(label, true);
        addComponent(input, false);
    }

    private void addComponent(Component comp, boolean newLine){
        if(newLine) {
            c.gridx = 0;
            c.gridy++;

        } else {
            c.gridx++;
        }

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

        Map<String, String> someAreDefaults = new HashMap<>();
        List <String> someAreNot = new ArrayList<>();
        someAreDefaults.put("customer", "lol");
        someAreDefaults.put("theDeadLine", "soon");

        someAreNot.addAll(Arrays.asList("asap", "estimation"));

        InputView inputView = new InputView();
        inputView.addParams(someAreDefaults);
        inputView.addParams(someAreNot);

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
