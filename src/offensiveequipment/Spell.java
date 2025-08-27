package offensiveequipment;

public class Spell {
    private String name;
    private int damage;

    public Spell(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public void displayStats() {
        System.out.println("=== Spell ===");
        System.out.println("Name   : " + name);
        System.out.println("Damage : +" + damage);
    }

    public String getName() {return name;}
    public int getDamage() {return damage;}

    public String setName(String name){
        this.name = name;
        return name;
    }
    public int setDamage(int damage){
        this.damage = damage;
        return damage;
    }
}
