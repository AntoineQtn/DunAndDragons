package item.bag;

import item.Collectible;

public class MinorBag extends Bag {

    public MinorBag() {
        super("Minor Bag", "A reinforced bag that can hold more items than the basic one", 8);
    }

    @Override
    public void onCollect() {
        super.onCollect();
        System.out.println("Your carrying capacity has increased to " + capacity + " items!");
    }
}
