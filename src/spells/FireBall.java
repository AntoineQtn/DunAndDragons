package spells;

import offensiveequipment.Spell;

public class FireBall extends Spell {
    public FireBall(String name, int damage) {
        super(name, damage);
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
