package item.offensiveequipment.weapons;

import item.offensiveequipment.Weapon;

public class Mace extends Weapon {

    public Mace(String name, int damage){
        super(name, damage);

    }

    @Override
    public int getWeight() {
        return 0;
    }
}
