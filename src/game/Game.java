package game;

import java.util.*;

import characters.*;
import enemy.Dragon;
import enemy.Goblin;
import enemy.Sorcerer;
import exception.PlayerOutOfBoardException;
import item.SurpriseChest;
import warrior.HeavyWarrior;
import warrior.LightWarrior;
import wizard.FireWizard;
import wizard.IceWizard;

public class Game {

    private Board board;
    private Player player;
    private Dice dice;
    private Menu menu;
    private SurpriseChest surprisechest;
    private List<Enemy> enemies;
    private boolean isGameRunning;
    private boolean isGameWon;
    private Player Warrior;
    private Player Wizard;

    public Game() {
        this.dice = new Dice();
        this.menu = new Menu();
        this.surprisechest = new SurpriseChest();
        this.enemies = new ArrayList<>();
        this.isGameRunning = false;
        this.isGameWon = false;
    }

    public void startGame() {
        menu.displayWelcome();
        player = createCharacter();
        board = createBoard();
        populateBoard();
        isGameRunning = true;
        gameLoop();
        displayGameEnd();
    }

    private Player createCharacter() {
        menu.displayMessage("\n=== CHARACTER CREATION ===");
        String name = menu.askForString(" Enter your name ");
        menu.displayMessage("\nChoose your class :");
        menu.displayMessage("1. Warrior ");
        menu.displayMessage("2. Wizard ");

        int classChoice = menu.askForInt("Your choice (1-2)", 1, 2);

        Player character = null;

        if (classChoice == 1) {
            menu.displayMessage("\nChoose your subclass :");
            menu.displayMessage("1. Heavy Warrior (life : 10; damage : 5)");
            menu.displayMessage("2. Light Warrior (life : 5; damage : 8)");

            int subClassChoice = menu.askForInt("Your choice (1-2)", 1, 2);

            switch (subClassChoice) {
                case 1:
                    character = new HeavyWarrior(name);
                    break;
                case 2:
                    character = new LightWarrior(name);
                    break;
            }

        } else if (classChoice == 2) { // Wizard
            menu.displayMessage("\nChoose your subclass :");
            menu.displayMessage("1. Fire Wizard (life : 10; damage : 5)");
            menu.displayMessage("2. Ice Wizard (life : 5; damage : 12)");

            int subClassChoice = menu.askForInt("Your choice (1-2)", 1, 2);

            switch (subClassChoice) {
                case 1:
                    character = new FireWizard(name);
                    break;
                case 2:
                    character = new IceWizard(name);
                    break;
            }
        }

        menu.displayMessage("\nCharacter created successfully !");
        character.displayStats();
        return character;
    }

    private Board createBoard() {
        Board board = new Board();
        return board;
    }

    private void populateBoard() {
        int boardSize = board.getLength();

        int numChests = Math.max(2, boardSize / 5);
        for (int i = 0; i < numChests; i++) {
            board.placeChestCase();
        }

        int numEnemies = Math.max(2, boardSize / 4);
        for (int i = 0; i < numEnemies; i++) {
            int enemyPos = board.placeEnemy();
            if (enemyPos != -1) {
                Enemy enemy = createRandomEnemy();
                enemies.add(enemy);
            }
        }
    }


    public Enemy createRandomEnemy() {
        int index = (int) (Math.random() * 3);

        switch (index) {
            case 0:
                return new Goblin();
            case 1:
                return new Dragon();
            case 2:
                return new Sorcerer();
            default:
                return new Goblin();
        }
    }

    public void gameLoop() {
        while (isGameRunning && player.isAlive()) {

            menu.displayMessage("\n" + "=".repeat(50));
            System.out.println(player.toString());
            menu.displayMessage("\nWhat do you want to do ?");
            menu.displayMessage("1. Throw the dice and move");
            menu.displayMessage("2. See my stats");
            menu.displayMessage("3. Quit the game");

            int choice = menu.askForInt("Your choice", 1, 3);

            switch (choice) {
                case 1:
                    playerMove();
                    break;
                case 2:
                    player.displayStats();
                    break;
                case 3:
                    isGameRunning = false;
                    menu.displayMessage("Thanks for playing !");
                    break;
            }

            checkWinCondition();
        }
    }

