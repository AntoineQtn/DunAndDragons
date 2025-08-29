package item.defensiveequipment.potion;

import item.defensiveequipment.Potion;

public class MinorLifePotion extends Potion {
    public MinorLifePotion(String name, int life) {
        super(name, life);
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
