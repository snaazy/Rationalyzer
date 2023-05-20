import java.util.Stack;

public class Conversion {
    private String regex;
    private Stack<State> stateStack;

    public Conversion(String regex) {
        this.regex = regex;
        this.stateStack = new Stack<>();
    }

    // Methode effectuant la conversion de l'expression rationnelle en un automate non déterministe en parcourant chaque caractère
    // de l'expression rationnelle
    public Automaton convert() {
        for (char ch : regex.toCharArray()) {
            switch (ch) {
                case '(':
                    stateStack.push(null); // Marque pour la concaténation
                    break;
                case ')':
                    processParenthesis();
                    break;
                case '+':
                    processAlternation();
                    break;
                case '*':
                    processClosure();
                    break;
                default:
                    processCharacter(ch);
                    break;
            }
        }

        // Lorsque la conversion est terminée, l'automate résultant est sur le dessus de la pile
        State finalState = stateStack.pop();
        return new Automaton(finalState);
    }

    private void processCharacter(char ch) {
        State state = new State(ch);
        stateStack.push(state);
        performConcatenation();
    }

    private void processParenthesis() {
        performConcatenation();

        // Concaténation des états entre les parenthèses
        State currentState = stateStack.pop();
        while (currentState != null) {
            State previousState = stateStack.pop();
            previousState.addTransition(currentState, null);
            currentState = previousState;
        }

        performConcatenation();
    }

    private void processAlternation() {
        performConcatenation();
        stateStack.push(null); // Marque pour l'alternance
    }

    private void processClosure() {
        performConcatenation();

        State state = stateStack.pop();
        State closureState = new State();
        state.addTransition(closureState, null);
        closureState.addTransition(state, null);

        stateStack.push(closureState);
        performConcatenation();
    }

    // gère la concaténation des etats si nécessaire
    private void performConcatenation() {
        if (stateStack.size() >= 2 && stateStack.peek() != null) {
            State state2 = stateStack.pop();
            State state1 = stateStack.pop();
            state1.addTransition(state2, null);
            stateStack.push(state1);
        }
    }

    private class State {
        private char label;
        private List<Transition> transitions;

        public State(char label) {
            this.label = label;
            this.transitions = new ArrayList<>();
        }

        public void addTransition(State nextState, Character input) {
            transitions.add(new Transition(nextState, input));
        }
    }

    private class Transition {
        private State nextState;
        private Character input;

        public Transition(State nextState, Character input) {
            this.nextState = nextState;
            this.input = input;
        }
    }
}
