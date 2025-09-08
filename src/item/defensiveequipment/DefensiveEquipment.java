package item.defensiveequipment;

import item.ICollectible;

abstract class DefensiveEquipment implements ICollectible {

    private String name;
    private int life;

    protected abstract int getWeight();
    public abstract String getName();
    abstract int getLife();
    abstract void displayStats();

}
