package fr.polytech.pfe.multicapteurs.model.structural;

public enum Type {
    DIGITAL("boolean"),
    INTEGER("int"),
    REAL("double"),
    STRING("string");

    private String name;

    Type(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}