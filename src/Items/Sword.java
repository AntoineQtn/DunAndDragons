package Items;

public class Sword extends Weapon {

    public Sword(String name, int damage) {
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