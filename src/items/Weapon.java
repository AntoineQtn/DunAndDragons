package items;

public class Weapon {
    private String name;
    private int bonusDamage;

    public Weapon(String name, int bonusDamage) {
        this.name = name;
        this.bonusDamage = bonusDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void displayStats() {
        System.out.println("=== Weapon ===");
        System.out.println("Name   : " + name);
        System.out.println("Damage : +" + bonusDamage);
    }
}
