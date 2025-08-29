package game;

import java.util.*;

import characters.*;
import characters.enemy.Dragon;
import characters.enemy.Goblin;
import characters.enemy.Sorcerer;
import game.exception.InvalidChestContentException;
import game.exception.PlayerOutOfBoardException;
import item.SurpriseChest;
import characters.player.warrior.HeavyWarrior;
import characters.player.warrior.LightWarrior;
import characters.player.wizard.FireWizard;
import characters.player.wizard.IceWizard;


/**
 * The Game class represents the core functionality of a text-based
 * role-playing game. It initializes characters, the game board, enemies,
 * and other elements required to run and play the game.
 * The main gameplay involves navigating a board, encountering enemies,
 * and interacting with chests, all while managing the characters.player's character
 * stats and actions.
 */
public class Game {

    private Board board;
    private Player player;
    private final Dice dice;
    private final Menu menu;
    private final SurpriseChest surprisechest;
    private final List<Enemy> enemies;
    private boolean isGameRunning;
    private boolean isGameWon;
    private Player Warrior;
    private Player Wizard;

    /**
     * Constructs a new instance of the Game class.
     *
     * This constructor initializes the primary components of the game:
     * - A Dice object for game mechanics involving random rolls.
     * - A Menu object for displaying UI messages and handling user input.
     * - A SurpriseChest object for handling surprise rewards.
     * - An ArrayList to hold characters.enemy characters encountered in the game.
     *
     * Additionally, the constructor sets the game's running and winning
     * states to false, indicating that the game has not yet begun.
     */
    public Game() {
        this.dice = new Dice();
        this.menu = new Menu();
        this.surprisechest = new SurpriseChest();
        this.enemies = new ArrayList<>();
        this.isGameRunning = false;
        this.isGameWon = false;
    }

    /**
     * Starts the game by initializing essential components and managing the game flow.
     * This method performs the following sequence of actions:
     * 1. Displays a welcome message to the characters.player.
     * 2. Creates the characters.player's character through a selection process.
     * 3. Initializes and sets up the game board.
     * 4. Populates the board with necessary game elements, such as chests and enemies.
     * 5. Initiates the game loop to manage characters.player moves, events, and progression.
     * 6. Displays the end of the game message based on the game's outcome.
     */
    public void startGame() {
        menu.displayWelcome();
        player = createCharacter();
        board = createBoard();
        populateBoard();
        isGameRunning = true;
        gameLoop();
        displayGameEnd();
    }

    /**
     * Creates a new Player character through an interactive selection process.
     * The method guides the user to define their character's name, class,
     * and subclass based on available options. Characters can be of type
     * Warrior or Wizard, each with two possible subclasses, where each
     * subclass defines specific life and damage stats.
     *
     * @return the created Player character based on user input
     */
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

            character = switch (subClassChoice) {
                case 1 -> new HeavyWarrior(name);
                case 2 -> new LightWarrior(name);
                default -> character;
            };

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

    /**
     * Creates and initializes a new instance of the game board.
     * The board is used to manage the positions of the characters.player, chests, and enemies
     * during the gameplay. Upon creation, the board will have no chests, no enemies,
     * and the characters.player's position will be set to the starting point.
     *
     * @return a newly created Board instance for the game
     */
    private Board createBoard() {
        Board board = new Board();
        return board;
    }

    /**
     * Populates the game board with chests and enemies.
     *
     * This method places a number of chests and enemies on the game board
     * based on its size. It ensures that a minimum of two chests and two
     * enemies are added, with additional objects determined proportionally
     * to the board size.
     *
     * For chests:
     * - Calculates the number of chests to place as the maximum of 2 or one-fifth of the board size.
     * - Randomly places the calculated number of chests at available positions on the board.
     *
     * For enemies:
     * - Calculates the number of enemies to place as the maximum of 2 or one-fourth of the board size.
     * - For each characters.enemy position successfully determined, creates a random characters.enemy and adds it to the characters.enemy list.
     * - Ensures no characters.enemy is placed if no valid positions are available on the board.
     */
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

    /**
     * Creates and returns a randomly generated characters.enemy instance.
     * The method randomly selects one of three characters.enemy types from the following:
     * - Goblin: A basic characters.enemy with moderate stats.
     * - Dragon: A powerful characters.enemy with high stats.
     * - Sorcerer: A magic-based characters.enemy with balanced stats.
     *
     * @return an Enemy instance of either Goblin, Dragon, or Sorcerer, selected randomly
     */
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

    /**
     * Executes the main game loop, controlling the flow of gameplay until the game ends.
     *
     * The game loop repeatedly prompts the characters.player with a menu of actions and processes
     * the characters.player's choice until either the characters.player exits the game or the game concludes.
     *
     * During each iteration of the loop:
     * 1. The current state of the game and characters.player's statistics are displayed on the console.
     * 2. The characters.player is prompted to choose one of three options:
     *    - Throw the dice and move their position on the board.
     *    - View their detailed statistics.
     *    - Exit the game.
     * 3. Based on the characters.player's choice:
     *    - If the characters.player chooses to throw the dice, the `playerMove` method is executed,
     *      which moves the characters.player and processes the resulting board event.
     *    - If the characters.player chooses to view stats, the characters.player's statistics are displayed
     *      using the `displayStats` method.
     *    - If the characters.player chooses to quit, the game ends with an exit message.
     * 4. The game continuously checks for win conditions using the `checkWinCondition` method,
     *    which terminates the loop if the characters.player wins the game.
     *
     * The loop ends if either the `isGameRunning` flag is set to false or the characters.player's
     * `isAlive` method returns false, indicating the game should no longer continue.
     */
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

