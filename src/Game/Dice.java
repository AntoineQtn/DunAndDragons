package Game;

public class Dice {
    private int diceValue;

    // Constructeur
    public Dice() {
        this.diceValue = 1; // Valeur par défaut
    }

    /**
     * Lancer un dé à 6 faces
     * Doit retourner un int pour être compatible
     */
    public int rollDice() {
        // Génère un nombre aléatoire entre 1 et 6 (dé classique)
        diceValue = (int)(Math.random() * 6) + 1;
        return diceValue;
    }

    // ... existing code ...
    public void rollAndDisplay() {
        int result = rollDice();
        System.out.println("🎲 Le dé affiche : " + result);
    }

    /**
     * Lance un dé avec un nombre de faces personnalisé
     * @param faces nombre de faces du dé
     * @return résultat du lancer
     */
    public int rollDice(int faces) {
        if (faces < 1) {
            System.out.println("Erreur : Un dé doit avoir au moins 1 face !");
            return 1;
        }
        diceValue = (int)(Math.random() * faces) + 1;
        System.out.println("🎲 Dé " + faces + " faces : " + diceValue);
        return diceValue;
    }


    /**
     * Lance plusieurs dés et retourne la somme
     * @param numberOfDice nombre de dés à lancer
     * @param faces nombre de faces par dé
     * @return somme des résultats
     */
    public int rollMultipleDice(int numberOfDice, int faces) {
        int total = 0;
        System.out.println("🎲 Lancement de " + numberOfDice + " dé(s) à " + faces + " faces :");

        for (int i = 0; i < numberOfDice; i++) {
            int roll = (int)(Math.random() * faces) + 1;
            System.out.println("  Dé " + (i + 1) + " : " + roll);
            total += roll;
        }

        System.out.println("💯 Total : " + total);
        diceValue = total; // Store le total
        return total;
    }

    // Getters et Setters
    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        if (diceValue >= 1 && diceValue <= 6) {
            this.diceValue = diceValue;
        } else {
            System.out.println("Valeur invalide ! Le dé doit être entre 1 et 6.");
        }
    }

    /**
     * Affiche la valeur actuelle du dé
     */
    public void displayDice() {
        System.out.println("Valeur actuelle du dé : " + diceValue);
    }

    public static void main(String[] args) {
        System.out.println("=== Test de la classe Dice ===");

        Dice gameDice = new Dice();

        // Test du lancer simple
        System.out.println("\n--- Lancers simples ---");
        for (int i = 0; i < 5; i++) {
            gameDice.rollAndDisplay();
        }

        // Test de dés personnalisés
        System.out.println("\n--- Dés personnalisés ---");
        gameDice.rollDice(20); // Dé à 20 faces (D&D style)
        gameDice.rollDice(100); // Dé à 100 faces

        // Test de lancers multiples
        System.out.println("\n--- Lancers multiples ---");
        gameDice.rollMultipleDice(2, 6);  // 2d6
        gameDice.rollMultipleDice(3, 8);  // 3d8
        gameDice.rollMultipleDice(1, 20); // 1d20

        // Test des getters/setters
        System.out.println("\n--- Test getter/setter ---");
        gameDice.setDiceValue(4);
        gameDice.displayDice();

        // Test avec valeur invalide
        gameDice.setDiceValue(10); // Devrait afficher une erreur
    }
}