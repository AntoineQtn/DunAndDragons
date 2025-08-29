package item.offensiveequipment.weapons;

import item.offensiveequipment.Weapon;

public class Sword extends Weapon {

    public Sword(String name, int damage){
        super(name, damage);

    }


    @Override
    public int getWeight() {
        return 0;
    }
}
