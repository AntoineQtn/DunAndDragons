package weapons;

import offensiveequipment.Weapon;

public class Sword extends Weapon {

    public Sword(String name, int damage){
        super(name, damage);

    }

    public void swordAttack() {
        System.out.println( getName() + " attack with its sword and inflict " + getDamage() + " damage!");
    }

    public static void main ( String[] args ) {

        Sword sword = new Sword("Sword", 5);

    }

}
