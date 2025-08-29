package item.defensiveequipment;

import item.Collectible;

abstract public class Potion extends DefensiveEquipment implements Collectible {
    private String name;
    private int life;
    private int weight;

    public Potion( String name, int life) {
        this.name = name;
        this.life = life;
        this.weight = weight;
    }

    @Override
    public void displayStats() {
        System.out.println("=== Potion ===");
        System.out.println("Name   : " + name);
        System.out.println("Life : +" + life);
    }

    public String getName() { return name; }
    public int getLife() { return life; }

}
