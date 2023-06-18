import java.util.*;

public class Automata {
    private String nom;
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

    public Automata(String nom) {
        this.nom = nom;
        etats = new HashSet<>();
        alphabet = new HashSet<>();
        etatsInitiaux = new HashSet<>();
        etatsFinaux = new HashSet<>();
        transitions = new HashMap<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

}
