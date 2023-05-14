import java.util.HashSet;
import java.util.Set;

public class Automaton {
    private State initialState;
    private Set<State> finalStates;

    public Automaton(State initialState) {
        this.initialState = initialState;
        this.finalStates = new HashSet<>();
    }

    public State getInitialState() {
        return initialState;
    }

    public void addFinalState(State state) {
        finalStates.add(state);
    }

    public boolean isFinalState(State state) {
        return finalStates.contains(state);
    }

    // Méthode pour effectuer une opération sur l'automate

    // Méthode pour valider une chaîne selon l'automate

    // ...
}
