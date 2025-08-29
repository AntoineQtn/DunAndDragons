package item.defensiveequipment.potion;

import item.defensiveequipment.Potion;

public class MajorLifePotion extends Potion {

    public MajorLifePotion(String name, int life) {
        super(name, life);
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
