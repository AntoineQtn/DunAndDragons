package item.offensiveequipment;

import item.ICollectible;

public abstract class Spell extends OffensiveEquipment implements ICollectible {
    private String name;
    private int damage;
    private int weight;

    public Spell(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public void displayStats() {
        System.out.println("=== Spell ===");
        System.out.println("Name   : " + name);
        System.out.println("Damage : +" + damage);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public String getName() {return name;}
    public int getDamage() {return damage;}

}
