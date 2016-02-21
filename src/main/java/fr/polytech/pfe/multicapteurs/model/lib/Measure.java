package fr.polytech.pfe.multicapteurs.model.lib;

import fr.polytech.pfe.multicapteurs.model.structural.Type;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lecpie on 1/15/16.
 */
public class Measure {
    private String name;

    private Library library;

    private Type type;

    private Map <String, String> defaultArgs = new LinkedHashMap<>();

    private List <String> requiredArgs = new ArrayList<>();

    private List <String> globalInstructions = new ArrayList<>();
    private List <String> setupInstructions = new ArrayList<>();
    private List <String> updateInstructions = new ArrayList<>();

    private List <String> variables = new ArrayList<>();

    private String readExpressionString;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Map<String, String> getDefaultArgs() {
        return defaultArgs;
    }

    public void setDefaultArgs(Map<String, String> defaultArgs) {
        this.defaultArgs = defaultArgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGlobalInstructions() {
        return globalInstructions;
    }

    public void setGlobalInstructions(List<String> globalInstructions) {
        this.globalInstructions = globalInstructions;
    }

    public List<String> getSetupInstructions() {
        return setupInstructions;
    }

    public void setSetupInstructions(List<String> setupInstructions) {
        this.setupInstructions = setupInstructions;
    }

    public List<String> getUpdateInstructions() {
        return updateInstructions;
    }

    public void setUpdateInstructions(List<String> updateInstructions) {
        this.updateInstructions = updateInstructions;
    }

    public String getReadExpressionString() {
        return readExpressionString;
    }

    public void setReadExpressionString(String readExpressionString) {
        this.readExpressionString = readExpressionString;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public List<String> getRequiredArgs() {
        return requiredArgs;
    }

    public void setRequiredArgs(List<String> requiredArgs) {
        this.requiredArgs = requiredArgs;
    }

}
