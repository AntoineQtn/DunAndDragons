package weapons;

import offensiveequipment.Weapon;

public class Mace extends Weapon {

    public Mace(String name, int damage){
        super(name, damage);

    }

    public void maceAttack() {
        System.out.println( getName() + " attack with its mace and inflict " + getDamage() + " damage!");
    }

    public static void main ( String[] args ) {

        Mace mace = new Mace("Mace", 3);

    }

    @Override
    public int getWeight() {
        return 0;
    }
}
