package enemy;

import characters.Enemy;

/**
 * The Dragon class represents a specific type of enemy in the game.
 * It is an extension of the Enemy class, with predefined attributes
 * for its name, life, and damage. Dragons are powerful enemies with
 * high stats compared to other enemy types.
 */
public class Dragon extends Enemy {

    public Dragon() {
        super("Dragon", 15, 4);
    }
}