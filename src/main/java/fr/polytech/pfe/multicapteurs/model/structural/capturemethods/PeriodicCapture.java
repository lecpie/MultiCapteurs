package fr.polytech.pfe.multicapteurs.model.structural.capturemethods;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.structural.Period;

/**
 * Created by lecpie on 2/23/16.
 */
public class PeriodicCapture extends TriggeredCapture {
    private Period capturePeriod;

    public PeriodicCapture(Period capturePeriod) {
        this.capturePeriod = capturePeriod;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
    public String readExpression(Visitor visitor) {
        return visitor.readExpression(this);
    }

    @Override
    public void update(Visitor visitor) {
        super.update(visitor);
        visitor.update(this);
    }
}
