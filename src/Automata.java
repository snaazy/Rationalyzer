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

    public boolean accepteMot(String mot) {
        // On créé un ensemble etatsCourant qui contient initialement les états initiaux de l'automate.
        // cet ensemble représente les états possibles à un certain point de l'exécution de l'automate.

        Set<String> etatsCourants = new HashSet<>(etatsInitiaux);
        
        // on itère sur chaque symbole du mot donné en paramètre. La méthode toCharArray convertir le mot en un tableau
        // de caractères, et la boucle for parcourt chaque caractère du mot
        for (char symbole : mot.toCharArray()) {
            // ensemblr vide qui va contenir les etats atteignables à partir des etats courants en lisant le symbole en cours
            Set<String> etatsSuivants = new HashSet<>();
            // on irere sur chaque etat courants qui sont les etats actuellement possibles
            for (String etat : etatsCourants) {
                Map<String, Set<String>> transitionEtat = transitions.getOrDefault(etat, Collections.emptyMap());
                Set<String> etatsDestination = transitionEtat.getOrDefault(String.valueOf(symbole), Collections.emptySet());
                etatsSuivants.addAll(etatsDestination);
            }
    
            etatsCourants = etatsSuivants;
        }
    
        // Vérification des états finals, si l'un des etats finals de l'automate se trouve dans l'ensemble etatscourants => l'automate
        // a atteint un etat final en lisant le mot, on renvoie true
        for (String etatFinal : etatsFinaux) {
            if (etatsCourants.contains(etatFinal)) {
                return true;
            }
        }
    
        return false;
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
