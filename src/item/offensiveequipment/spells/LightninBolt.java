package item.offensiveequipment.spells;


import item.offensiveequipment.Spell;

public class LightninBolt extends Spell {
    public LightninBolt(String name, int damage) {
        super(name, damage);
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
