package items;

public class MajorLifePotion extends Potion {
    public MajorLifePotion(String name, int life) {
        super(name, life);
    }
    public int majorBonus() {
        System.out.println(getName() + " Gives +" + getLife() + " life point!");
        return getLife();
    }
}
