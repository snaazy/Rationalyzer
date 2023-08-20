public class TestAutomata {
    public static void main(String[] args) {
        // Création de l'automate
        Automata automate = new Automata("A");
        
        // Ajout des états
        automate.ajouterEtat("q1");
        automate.ajouterEtat("q2");
        automate.ajouterEtat("q3");

        
        
        // Ajout de l'alphabet
        automate.ajouterAlphabet("a");
        automate.ajouterAlphabet("b");
        
        // Ajout des états initiaux et finals
        automate.ajouterEtatInitial("q1");
        automate.ajouterEtatFinal("q3");
      
        
        // Ajout des transitions
        automate.ajouterTransition("q1", "a", "q2");
        automate.ajouterTransition("q1", "b", "q1");
        automate.ajouterTransition("q2", "a", "q3");
        automate.ajouterTransition("q2", "b", "q3");
        automate.ajouterTransition("q3", "b", "q3");
        automate.ajouterTransition("q3", "a", "q1");
     
        
        // Affichage des propriétés de l'automate
        System.out.println(" ---- AFFICHAGE DES ATTRIBUTS ---- \n");
        System.out.println(" === NOM : " + automate.getNom());
        System.out.println(" === ETATS : " + automate.getEtats());
        System.out.println(" === ALPHABET : " + automate.getAlphabet());
        System.out.println(" === ETATS INITIAUX : " + automate.getEtatsInitiaux());
        System.out.println(" === ETATS FINALS : " + automate.getEtatsFinaux());
        System.out.println(" === TRANSITIONS : " + automate.getTransitions() + "\n");
        System.out.println(" ---- FIN AFFICHAGE DES ATTRIBUTS ---- ");

        System.out.println(" ---- TEST ACCEPTATION  ---- \n");

         // Test de la méthode accepteMot
         String mot1 = "a";
         String mot2 = "b";
         String mot3 = "aa";
         String mot4 = "bb";
         String mot5 = "bbaab";
         String mot6 = "aaba";
         String mot7 = "aabbbbbbbbb";
         
         System.out.println("Le mot \"" + mot1 + "\" est accepté : " + automate.accepteMot(mot1));
         System.out.println("Le mot \"" + mot2 + "\" est accepté : " + automate.accepteMot(mot2));
         System.out.println("Le mot \"" + mot3 + "\" est accepté : " + automate.accepteMot(mot3));
         System.out.println("Le mot \"" + mot4 + "\" est accepté : " + automate.accepteMot(mot4));
         System.out.println("Le mot \"" + mot5 + "\" est accepté : " + automate.accepteMot(mot5));
         System.out.println("Le mot \"" + mot6 + "\" est accepté : " + automate.accepteMot(mot6));
         System.out.println("Le mot \"" + mot7 + "\" est accepté : " + automate.accepteMot(mot7));

         System.out.println(" ---- FIN TEST ACCEPTATION  ---- \n");

          // Test de la complétude de l'automate avant la complétion
          boolean estComplet = automate.estComplet();
          System.out.println("L'automate est complet : " + estComplet);
  
        


        
        
    }
}
