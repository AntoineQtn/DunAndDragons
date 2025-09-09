package item.bag;

public class BasicBag extends Bag {
    public BasicBag() {
        super("Basic Bag", "A simple leather bag for carrying basic items", 5, 50.0);
    }

    @Override
    protected double getEmptyWeight() {
        return 2.0; // Le sac vide pèse 2 livres
    }

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You start your adventure with a " + getName() + "!");
    }

    @Override
    public void onCollect() {

    }
}