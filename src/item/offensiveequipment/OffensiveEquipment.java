package item.offensiveequipment;

abstract public class OffensiveEquipment {

    private String name;
    private int damage;

    abstract public int getWeight();
    abstract public void displayStats();
    abstract public String getName();
    abstract public int getDamage();


}
