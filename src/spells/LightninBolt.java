package spells;


public class LightninBolt extends Spell {
    public LightninBolt(String name, int damage) {
        super(name, damage);
    }
    public void lightninBoltAttack() {
        System.out.println( getName() + " attack with a lightnin bolt and inflict " + getDamage() + " damage!");
    }

    public static void main ( String[] args ) {

        LightninBolt lightninbolt = new LightninBolt("Lightnin Bolt", 5);

    }

}
