package game.dice;

import characters.Player;

public interface Dice {
    String getName();
    String getDescription();

    int roll(Player player);
}
