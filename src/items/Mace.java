package items;

public class Mace extends Weapon {

    public Mace(String name, int damage) {
        super(name, damage);
    }

    public void maceAttack() {
        System.out.println(getName() + " slashes for +" + getBonusDamage() + " damage!");
    }

    public static void main(String[] args) {
        Mace mace = new Mace("Steel Mace", 3);

        mace.displayStats();
        mace.maceAttack();
    }
}