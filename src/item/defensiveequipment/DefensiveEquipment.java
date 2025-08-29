package item.defensiveequipment;

abstract class DefensiveEquipment {

    protected String name;
    protected int life;
    protected int weight;

    protected abstract int getWeight();
    abstract String getName();
    abstract int getLife();
    abstract void displayStats();

}
