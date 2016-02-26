package fr.polytech.pfe.multicapteurs.model.structural.capturemethods;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

/**
 * Created by lecpie on 2/26/16.
 */
public class DistancedCapture extends TriggeredCapture {
    private double distance;

    public DistancedCapture() {
        this(1.0);
    }

    public DistancedCapture(double meterDistance) {
        this.distance = meterDistance;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void expression(Visitor visitor) {
        visitor.expression(this);
    }

    @Override
    public void global(Visitor visitor) {
        super.global(visitor);
        visitor.global(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistancedCapture that = (DistancedCapture) o;

        return Double.compare(that.distance, distance) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(distance);
        return (int) (temp ^ (temp >>> 32));
    }
}
