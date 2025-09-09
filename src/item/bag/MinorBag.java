package item.bag;

public class MinorBag extends Bag {
    public MinorBag() {
        super("Minor Bag of Holding", "A small magical bag that can hold more than it appears", 8, 100.0);
    }

    @Override
    protected double getEmptyWeight() {
        return 1.5; // Plus léger grâce à la magie
    }

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You acquired a magical " + getName() + "! The inside seems larger than the outside...");
    }

    @Override
    public void onCollect() {

    }
}
