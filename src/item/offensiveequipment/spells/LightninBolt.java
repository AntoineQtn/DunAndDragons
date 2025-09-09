package item.offensiveequipment.spells;


import item.bag.Bag;
import item.offensiveequipment.Spell;

public class LightninBolt extends Spell {
    public LightninBolt(String name, int damage) {
        super(name, damage);
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void onCollect(Bag bag) {

    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void onCollect() {

    }
}
