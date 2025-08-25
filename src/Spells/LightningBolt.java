package Spells;

public class LightningBolt extends Spells {
    private String name;
    private int damage;

    public LightningBolt(String name, int damage) {
        super(name, damage);
        this.name = name;
        this.damage = damage;
    }
}
