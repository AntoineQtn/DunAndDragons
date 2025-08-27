package item;

/**
 * Represents an item that can be collected within the game.
 * This interface defines the minimum requirements for any collectible object,
 * providing methods to retrieve its name and weight.
 */
public interface Collectible {
    String getName();
    int getWeight();
}
