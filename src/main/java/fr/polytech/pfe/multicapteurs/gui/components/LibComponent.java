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
public class LibComponent extends JPanel{
    private String tabId;
    private String libName;
    private Map<String, Component> components;
    private ParamView params;

    public LibComponent(){
        this.components = new HashMap<>();
    }
    public LibComponent(String tabId, String libName, ParamView params) {
        this.tabId = tabId;
        this.libName = libName;
        this.params = params;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
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
