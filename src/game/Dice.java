package game;

public class Dice {
    private int diceValue;


    public Dice() {
        this.diceValue = 1;
    }

    /**
     * Lancer un dé à 6 faces
     * Doit retourner un int pour être compatible
     */
    public int rollDice() {
        diceValue = (int)(Math.random() * 6) + 1;
        return diceValue;
    }

    public void rollAndDisplay() {
        int result = rollDice();
        System.out.println(" Le dé affiche : " + result);
    }

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

        System.out.println("\n--- Throwing dice ---");
        for (int i = 0; i < 5; i++) {
            gameDice.rollAndDisplay();
        }

        // Test des getters/setters
        System.out.println("\n--- Test getter/setter ---");
        gameDice.setDiceValue(4);
        gameDice.displayDice();

    }
}