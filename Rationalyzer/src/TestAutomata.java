import java.util.Map;
import java.util.Set;

public class TestAutomata {
    public static void main(String[] args) {
        Automata automate = new Automata("A");
        automate.ajouterEtat("q1");
        automate.ajouterEtat("q2");
        automate.ajouterEtat("q3");
        automate.ajouterEtat("q4");

        automate.ajouterAlphabet("a");
        automate.ajouterAlphabet("b");

        automate.ajouterEtatInitial("q1");
        automate.ajouterEtatFinal("q2");

        automate.ajouterTransition("q1", "a", "q2");
        automate.ajouterTransition("q1", "b", "q3");
        automate.ajouterTransition("q2", "a", "q4");
        automate.ajouterTransition("q2", "b", "q1");

        Automata automateDeux = new Automata("B");
        automateDeux.ajouterEtat("q1");
        automateDeux.ajouterEtat("q2");
        automateDeux.ajouterAlphabet("a");
        automateDeux.ajouterAlphabet("b");
        automateDeux.ajouterEtatInitial("q1");
        automateDeux.ajouterEtatFinal("q2");
        automateDeux.ajouterTransition("q1", "b", "q1");
        automateDeux.ajouterTransition("q1", "a", "q2");
        automateDeux.ajouterTransition("q2", "b", "q2");
        automateDeux.ajouterTransition("q2", "a", "q1");

        afficherAttributsAutomate(automate);
        testerAcceptation(automate);
        testerCompletudeAvant(automate);
        
        automate.completion();
        
        afficherAttributsAutomate(automate);
        testerCompletudeApres(automate);

        afficherAttributsAutomate(automateDeux);
        testerAcceptation(automateDeux);
        testerCompletude(automateDeux);
    }

    private static void afficherAttributsAutomate(Automata automate) {
        System.out.println(" ---- AFFICHAGE DES ATTRIBUTS ---- ");
        System.out.println(" === NOM : " + automate.getNom());
        System.out.println(" === ETATS : " + automate.getEtats());
        System.out.println(" === ALPHABET : " + automate.getAlphabet());
        System.out.println(" === ETATS INITIAUX : " + automate.getEtatsInitiaux());
        System.out.println(" === ETATS FINALS : " + automate.getEtatsFinaux());
        System.out.println(" === TRANSITIONS : ");
        for (Map.Entry<String, Map<String, Set<String>>> entry : automate.getTransitions().entrySet()) {
            String etatSource = entry.getKey();
            Map<String, Set<String>> transitionEtat = entry.getValue();
            for (Map.Entry<String, Set<String>> transition : transitionEtat.entrySet()) {
                String symbole = transition.getKey();
                Set<String> etatsDestination = transition.getValue();
                System.out.println("    " + etatSource + " --" + symbole + "--> " + etatsDestination);
            }
        }
        System.out.println(" ---- FIN AFFICHAGE DES ATTRIBUTS ---- ");
    }

    private static void testerAcceptation(Automata automate) {
        System.out.println(" ---- TEST ACCEPTATION ---- ");
        String[] mots = {"a", "b", "aa", "bb", "bbaab", "aaba", "aabbbbbbbbb"};
        for (String mot : mots) {
            System.out.println("Le mot \"" + mot + "\" est accepté : " + automate.accepteMot(mot));
        }
        System.out.println(" ---- FIN TEST ACCEPTATION ---- ");
    }

    private static void testerCompletudeAvant(Automata automate) {
        boolean estCompletAvant = automate.estComplet();
        System.out.println("Avant la complétion : L'automate est complet : " + estCompletAvant);
    }

    private static void testerCompletudeApres(Automata automate) {
        boolean estCompletApres = automate.estComplet();
        System.out.println("Après la complétion : L'automate est complet : " + estCompletApres);
    }

    private static void testerCompletude(Automata automate) {
        boolean estComplet = automate.estComplet();
        System.out.println("L'automate est complet : " + estComplet);
    }
}
