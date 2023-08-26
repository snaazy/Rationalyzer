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
    private int stateCounter = 0; 
    
    public String getNewStateName() {
        return "q" + stateCounter++;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
    
    public void completion() {
        String poubelle = "Poubelle"; // etat poubelle
        ajouterEtat(poubelle);
    
        // Ajout des transitions manquantes pour chaque état
        for (String etat : etats) {
            Map<String, Set<String>> transitionEtat = transitions.getOrDefault(etat, new HashMap<>());
            Set<String> symbolesManquants = new HashSet<>(alphabet);
            symbolesManquants.removeAll(transitionEtat.keySet());
    
            // Ajout des transitions manquantes vers l'état poubelle pour chaque symbole manquant
            for (String symbole : symbolesManquants) {
                ajouterTransition(etat, symbole, poubelle);
            }
        }
    
        // Ajout des transitions manquantes pour l'état poubelle
        Map<String, Set<String>> poubelleTransitions = new HashMap<>();
        for (String symbole : alphabet) {
            poubelleTransitions.put(symbole, Collections.singleton(poubelle));
        }
        transitions.put(poubelle, poubelleTransitions);
    }
    

    public boolean estComplet() {
        for (String etat : etats) {
            for (String symbole : alphabet) {
                Map<String, Set<String>> transitionEtat = transitions.getOrDefault(etat, Collections.emptyMap());
                Set<String> etatsDestination = transitionEtat.getOrDefault(symbole, Collections.emptySet());
    
                if (etatsDestination.isEmpty()) {
                    // Si les états de destination sont vides pour un symbole, on continue à vérifier les autres symboles
                    // au lieu de retourner immédiatement false
                    return false;
                }
            }
        }
    
        return true;
    }
    
    
    

    public boolean estDeterministe() {
        for (String etat : etats) {
            Map<String, Set<String>> transitionEtat = transitions.getOrDefault(etat, Collections.emptyMap());
    
            // Vérifier le nombre de transitions sortantes pour chaque symbole
            for (String symbole : alphabet) {
                Set<String> etatsDestination = transitionEtat.getOrDefault(symbole, Collections.emptySet());
                
                // Si un symbole a plus d'une transition sortante, l'automate n'est pas déterministe
                if (etatsDestination.size() > 1) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public Automata determiniser() {
        Automata automateDeterministe = new Automata(nom + "_deterministe");
    
        // Initialisation de l'état initial de l'automate déterministe avec l'ensemble des états atteignables depuis les états initiaux
        Set<String> etatsInitiauxDeterministes = epsilonFermeture(etatsInitiaux);
        automateDeterministe.ajouterEtatInitial(String.join(",", etatsInitiauxDeterministes));
        
        // Map pour stocker les nouveaux états de l'automate déterministe et leurs transitions
        Map<Set<String>, Map<String, Set<String>>> nouveauxEtatsTransitions = new HashMap<>();
        
        // File pour stocker les nouveaux états à explorer
        Queue<Set<String>> etatsAExplorer = new LinkedList<>();
        etatsAExplorer.add(etatsInitiauxDeterministes);
    
        while (!etatsAExplorer.isEmpty()) {
            Set<String> etatsCourants = etatsAExplorer.poll();
    
            for (String symbole : alphabet) {
                Set<String> etatsSuivants = new HashSet<>();
                for (String etatCourant : etatsCourants) {
                    Map<String, Set<String>> transitionEtat = transitions.getOrDefault(etatCourant, Collections.emptyMap());
                    Set<String> etatsDestination = transitionEtat.getOrDefault(symbole, Collections.emptySet());
                    etatsSuivants.addAll(epsilonFermeture(etatsDestination));
                }
    
                if (!etatsSuivants.isEmpty()) {
                    if (!nouveauxEtatsTransitions.containsKey(etatsSuivants)) {
                        etatsAExplorer.add(etatsSuivants);
                        nouveauxEtatsTransitions.put(etatsSuivants, new HashMap<>());
                    }
                    Map<String, Set<String>> transitionEtat = nouveauxEtatsTransitions.get(etatsSuivants);
                    transitionEtat.put(symbole, etatsSuivants);
                }
            }
        }
    
        // Convertir les nouveaux états en noms uniques pour les états de l'automate déterministe
        Map<Set<String>, String> etatsNoms = new HashMap<>();
        for (Set<String> etatsDeterministes : nouveauxEtatsTransitions.keySet()) {
            String etatNom = String.join(",", etatsDeterministes);
            etatsNoms.put(etatsDeterministes, etatNom);
            automateDeterministe.ajouterEtat(etatNom);
        }
    
        // Ajouter les transitions à l'automate déterministe en utilisant les noms des nouveaux états
        for (Map.Entry<Set<String>, Map<String, Set<String>>> entry : nouveauxEtatsTransitions.entrySet()) {
            Set<String> etatsDeterministes = entry.getKey();
            String etatNom = etatsNoms.get(etatsDeterministes);
            Map<String, Set<String>> transitionEtat = entry.getValue();
            for (Map.Entry<String, Set<String>> transEntry : transitionEtat.entrySet()) {
                String symbole = transEntry.getKey();
                Set<String> etatsSuivants = transEntry.getValue();
                String etatsSuivantsNom = etatsNoms.get(etatsSuivants);
                automateDeterministe.ajouterTransition(etatNom, symbole, etatsSuivantsNom);
            }
        }
    
        // Marquer les états finaux de l'automate déterministe
        for (Set<String> etatsDeterministes : etatsNoms.keySet()) {
            for (String etat : etatsDeterministes) {
                if (etatsFinaux.contains(etat)) {
                    automateDeterministe.ajouterEtatFinal(etatsNoms.get(etatsDeterministes));
                    break;
                }
            }
        }
    
        return automateDeterministe;
    }
    
    private Set<String> epsilonFermeture(Set<String> etats) {
        Set<String> fermeture = new HashSet<>(etats);
        Queue<String> etatsAExplorer = new LinkedList<>(etats);
    
        while (!etatsAExplorer.isEmpty()) {
            String etatCourant = etatsAExplorer.poll();
            Map<String, Set<String>> transitionEtat = transitions.getOrDefault(etatCourant, Collections.emptyMap());
            Set<String> epsilonTransitions = transitionEtat.getOrDefault("", Collections.emptySet());
            for (String etatSuivant : epsilonTransitions) {
                if (!fermeture.contains(etatSuivant)) {
                    fermeture.add(etatSuivant);
                    etatsAExplorer.add(etatSuivant);
                }
            }
        }
    
        return fermeture;
    }
    
    public void ajouterTransitionsEpsilon(Set<String> etatsSources, String etatDestination) {
        for (String etatSource : etatsSources) {
            ajouterTransition(etatSource, "", etatDestination);
        }
    }
    
    
    
    public RegularExpressionNode parseExpression(String expression) {
        Stack<RegularExpressionNode> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '(') {
                operatorStack.push("(");
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    String operator = operatorStack.pop();
                    RegularExpressionNode rightOperand = operandStack.pop();
                    RegularExpressionNode leftOperand = operandStack.pop();
                    operandStack.push(createOperationNode(operator, leftOperand, rightOperand));
                }
                operatorStack.pop(); // Pop la "("
            } else if (isOperator(c)) {
                String currentOperator = String.valueOf(c);
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(currentOperator)) {
                    String operator = operatorStack.pop();
                    RegularExpressionNode rightOperand = operandStack.pop();
                    RegularExpressionNode leftOperand = operandStack.pop();
                    operandStack.push(createOperationNode(operator, leftOperand, rightOperand));
                }
                operatorStack.push(currentOperator);
            } else {
                operandStack.push(new RegularExpressionNode("", String.valueOf(c)));
            }
        }
        
        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            RegularExpressionNode rightOperand = operandStack.pop();
            RegularExpressionNode leftOperand = operandStack.pop();
            operandStack.push(createOperationNode(operator, leftOperand, rightOperand));
        }
        
        return operandStack.pop();
    }
    
    private RegularExpressionNode createOperationNode(String operation, RegularExpressionNode left, RegularExpressionNode right) {
        RegularExpressionNode operationNode = new RegularExpressionNode(operation, "");
        operationNode.setLeftChild(left);
        operationNode.setRightChild(right);
        return operationNode;
    }
    
    private boolean isOperator(char c) {
        return c == '|' || c == '*' || c == '+';
    }
    
    private int precedence(String operator) {
        if (operator.equals("|")) return 1;
        if (operator.equals("*") || operator.equals("+")) return 2;
        return 0;
    }
    
    
    
    

}
