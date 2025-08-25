package spells;

public class Fireball extends BaseSpells {

    public Fireball(String name, int damage) {
        super(name, damage);
    }

    public void fireBallAttack() {
        System.out.println(getName() + " slashes for +" + getBonusDamage() + " damage!");
    }

    public static void main(String[] args) {
        Fireball fireball = new Fireball("Fire spell", 7);

        fireball.displayStats();
        fireball.fireBallAttack();
    }
}