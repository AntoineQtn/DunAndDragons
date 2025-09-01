package item.bag;

import item.Collectible;
import java.util.ArrayList;
import java.util.List;

public class BasicBag extends Bag {

    public BasicBag() {
        super("Basic Bag", "A simple leather bag for carrying basic items", 5);
    }

    @Override
    public void onCollect() {
        System.out.println("You start your adventure with a " + name + "!");
    }
}