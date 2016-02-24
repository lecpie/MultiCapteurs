package fr.polytech.pfe.multicapteurs.model.structural.capturemethods;

import fr.polytech.pfe.multicapteurs.model.generator.Visitor;
import fr.polytech.pfe.multicapteurs.model.language.Expression;
import fr.polytech.pfe.multicapteurs.model.language.Global;
import fr.polytech.pfe.multicapteurs.model.language.Updatable;
import fr.polytech.pfe.multicapteurs.model.structural.Type;

/**
 * Created by lecpie on 2/23/16.
 */
public abstract class TriggeredCapture implements CaptureMethod, Global, Updatable {

    public abstract String readExpression(Visitor visitor);

    public void global(Visitor visitor) {
        visitor.global(this);
    }

    public void update(Visitor visitor) {
        visitor.update(this);
    }
}
