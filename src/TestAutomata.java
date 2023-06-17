public class TestAutomata {
    public static void main(String[] args) {
        // Création de l'automate
        Automata automate = new Automata();
        
        // Ajout des états
        automate.ajouterEtat("q0");
        automate.ajouterEtat("q1");
        automate.ajouterEtat("q2");
        
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
        System.out.println("États : " + automate.getEtats());
        System.out.println("Alphabet : " + automate.getAlphabet());
        System.out.println("États initiaux : " + automate.getEtatsInitiaux());
        System.out.println("États finaux : " + automate.getEtatsFinaux());
        System.out.println("Transitions : " + automate.getTransitions());
    }
}
