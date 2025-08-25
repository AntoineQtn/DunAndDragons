package items;

public class Potion {
    private String name;
    private int bonusLife;

    public Potion(String name, int bonusLife) {
        this.name = name;
        this.bonusLife = bonusLife;
    }

    public String getName() {
        return name;
    }

    public int getBonusLife() {
        return bonusLife;
    }

    public void displayStats() {
        System.out.println("=== Potion ===");
        System.out.println("Name   : " + name);
        System.out.println("Life : +" + bonusLife);
    }
}
