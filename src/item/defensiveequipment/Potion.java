package item.defensiveequipment;

import item.Collectible;

abstract public class Potion extends DefensiveEquipment implements Collectible {
    protected String name;
    protected int life;
    protected int weight;
    protected String description;

    public Potion(String name, int life, int weight, String description) {
        this.name = name;
        this.life = life;
        this.weight = weight;
        this.description = description;
    }

    @Override
    public void displayStats() {
        System.out.println("=== Potion ===");
        System.out.println("Name   : " + name);
        System.out.println("Life   : +" + life);
        System.out.println("Weight : " + weight);
        System.out.println("Desc   : " + description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void onCollect() {
        System.out.println("You collected a " + name + "! " + description);
    }

    public int getLife() {
        return life;
    }

    public int getWeight() {
        return weight;
    }

    public abstract void usePotion(characters.Player player);

    public boolean canBeUsed(characters.Player player) {
        return player.getLife() < player.getMaxLife(); // Assuming Player has getMaxLife()
    }
}
