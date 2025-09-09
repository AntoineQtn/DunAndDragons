package item.offensiveequipment.weapons;

import item.bag.Bag;
import item.offensiveequipment.Weapon;

public class Sword extends Weapon {

    public Sword(String name, int damage){
        super(name, damage);

    }


    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void onCollect() {

    }

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You grip the " + getName() + " firmly. It feels well-balanced in your hands.");
    }
}
