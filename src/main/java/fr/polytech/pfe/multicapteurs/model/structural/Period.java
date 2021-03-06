package fr.polytech.pfe.multicapteurs.model.structural;

/**
 * Created by fofo on 21/02/16.
 */
public class Period {

    private int rate;
    private Time unit;

    public Period(int msRate) {
        this.rate = msRate;
        this.unit = Time.MS;
    }

    public Period(int rate, Time unit){
        this.rate = rate;
        this.unit = unit;
    }

    public int getRateintoMS (){

            switch (this.unit){
                case HOUR:
                    return (this.rate * 60 * 60 * 1000);
                case MIN:
                    return (this.rate * 60 * 1000);
                case SEC:
                    return (this.rate * 1000);
                case MS:
                    return (this.rate);
                default :
                    return (this.rate);
            }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        return getRateintoMS() == period.getRateintoMS();
    }

    @Override
    public int hashCode() {
        return getRateintoMS();
    }
}
