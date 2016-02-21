package fr.polytech.pfe.multicapteurs;

/**
 * Created by fofo on 21/02/16.
 */
public class CompilationError extends RuntimeException {
    public CompilationError(String msg) {
        super(msg);
    }
}
