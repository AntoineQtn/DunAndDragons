package spells;

public class LightningBolt extends BaseSpells {

    public LightningBolt(String name, int damage) {
        super(name, damage);
    }

    public void lightningBoltAttack() {
        System.out.println(getName() + " slashes for +" + getBonusDamage() + " damage!");
    }

    public static void main(String[] args) {
        LightningBolt lightninbolt = new LightningBolt("Lightning Bolt", 2);

        lightninbolt.displayStats();
        lightninbolt.lightningBoltAttack();
    }
}