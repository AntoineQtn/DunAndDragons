package defensiveequipment;

import item.Collectible;

abstract public class Potion extends DefensiveEquipment implements Collectible {
    private String name;
    private int life;

    public Potion( String name, int life) {
        this.name = name;
        this.life = life;
    }

    public void displayStats() {
        System.out.println("=== Potion ===");
        System.out.println("Name   : " + name);
        System.out.println("Life : +" + life);
    }

    public String getName() { return name; }
    public int getLife() { return life; }

    public String setName( String name ) {
        this.name = name;
        return name;
    }
    public int setLife ( int life ) {
        this.life = life;
        return life;
    }
}
