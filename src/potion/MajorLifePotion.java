package potion;

import defensiveequipment.Potion;

public class MajorLifePotion extends Potion {
    public MajorLifePotion(String name, int life) {
        super(name, life);
    }
    public int majorBonus() {
        System.out.println(getName() + " Gives +" + getLife() + " life point!");
        return getLife();
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
