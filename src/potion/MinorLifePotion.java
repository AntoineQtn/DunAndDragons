package potion;

import defensiveequipment.Potion;

public class MinorLifePotion extends Potion {
    public MinorLifePotion(String name, int life) {
        super(name, life);
    }
    public int minorBonus() {
        System.out.println(getName() + " Gives +" + getLife() + " life point!");
        return getLife();
    }
}
