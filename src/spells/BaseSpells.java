package spells;

public class BaseSpells {
    private String name;
    private int bonusDamage;

    public BaseSpells(String name, int bonusDamage) {
        this.name = name;
        this.bonusDamage = bonusDamage;
    }

    public String getName() {
        return name;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void displayStats() {
        System.out.println("=== Spell ===");
        System.out.println("Name   : " + name);
        System.out.println("Damage : +"  + bonusDamage);
    }
}
