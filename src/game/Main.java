package game;

public class Main {


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