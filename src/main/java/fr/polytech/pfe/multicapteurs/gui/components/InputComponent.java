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

    public InputComponent(){
        this.components = new HashMap<>();
    }

    public InputComponent(int tabId, String componentName, ParamView params) {
        this.tabId = tabId;
        this.componentName = componentName;
        this.setName(componentName);
        this.params = params;
    }

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
        this.params = params;
    }

    public Map<String, Component> getAllComponents() {
        return components;
    }

    public void setComponents(Map<String, Component> components) {
        this.components = components;
    }

    public Component addComponent(String name, Component c){
        components.put(name, c);
        return c;
    }

}
