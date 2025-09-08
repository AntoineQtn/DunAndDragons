package game.dice;

import characters.Player;

public interface IDice {
    String getName();
    String getDescription();

    int roll(Player player);
}
