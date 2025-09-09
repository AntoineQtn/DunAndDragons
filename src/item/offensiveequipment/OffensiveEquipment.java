package item.offensiveequipment;

import item.ICollectible;
import item.Item;
import item.bag.Bag;

abstract public class OffensiveEquipment extends Item implements ICollectible {

    private String name;
    private int damage;

    public OffensiveEquipment() {
        super(String name );

    }


    abstract public double getWeight();
    abstract public void displayStats();
    abstract public String getName();
    abstract public int getDamage();

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You grip the " + getName() + " firmly. It feels well-balanced in your hands.");
    }

}
