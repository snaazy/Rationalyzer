import java.util.Collections;

public class RegularExpressionNode {
    private String operation;
    private String symbol;
    private RegularExpressionNode leftChild;
    private RegularExpressionNode rightChild;

    public RegularExpressionNode(){
        this.operation = null;
        this.symbol = null;
        this.leftChild = null;
        this.rightChild = null;
    }
    
    public RegularExpressionNode(String operation, String symbol) {
        this.operation = operation;
        this.symbol = symbol;
        this.leftChild = null;
        this.rightChild = null;
    }
    
    public String getOperation() {
        return operation;
    }
    
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public RegularExpressionNode getLeftChild() {
        return leftChild;
    }
    
    public void setLeftChild(RegularExpressionNode leftChild) {
        this.leftChild = leftChild;
    }
    
    public RegularExpressionNode getRightChild() {
        return rightChild;
    }
    
    public void setRightChild(RegularExpressionNode rightChild) {
        this.rightChild = rightChild;
    }

   
    public Automata generateAutomaton() {
        if (operation.equals("")) {
            Automata automaton = new Automata();
            
            String stateA = automaton.getNewStateName();
            String stateB = automaton.getNewStateName();
            
            automaton.ajouterEtat(stateA);
            automaton.ajouterEtat(stateB);
            automaton.ajouterTransition(stateA, symbol, stateB);
            
            automaton.ajouterEtatInitial(stateA);
            automaton.ajouterEtatFinal(stateB);
            
            return automaton;
        } else if (operation.equals("|")) {
            Automata leftAutomaton = leftChild.generateAutomaton();
            Automata rightAutomaton = rightChild.generateAutomaton();
            
            Automata automaton = new Automata();
            
            String newState = automaton.getNewStateName();
            automaton.ajouterEtat(newState);
            for (String initialState : leftAutomaton.getEtatsInitiaux()) {
                automaton.ajouterTransition(newState, "", initialState);
            }
            for (String initialState : rightAutomaton.getEtatsInitiaux()) {
                automaton.ajouterTransition(newState, "", initialState);
            }
            
            automaton.ajouterEtatInitial(newState);
            
            String newFinalState = automaton.getNewStateName();
            automaton.ajouterEtat(newFinalState);
            automaton.ajouterTransitionsEpsilon(leftAutomaton.getEtatsFinaux(), newFinalState);
            automaton.ajouterTransitionsEpsilon(rightAutomaton.getEtatsFinaux(), newFinalState);
            automaton.ajouterEtatFinal(newFinalState);
            
            return automaton;
        } else if (operation.equals("*")) {
            Automata childAutomaton = leftChild.generateAutomaton();
            
            Automata automaton = new Automata();
            
            String newState = automaton.getNewStateName();
            automaton.ajouterEtat(newState);
            for (String etatSource : childAutomaton.getEtatsInitiaux()) {
                automaton.ajouterTransition(etatSource, "", newState);
            }
            
            
        
            automaton.ajouterEtatInitial(newState);
            String newFinalState = automaton.getNewStateName();
            automaton.ajouterEtat(newFinalState);
            
            for (String etatSource : childAutomaton.getEtatsFinaux()) {
                automaton.ajouterTransitionsEpsilon(Collections.singleton(etatSource), newFinalState);
            }
            
            
            

            automaton.ajouterTransitionsEpsilon(childAutomaton.getEtatsFinaux(), newFinalState);
            automaton.ajouterTransition(newState, "", newFinalState);
            automaton.ajouterEtatFinal(newFinalState);
            
            return automaton;
        }
        
        return null; // ou lancer une exception en cas d'opération non reconnue
    }
    
    
}
