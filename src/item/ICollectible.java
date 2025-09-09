package item;

import item.bag.Bag;

/**
 * Represents an item that can be collected within the game.
 * This interface defines the minimum requirements for any collectible object,
 * providing methods to retrieve its name and weight.
 */

public interface ICollectible {
    double getWeight();
    boolean isStackable();
    int getMaxStackSize();
    void onCollect(Bag bag);
    String getName();
    String getDescription();
//    void onCollect();
}

