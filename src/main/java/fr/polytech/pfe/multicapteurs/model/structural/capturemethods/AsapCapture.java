package fr.polytech.pfe.multicapteurs.model.structural.capturemethods;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

/**
 * Created by lecpie on 2/23/16.
 */
public class AsapCapture extends TriggeredCapture {
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
    public void update(Visitor visitor) {
        visitor.update(this);
    }
}