    /**
     * Executes the characters.player's movement on the game board during their turn by rolling a dice
     * and updating their position accordingly. This method handles cases where the characters.player
     * might move beyond the confines of the board or encounter an event at their new position.
     *
     * Detailed steps:
     * 1. Displays a message indicating the dice roll initiation and the result of the roll.
     * 2. Calculates the new position for the characters.player based on the dice roll.
     * 3. Moves the characters.player to the calculated position unless it surpasses the board limits.
     * 4. Catches movement-related exceptions if the characters.player attempts an invalid move, and:
     *    - Displays relevant error messages and suggested recovery actions.
     *    - Attempts to move the characters.player to the nearest valid position (e.g., the end of the board).
     * 5. Calls the `handleCaseEvent` method to process any event at the characters.player's new position.
     *
     * Exceptions handled:
     * - PlayerOutOfBoardException: Triggers when the characters.player's target position is outside the board.
     *
     * This method ensures proper error handling and updates the game state after the characters.player's movement.
     */
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

    /**
     * Processes the event on the characters.player's current position on the game board.
     *
     * This method checks the type of the case on the board at the characters.player's current position
     * and triggers the corresponding event. The case type is determined using the method
     * `board.checkPlayerPosition`, which returns one of the following values:
     * - 'C': Indicates the characters.player has encountered a chest. The method `handleChestEvent` is invoked.
     * - 'E': Indicates the characters.player has encountered an characters.enemy. The method `handleEnemyEvent` is invoked.
     * - '.': Indicates an empty case. A message is displayed to inform the characters.player that there is no event.
     *
     * The following operations are performed:
     * 1. Retrieves the characters.player's current case type and position using the `board` object.
     * 2. Switches on the case type to determine the appropriate action:
     *    - Calls `handleChestEvent` to process chest interaction for case type 'C'.
     *    - Calls `handleEnemyEvent` to manage combat for case type 'E'.
     *    - Displays a message for case type '.' indicating no event.
     */
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

    /**
     * Handles the event when the characters.player encounters a chest on the game board.
     *
     * This method processes the chest event by performing the following steps:
     * 1. Displays a message informing the characters.player that a chest has been found.
     * 2. Opens the chest via the SurpriseChest class to provide rewards or surprises.
     * 3. Removes the chest from the game board at the specified position.
     *
     * @param position the position of the chest on the game board that the characters.player has encountered
     */
    private void handleChestEvent(int position) {
        menu.displayMessage("\n You've found a chest !");
        try {
            Object loot = SurpriseChest.openChest(player);
            menu.displayMessage("You get a " + loot);
        } catch (InvalidChestContentException e) {
            menu.displayMessage("Invalid chest content !");
        }
        board.removeChest(position);
    }


    /**
     * Handles the event when the characters.player encounters an characters.enemy on the game board.
     *
     * This method manages the combat sequence with a randomly selected characters.enemy from the list
     * of remaining enemies. It determines the outcome of the combat and updates the game
     * state based on whether the characters.player wins or loses the battle. If the characters.player defeats the
     * characters.enemy, the characters.enemy is removed from the game board and the list of enemies. If the
     * characters.player is defeated, the game ends.
     *
     * @param position the position of the characters.enemy on the game board that the characters.player encounters
     */
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

    /**
     * Handles a combat scenario between the characters.player and an characters.enemy.
     * This method manages turns between the characters.player and the characters.enemy, processing
     * actions like attacking and healing, while updating their respective stats.
     *
     * @param enemy the characters.enemy combatant involved in the battle
     * @return true if the characters.player wins the combat and the characters.enemy is defeated,
     *         or false if the characters.player is defeated
     */
    private boolean handleCombat(Enemy enemy) {
        menu.displayMessage("\n=== COMBAT ===");
        player.displayStats();
        enemy.displayStats();

        while (player.isAlive() && enemy.isAlive()) {
            menu.displayMessage("\n--- Your turn ---");
            menu.displayMessage("What do you want to do?");
            menu.displayMessage("1. Attack");
            menu.displayMessage("2. Use a basic item.defensiveequipment.potion");

            int choice = menu.askForInt("Your choice", 1, 2);

            if (choice == 1) {
                menu.displayMessage("\n" + player.getName() + " attacks!");
                enemy.takeDamage(player.getAttack());
            } else {
                menu.displayMessage("\n" + player.getName() + " uses a item.defensiveequipment.potion!");
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

    /**
     * Evaluates the current state of the game to determine if the
     * win condition has been met.
     *
     * If the characters.player has won, the method updates the game state by
     * setting the game as won and stops the game from running.
     */
    private void checkWinCondition() {
        if (board.hasPlayerWon()) {
            isGameWon = true;
            isGameRunning = false;
        }
    }

    /**
     * Displays the end-game message and final statistics to the characters.player.
     *
     * This method checks the outcome of the game and provides feedback to the characters.player
     * based on whether they have won the game, lost due to the characters.player's demise, or
     * finished in another state. After displaying the appropriate message, it outputs
     * the characters.player's final statistics and a separator line for visual formatting.
     *
     * The method interacts with the menu to display messages and the characters.player object to
     * retrieve and display statistics.
     *
     * Game end scenarios handled:
     * 1. Game is won - Displays a congratulatory message.
     * 2. Player is no longer alive - Displays a "Game Over" message.
     * 3. Neither of the above - Displays a farewell message.
     */
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

    /**
     * Rolls a dice and returns the result.
     * This method delegates the dice rolling operation to the Dice object
     * associated with the game. The Dice object generates a random number
     * between 1 and 6, inclusive, to simulate the outcome of a dice roll.
     *
     * @return the result of the dice roll as an integer between 1 and 6
     */
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