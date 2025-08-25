package Items;

public class Potion {
    private String name;
    private int life;

    public Potion(String name, int life) {
        this.name = name;
        this.life = life;
    }

    public void drink() {
        System.out.println(name + " drinks a potion!");
        this.life += 20;
    }
}
