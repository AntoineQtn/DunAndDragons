package defensiveequipment;

public class Shield extends DefensiveEquipment {
    private String name;
    private int life;

    public Shield( String name, int life) {
        this.name = name;
        this.life = life;
    }

    public void displayStats() {
        System.out.println("=== Shield ===");
        System.out.println("Name   : " + name);
        System.out.println("Life : +" + life);
    }

    public String getName() { return name; }
    public int getLife() { return life; }

    public String setName( String name ) {
        this.name = name;
        return name;
    }
    public int setLife ( int life ) {
        this.life = life;
        return life;
    }
}
