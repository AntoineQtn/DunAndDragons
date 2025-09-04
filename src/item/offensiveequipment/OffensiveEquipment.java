package item.offensiveequipment;

abstract public class OffensiveEquipment {

    protected String name;
    protected int damage;

    abstract public int getWeight();
    abstract public void displayStats();
    abstract public String getName();
    abstract public int getDamage();

}
