package item.bag;



public class LargeBag extends Bag {
    public LargeBag() {
        super("Large Bag of Holding", "A powerful magical bag with tremendous storage capacity", 15, 250.0);
    }

    @Override
    protected double getEmptyWeight() {
        return 1.0; // Très léger grâce à la magie puissante
    }

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You found a legendary " + getName() + "! This will revolutionize your inventory management!");
    }

    @Override
    public void onCollect() {

    }
}