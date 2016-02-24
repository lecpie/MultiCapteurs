package fr.polytech.pfe.multicapteurs.model.structural.capturemethods;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;

/**
 * Created by lecpie on 2/23/16.
 */
public class AsapCapture extends TriggeredCapture {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String readExpression(Visitor visitor) {
        return visitor.readExpression(this);
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
