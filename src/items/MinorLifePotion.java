package items;

public class MinorLifePotion extends Potion {

    public MinorLifePotion(String name, int life) {
        super(name, life);
    }

    public int minorBonus() {
        System.out.println(getName() + " Gives +" + getBonusLife() + " life point!");
        return getBonusLife();
    }

    public static void main(String[] args) {
        MinorLifePotion minorlifepotion = new MinorLifePotion("Minor Life Potion", 2);

        minorlifepotion.displayStats();
        minorlifepotion.minorBonus();
    }
}