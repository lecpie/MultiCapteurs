package fr.polytech.pfe.multicapteurs.model.structural.capturemethods;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.structural.Period;

/**
 * Created by lecpie on 2/23/16.
 */
public class PeriodicCapture extends TriggeredCapture {
    private Period capturePeriod;

    public PeriodicCapture() {
        this(new Period(1000));
    }

    public PeriodicCapture(Period capturePeriod) {
        this.capturePeriod = capturePeriod;
    }

    public Period getCapturePeriod() {
        return capturePeriod;
    }

    public void setCapturePeriod(Period capturePeriod) {
        this.capturePeriod = capturePeriod;
    }

    @Override
    public void global(Visitor visitor) {
        super.global(visitor);
        visitor.global(this);
    }

    @Override
    public void expression(Visitor visitor) {
        visitor.expression(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodicCapture that = (PeriodicCapture) o;

        return !(capturePeriod != null ? !capturePeriod.equals(that.capturePeriod) : that.capturePeriod != null);
    }

    @Override
    public int hashCode() {
        return capturePeriod.hashCode();
    }
}
