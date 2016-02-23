package fr.polytech.pfe.multicapteurs.model.generator;

/**
 * Created by lecpie on 2/23/16.
 */
public class IncrementalVariableGenerator implements VariableGenerator {
    private static int next = 0;

    @Override
    public String genName() {
        return "_CML_var_" + next++;
    }
}
