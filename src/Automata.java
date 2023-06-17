import java.util.*;

public class Automata {
    private Set<String> etats;
    private Set<String> alphabet;
    private Set<String> etatsInitiaux;
    private Set<String> etatsFinaux;
    private Map<String, Map<String, Set<String>>> transitions;

    public Automata() {
        etats = new HashSet<>();
        alphabet = new HashSet<>();
        etatsInitiaux = new HashSet<>();
        etatsFinaux = new HashSet<>();
        transitions = new HashMap<>();
    }

    public void ajouterEtat(String etat) {
        etats.add(etat);
    }

    public void ajouterAlphabet(String symbole) {
        alphabet.add(symbole);
    }

    public void ajouterEtatInitial(String etat) {
        etatsInitiaux.add(etat);
    }

    public void ajouterEtatFinal(String etat) {
        etatsFinaux.add(etat);
    }

    public void ajouterTransition(String etatSource, String symbole, String etatDestination) {
        if (!transitions.containsKey(etatSource)) {
            transitions.put(etatSource, new HashMap<>());
        }

        Map<String, Set<String>> transitionEtat = transitions.get(etatSource);
        if (!transitionEtat.containsKey(symbole)) {
            transitionEtat.put(symbole, new HashSet<>());
        }

        Set<String> etatsDestination = transitionEtat.get(symbole);
        etatsDestination.add(etatDestination);
    }

    public Set<String> getEtats() {
        return etats;
    }

    public Set<String> getAlphabet() {
        return alphabet;
    }

    public Set<String> getEtatsInitiaux() {
        return etatsInitiaux;
    }

    public Set<String> getEtatsFinaux() {
        return etatsFinaux;
    }

    public Map<String, Map<String, Set<String>>> getTransitions() {
        return transitions;
    }

    public static void main(String[] args) {
        Automata automate = new Automata();
        automate.ajouterEtat("q0");
        automate.ajouterEtat("q1");
        automate.ajouterAlphabet("a");
        automate.ajouterAlphabet("b");
        automate.ajouterEtatInitial("q0");
        automate.ajouterEtatFinal("q1");
        automate.ajouterTransition("q0", "a", "q1");

        // Affichage des propriétés de l'automate
        System.out.println("États : " + automate.getEtats());
        System.out.println("Alphabet : " + automate.getAlphabet());
        System.out.println("États initiaux : " + automate.getEtatsInitiaux());
        System.out.println("États finaux : " + automate.getEtatsFinaux());
        System.out.println("Transitions : " + automate.getTransitions());
    }
}
