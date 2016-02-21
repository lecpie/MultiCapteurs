package fr.polytech.pfe.multicapteurs.model.structural;

/**
 * Created by fofo on 21/02/16.
 */
public class Frequency {

    private int rate;
    private Time unit;

    public Frequency(int rate, Time unit){
        this.rate = rate;
        this.unit = unit;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Time getUnit() {
        return unit;
    }

    public void setUnit(Time unit) {
        this.unit = unit;
    }

}
