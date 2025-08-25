package items;

public class LargeLifePotion extends Potion {

    public LargeLifePotion(String name, int life) {
        super(name, life);
    }

    public void largeBonus() {
        System.out.println(getName() + " Gives +" + getBonusLife() + " life point!");
    }

    public static void main(String[] args) {
        LargeLifePotion largelifepotion = new LargeLifePotion("Large Life Potion", 5);

        largelifepotion.displayStats();
        largelifepotion.largeBonus();
    }
}