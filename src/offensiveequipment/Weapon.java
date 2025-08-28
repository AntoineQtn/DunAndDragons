package offensiveequipment;

import item.Collectible;

public abstract class Weapon extends OffensiveEquipment implements Collectible {
    private String name;
    private int damage;

    public Weapon ( String name, int damage ) {

        this.name = name;
        this.damage = damage;

    }

    public void displayStats() {
        System.out.println("=== Weapon ===");
        System.out.println("Name   : " + name);
        System.out.println("Damage : +" + damage);
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }

}
