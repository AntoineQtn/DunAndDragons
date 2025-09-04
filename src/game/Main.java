package game;

/**
 * Point d'entrée principal du jeu Donjons et Dragons.
 * Cette classe contient la méthode main qui initialise et démarre le jeu.
 */
public class Main {

    /**
     * Méthode principale qui lance le jeu Donjons et Dragons.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        System.out.println("=== WELCOME TO DUNGEONS AND DRAGONS ===");
        System.out.println("Game initialization...\n");

        try {
            Game game = new Game();
            game.startGame();
        } catch (Exception e) {
            System.err.println("A critical error occured while launching.\n :");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== GAME END ===");
        System.out.println("Thanks for playing !");
    }
}