    private void playerMove() {
        menu.displayMessage("\n The dice is thrown...");
        int diceRoll = rollDice();
        menu.displayMessage("Result : " + diceRoll);

        int currentPos = board.getPlayerPosition();
        int newPos = Math.min(currentPos + diceRoll, board.getLength() - 1);

        try {
            board.movePlayer(newPos);
            menu.displayMessage(player.getName() + " moves from case " +
                    currentPos + " to case " + newPos);
        } catch (PlayerOutOfBoardException e) {
            menu.displayMessage("Movement Error!");
            menu.displayMessage(e.getDetailedErrorMessage());

            int safePosition = e.getSuggestedPosition();
            try {
                board.movePlayer(safePosition);
                menu.displayMessage("Player moved to the end of the board (position " + safePosition + ")");
            } catch (PlayerOutOfBoardException ex) {
                menu.displayMessage("Critical error: " + ex.getMessage());
            }
        }

        handleCaseEvent();
    }

    private void handleCaseEvent() {
        char caseType = board.checkPlayerPosition();
        int playerPos = board.getPlayerPosition();

        switch (caseType) {
            case 'C':
                handleChestEvent(playerPos);
                break;
            case 'E':
                handleEnemyEvent(playerPos);
                break;
            case '.':
                menu.displayMessage("The case is empty, you can go");
                break;
        }
    }

    private void handleChestEvent(int position) {
        menu.displayMessage("\n You've found a chest !");
        SurpriseChest.openChest(player);
        board.removeChest(position);
    }

    private void handleEnemyEvent(int position) {
        if (enemies.isEmpty()) return;

        Enemy enemy = enemies.get((int) (Math.random() * enemies.size()));
        menu.displayMessage("\n " + enemy.getName() + " attacks !");

        boolean playerWins = handleCombat(enemy);

        if (playerWins) {
            menu.displayMessage("Victory ! You've defeated a " + enemy.getName());
            board.removeEnemy(position);
            enemies.remove(enemy);
        } else if (!player.isAlive()) {
            menu.displayMessage(" You've been defeated, you lost the game...");
            isGameRunning = false;
        }
    }

    private boolean handleCombat(Enemy enemy) {
        menu.displayMessage("\n=== COMBAT ===");
        player.displayStats();
        enemy.displayStats();

        while (player.isAlive() && enemy.isAlive()) {
            menu.displayMessage("\n--- Your turn ---");
            menu.displayMessage("What do you want to do?");
            menu.displayMessage("1. Attack");
            menu.displayMessage("2. Use a basic potion");

            int choice = menu.askForInt("Your choice", 1, 2);

            if (choice == 1) {
                menu.displayMessage("\n" + player.getName() + " attacks!");
                enemy.takeDamage(player.getAttack());
            } else {
                menu.displayMessage("\n" + player.getName() + " uses a potion!");
                player.getPotion();
            }

            if (!enemy.isAlive()) {
                break;
            }

            menu.displayMessage("\n--- Enemy turn ---");
            menu.displayMessage(enemy.getName() + " attacks!");
            player.takeDamage(enemy.getAttack());

            menu.displayMessage("\n--- Current Status ---");
            menu.displayMessage(player.getName() + ": " + player.getLife() + " HP");
            menu.displayMessage(enemy.getName() + ": " + enemy.getLife() + " HP");

            menu.askForEnter("Press enter to continue...");
        }

        return player.isAlive() && !enemy.isAlive();
    }

    private void checkWinCondition() {
        if (board.hasPlayerWon()) {
            isGameWon = true;
            isGameRunning = false;
        }
    }

    private void displayGameEnd() {
        if (isGameWon) {
            menu.displayMessage(" Congratulations !");
            menu.displayMessage(" You've won the game !");
        } else if (!player.isAlive()) {
            menu.displayMessage(" GAME OVER ");
            menu.displayMessage("Your journey has come to an end...");
        } else {
            menu.displayMessage("See you next time !");
        }

        menu.displayMessage("\nFinal stats :");
        player.displayStats();
        menu.displayMessage("=".repeat(50));
    }

    public int rollDice() {
        return dice.rollDice();
    }

    public Board getGameBoard() { return board; }
    public Player getPlayer() { return player; }
    public boolean isGameRunning() { return isGameRunning; }
    public boolean isGameWon() { return isGameWon; }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}