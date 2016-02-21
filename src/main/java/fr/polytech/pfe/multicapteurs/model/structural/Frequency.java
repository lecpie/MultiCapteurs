package fr.polytech.pfe.multicapteurs.model.structural;

/**
 * Created by fofo on 21/02/16.
 */
public class Frequency {


    private Time unity;
    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Time getUnity() {
        return unity;
    }

    public void setUnity(Time unity) {
        this.unity = unity;
    }

}
