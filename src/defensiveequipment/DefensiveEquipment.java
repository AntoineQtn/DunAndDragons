package defensiveequipment;

abstract class DefensiveEquipment {

    protected String name;
    protected int life;

    abstract String getName();
    abstract int getLife();
    abstract void displayStats();

}
