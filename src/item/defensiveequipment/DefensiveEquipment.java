package item.defensiveequipment;

import item.Collectible;

abstract class DefensiveEquipment implements Collectible {

    protected String name;
    protected int life;
    protected int weight;

    protected abstract int getWeight();
    public abstract String getName();
    abstract int getLife();
    abstract void displayStats();

}
