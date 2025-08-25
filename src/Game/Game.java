package Game;

import Characters.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board gameBoard;
    private PlayableCharacter player;
    private Dice gameDice;
    private Menu gameMenu;
    private List<UnplayableCharacter> UnplayableCharacters;
    private boolean gameRunning;
    private boolean gameWon;

    public Game() {
        this.gameDice = new Dice();
        this.gameMenu = new Menu();
        this.UnplayableCharacters = new ArrayList<>();
        this.gameRunning = false;
        this.gameWon = false;
    }

    /**
     * Lance le jeu principal
     */
    public void startGame() {
        gameMenu.displayWelcome();

        // Création du personnage
        player = createCharacter();

        // Création du plateau
        gameBoard = createBoard();

        // Placement des éléments sur le plateau
        populateBoard();

        // Boucle principale du jeu
        gameRunning = true;
        gameLoop();

        // Fin de partie
        displayGameEnd();
    }

    /**
     * Création du personnage joueur
     */
    private PlayableCharacter createCharacter() {
        gameMenu.displayMessage("\n=== CHARACTER CREATION ===");

        String name = gameMenu.askForString("Enter your name  ");

        gameMenu.displayMessage("\nChoose your class :");
        gameMenu.displayMessage("1. Warrior (Life: 10, Attack: 5)");
        gameMenu.displayMessage("2. Wizard (Life: 6, Attack: 8)");

        int classChoice = gameMenu.askForInt("Your choice (1-2)", 1, 2);

        PlayableCharacter character;
        switch (classChoice) {
            case 1:
                character = new Warrior(name);
                break;
            case 2:
                character = new Wizard(name);
                break;
            default:
                character = new Warrior(name);
        }

        gameMenu.displayMessage("\nCharacter created successfully !");
        character.displayStats();
        return character;
    }

    /**
     * Création du plateau de jeu
     */
    private Board createBoard() {
        gameMenu.displayMessage("\n=== CRÉATION DU DONJON ===");

        String[] dungeonNames = {
                "Donjon des Ombres", "Crypte Maudite", "Labyrinthe Perdu",
                "Forteresse Abandonnée", "Temple Oublié", "Caverne du Dragon"
        };
        String dungeonName = dungeonNames[(int)(Math.random() * dungeonNames.length)];

        Board board = new Board(dungeonName); // plus besoin de passer la taille
        gameMenu.displayMessage("Donjon créé : " + dungeonName + " (64 cases)");
        return board;
    }

    /**
     * Peuple le plateau avec des ennemis, coffres et PNJ
     */
    private void populateBoard() {
        int boardSize = gameBoard.getLength();

        // Placement des coffres (environ 20% du plateau)
        int numChests = Math.max(2, boardSize / 5);
        for (int i = 0; i < numChests; i++) {
            gameBoard.placeChestCase();
        }

        // Placement des ennemis (environ 25% du plateau)
        int numEnemies = Math.max(2, boardSize / 4);
        for (int i = 0; i < numEnemies; i++) {
            int enemyPos = gameBoard.placeEnnemy();
            if (enemyPos != -1) {
                UnplayableCharacter enemy = createRandomEnemy();
                UnplayableCharacters.add(enemy);
            }
        }

//        // Placement de quelques PNJ (environ 10% du plateau)
//        int numNPCs = Math.max(1, boardSize / 10);
//        for (int i = 0; i < numNPCs; i++) {
//            // Pour cet exemple, on ne place pas visuellement les PNJ sur le board
//            // mais on pourrait l'ajouter plus tard
//            npcs.add(createRandomNPC());
//        }

        gameMenu.displayMessage("Plateau peuplé : " + numChests + " coffres, " +
                numEnemies + " ennemis, ");
    }

    /**
     * Crée un ennemi aléatoire
     */
    private UnplayableCharacter createRandomEnemy() {
        int index = (int)(Math.random() * 3);

        switch (index) {
            case 0: return new Goblin();
            case 1: return new Dragon();
            case 2: return new Sorcerer();
            default: return new Goblin();
        }
    }

    /**
     * Boucle principale du jeu
     */
    private void gameLoop() {
        while (gameRunning && player.isAlive()) {
            // Affichage de l'état actuel
            gameMenu.displayMessage("\n" + "=".repeat(50));
            gameBoard.displayBoard();
            player.displayStats();

            // Menu des actions
            gameMenu.displayMessage("\nWhat do you want to do ?");
            gameMenu.displayMessage("1. Throw the dice and move");
            gameMenu.displayMessage("2. See my stats");
            gameMenu.displayMessage("3. Quit the game");

            int choice = gameMenu.askForInt("Your choice", 1, 3);

            switch (choice) {
                case 1:
                    playerMove();
                    break;
                case 2:
                    player.displayStats();
                    break;
                case 3:
                    gameRunning = false;
                    gameMenu.displayMessage("Thanks for playing !");
                    break;
            }

            // Vérification des conditions de fin
            checkWinCondition();
        }
    }

    /**
     * Gère le déplacement du joueur
     */
    private void playerMove() {
        gameMenu.displayMessage("\n The dice is thrown...");
        int diceRoll = rollDice();
        gameMenu.displayMessage("Result : " + diceRoll);

        int currentPos = gameBoard.getPlayerPosition();
        int newPos = Math.min(currentPos + diceRoll, gameBoard.getLength() - 1);

        gameBoard.movePlayer(newPos);
        gameMenu.displayMessage(player.getName() + " move from case " +
                currentPos + " to case " + newPos);

        // Vérification de ce qui se trouve sur la case
        handleCaseEvent();
    }

    /**
     * Gère les événements selon le type de case
     */
    private void handleCaseEvent() {
        char caseType = gameBoard.checkPlayerPosition();
        int playerPos = gameBoard.getPlayerPosition();

        switch (caseType) {
            case 'C':
                handleChestEvent(playerPos);
                break;
            case 'E':
                handleEnemyEvent(playerPos);
                break;
            case '.':
                gameMenu.displayMessage("La case is empty, you can go");
                break;
        }
    }

    /**
     * Gère l'événement coffre
     */
    private void handleChestEvent(int position) {
        gameMenu.displayMessage("\n You've found a chest !");

        int eventType = (int)(Math.random() * 2);
        switch (eventType) {
            case 0:
                player.getWeapon();
                break;
            case 1:
                player.getPotion();
                break;
        }

        gameBoard.removeChest(position);
    }

    /**
     * Gère l'événement combat
     */
    private void handleEnemyEvent(int position) {
        if (UnplayableCharacters.isEmpty()) return;

        UnplayableCharacter enemy = UnplayableCharacters.get((int)(Math.random() * UnplayableCharacters.size()));
        gameMenu.displayMessage("\n⚔Un " + enemy.getName() + " attacks !");

        // Combat simple
        boolean playerWins = handleCombat(enemy);

        if (playerWins) {
            gameMenu.displayMessage("Victory ! You've defeated a " + enemy.getName());
            gameBoard.removeEnemy(position);
            UnplayableCharacters.remove(enemy);
        } else if (!player.isAlive()) {
            gameMenu.displayMessage(" You've been defeated, you lost the game...");
            gameRunning = false;
        }
    }

    /**
     * Système de combat simple
     */
    private boolean handleCombat(UnplayableCharacter enemy) {
        gameMenu.displayMessage("\n=== COMBAT ===");
        enemy.displayStats();

        while (player.isAlive() && enemy.isAlive()) {
            gameMenu.displayMessage("\n What do you want to do ?");
            gameMenu.displayMessage("1. Attack");
            gameMenu.displayMessage("2. Use a potion");

            int choice = gameMenu.askForInt("Your choice", 1, 2);

            if (choice == 1) {
                enemy.takeDamage(player.getAttack());
                if (enemy.isAlive()) {
                    player.takeDamage(enemy.getAttack());
                }
            } else {
                player.getPotion();
                if (enemy.isAlive()) {
                    player.takeDamage(enemy.getAttack());
                }
            }

            gameMenu.askForEnter("Press enter to continue...");
        }

        return !enemy.isAlive();
    }

    /**
     * Vérifie les conditions de victoire
     */
    private void checkWinCondition() {
        if (gameBoard.hasPlayerWon()) {
            gameWon = true;
            gameRunning = false;
        }
    }

    /**
     * Affiche l'écran de fin de partie
     */
    private void displayGameEnd() {
        gameMenu.displayMessage("\n" + "=".repeat(50));
        if (gameWon) {
            gameMenu.displayMessage(" Congratulations !");
            gameMenu.displayMessage(" You've won the game !");
        } else if (!player.isAlive()) {
            gameMenu.displayMessage(" GAME OVER ");
            gameMenu.displayMessage("Your journey has come to an end...");
        } else {
            gameMenu.displayMessage("See you next time !");
        }

        gameMenu.displayMessage("\nStatistiques finales :");
        player.displayStats();
        gameMenu.displayMessage("=".repeat(50));
    }

    /**
     * Méthode héritée pour le dé
     */
    public int rollDice() {
        return gameDice.rollDice();
    }

    // Getters
    public Board getGameBoard() { return gameBoard; }
    public PlayableCharacter getPlayer() { return player; }
    public boolean isGameRunning() { return gameRunning; }
    public boolean isGameWon() { return gameWon; }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
