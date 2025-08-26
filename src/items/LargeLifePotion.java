package items;

public class LargeLifePotion extends Potion {

    public LargeLifePotion(String name, int life) {
        super(name, life);
    }

    public int largeBonus() {
        System.out.println(getName() + " Gives +" + getBonusLife() + " life point!");
        return getBonusLife();
    }


    public static void main(String[] args) {
        LargeLifePotion largelifepotion = new LargeLifePotion("Large Life Potion", 5);

        largelifepotion.displayStats();
        largelifepotion.largeBonus();
    }
}