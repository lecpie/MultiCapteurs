package fr.polytech.pfe.multicapteurs.gui.components;

import fr.polytech.pfe.multicapteurs.gui.views.ParamView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Louis on 25/02/2016.
 */
public class InputComponent extends JPanel{
    private int tabId;
    private String componentName;
    private Map<String, Component> components;
    private ParamView params;
    private boolean isSelected;

    private GridBagLayout layout;
    private GridBagConstraints c;

    public InputComponent(){
        layout = new GridBagLayout();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 100.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(1,1,1,1);
        this.setLayout(new GridBagLayout());

        components = new HashMap<>();
        params = new ParamView();
        isSelected = false;
    }


    /*public InputComponent(int tabId, String componentName, ParamView params) {
        this.tabId = tabId;
        this.componentName = componentName;
        this.setName(componentName);
        this.params = params;
        isSelected = false;
    }*/

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.setName(componentName);
        this.componentName = componentName;
    }

    public ParamView getParams() {
        return params;
    }

    public void setParams(ParamView params) {
        this.remove(this.params);
        this.params = params;
        this.add(this.params, c);
        this.repaint();
    }

  /*  public void setParams(List<String> params) {
        this.remove(this.params);
        this.params = new ParamView(params);
        this.add(this.params);
        System.out.println("heyyyyyy");
    }*/

    public Map<String, Component> getAllComponents() {
        return components;
    }

    public void setComponents(Map<String, Component> components) {
        this.components = components;
    }

    public Component addComponent(String name, Component newComp){
        components.put(name, newComp);
        this.add(newComp, c);
        this.repaint();
        return newComp;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void unselect(){
        this.isSelected = false;
    }

    public void select() {
        this.isSelected = true;
    }

    /*public void addSubComponent(Component newComp1, Component newComp2){
        JPanel container = new JPanel();

        container.add(newComp1);
        container.add(newComp2);

        components.put(newComp1, newComp2);
        this.add(container,c);
    }*/

}
