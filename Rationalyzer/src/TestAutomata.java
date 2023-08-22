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
        
        // Ajout des états initiaux et finals
        automate.ajouterEtatInitial("q1");
        automate.ajouterEtatFinal("q2");
      
        
        // Ajout des transitions
        automate.ajouterTransition("q1", "a", "q2");
        automate.ajouterTransition("q1", "b", "q3");
        automate.ajouterTransition("q2", "a", "q4");
        automate.ajouterTransition("q2", "b", "q1");

         // Création de l'automate
         Automata automateDeux = new Automata("B");
          // Ajout des états
        automateDeux.ajouterEtat("q1");
        automateDeux.ajouterEtat("q2");
         // Ajout de l'alphabet
         automateDeux.ajouterAlphabet("a");
         automateDeux.ajouterAlphabet("b");
         
         // Ajout des états initiaux et finals
         automateDeux.ajouterEtatInitial("q1");
         automateDeux.ajouterEtatFinal("q2");
       
         
         // Ajout des transitions
         automateDeux.ajouterTransition("q1", "b", "q1");
         automateDeux.ajouterTransition("q1", "a", "q2");
         automateDeux.ajouterTransition("q2", "b", "q2");
         automateDeux.ajouterTransition("q2", "a", "q1");
        
     
        
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

          automate.completion();







             
        // Affichage des propriétés de l'automate
        System.out.println(" ---- AFFICHAGE DES ATTRIBUTS ---- \n");
        System.out.println(" === NOM : " + automateDeux.getNom());
        System.out.println(" === ETATS : " + automateDeux.getEtats());
        System.out.println(" === ALPHABET : " + automateDeux.getAlphabet());
        System.out.println(" === ETATS INITIAUX : " + automateDeux.getEtatsInitiaux());
        System.out.println(" === ETATS FINALS : " + automateDeux.getEtatsFinaux());
        System.out.println(" === TRANSITIONS : " + automateDeux.getTransitions() + "\n");
        System.out.println(" ---- FIN AFFICHAGE DES ATTRIBUTS ---- ");

        System.out.println(" ---- TEST ACCEPTATION  ---- \n");

         // Test de la méthode accepteMot
         String moot1 = "a";
         String moot2 = "b";
         String moot3 = "aa";
         String moot4 = "bb";
         String moot5 = "bbaab";
         String moot6 = "aaba";
         String moot7 = "aabbbbbbbbb";
         
         System.out.println("Le mot \"" + moot1 + "\" est accepté : " + automateDeux.accepteMot(moot1));
         System.out.println("Le mot \"" + moot2 + "\" est accepté : " + automateDeux.accepteMot(moot2));
         System.out.println("Le mot \"" + moot3 + "\" est accepté : " + automateDeux.accepteMot(moot3));
         System.out.println("Le mot \"" + moot4 + "\" est accepté : " + automateDeux.accepteMot(moot4));
         System.out.println("Le mot \"" + moot5 + "\" est accepté : " + automateDeux.accepteMot(moot5));
         System.out.println("Le mot \"" + moot6 + "\" est accepté : " + automateDeux.accepteMot(moot6));
         System.out.println("Le mot \"" + moot7 + "\" est accepté : " + automateDeux.accepteMot(moot7));

         System.out.println(" ---- FIN TEST ACCEPTATION  ---- \n");
        
  
           // Test de la complétude de l'automate avant la complétion
           boolean estCompletDeux = automateDeux.estComplet();
           System.out.println("L'automate est complet : " + estCompletDeux);
 
           automate.completion();
 


        
        
    }
}
