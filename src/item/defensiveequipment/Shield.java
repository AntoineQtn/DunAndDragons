package item.defensiveequipment;

import item.ICollectible;

public abstract class Shield extends DefensiveEquipment implements ICollectible {
    private String name;
    private int life;
    private int weight;

    public Shield( String name, int life) {
        this.name = name;
        this.life = life;
    }

    public void displayStats() {
        System.out.println("=== Shield ===");
        System.out.println("Name   : " + name);
        System.out.println("Life : +" + life);
        System.out.println("Weight : +" + weight);
    }

    public String getName() { return name; }
    public int getLife() { return life; }
    public int getWeight() { return weight; }


}
