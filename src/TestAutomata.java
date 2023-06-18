public class TestAutomata {
    public static void main(String[] args) {
        // Création de l'automate
        Automata automate = new Automata("A");
        
        // Ajout des états
        automate.ajouterEtat("q1");
        automate.ajouterEtat("q2");
        automate.ajouterEtat("q3");
        automate.ajouterEtat("q4");
        
        // Ajout de l'alphabet
        automate.ajouterAlphabet("a");
        automate.ajouterAlphabet("b");
        
        // Ajout des états initiaux et finaux
        automate.ajouterEtatInitial("q0");
        automate.ajouterEtatFinal("q2");
        
        // Ajout des transitions
        automate.ajouterTransition("q0", "a", "q1");
        automate.ajouterTransition("q1", "b", "q2");
        automate.ajouterTransition("q2", "a", "q0");
        
        // Affichage des propriétés de l'automate
        System.out.println(" ---- AFFICHAGE ---- \n");
        System.out.println(" === NOM : " + automate.getNom());
        System.out.println(" === ETATS : " + automate.getEtats());
        System.out.println(" === ALPHABET : " + automate.getAlphabet());
        System.out.println(" === ETATS INITIAUX : " + automate.getEtatsInitiaux());
        System.out.println(" === ETATS FINALS : " + automate.getEtatsFinaux());
        System.out.println(" === TRANSITIONS : " + automate.getTransitions() + "\n");
        System.out.println(" ---- FIN AFFICHAGE ---- ");
    }
}
