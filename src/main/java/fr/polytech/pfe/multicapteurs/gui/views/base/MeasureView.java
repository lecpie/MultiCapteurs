package fr.polytech.pfe.multicapteurs.gui.views.base;

import fr.polytech.pfe.multicapteurs.gui.controlers.LibControler;
import fr.polytech.pfe.multicapteurs.gui.views.base.InputView;

/**
 * Created by Louis on 26/02/2016.
 */
public class MeasureView extends InputView {

    private LibControler controler;

    public MeasureView (LibControler controler){
        this.controler = controler;
    }
}
