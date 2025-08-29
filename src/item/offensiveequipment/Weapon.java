package item.offensiveequipment;

import item.Collectible;

public abstract class Weapon extends OffensiveEquipment implements Collectible {
    private String name;
    private int damage;
    private int weight;

    public Weapon ( String name, int damage ) {
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

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getWeight() { return weight; }

}
