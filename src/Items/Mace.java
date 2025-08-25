package Items;

public class Mace extends Weapon {

    public Mace(String name, int damage) {
        super(name, damage);
    }

    public void slashAttack() {
        System.out.println(getName() + " slashes for +" + getBonusDamage() + " damage!");
    }

    public static void main(String[] args) {
        Sword sword = new Sword("Steel Sword", 5);

        sword.displayStats();
        sword.slashAttack();
    }
}