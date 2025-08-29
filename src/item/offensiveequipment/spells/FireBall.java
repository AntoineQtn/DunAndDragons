package item.offensiveequipment.spells;

import item.offensiveequipment.Spell;

public class FireBall extends Spell {
    public FireBall(String name, int damage) {
        super(name, damage);
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void onCollect() {

    }
}
