package item.offensiveequipment;

import item.ICollectible;
import item.bag.Bag;

public abstract class Weapon extends OffensiveEquipment implements ICollectible {
    private String name;
    private int damage;
    private int weight;

    public Weapon ( String name, int damage ) {
        super();
        this.name = name;
        this.damage = damage;
        this.weight = weight;

    }

    public void displayStats() {
        System.out.println("=== Weapon ===");
        System.out.println("Name   : " + name);
        System.out.println("Damage : +" + damage);
        System.out.println("Weight : +" + weight);

    }

    @Override
    public void onCollect(Bag bag){
        bag.addItem(this);
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public double getWeight() { return weight; }

}
