package item.bag;

public class MinorBag extends Bag {

    public MinorBag() {
        super("Minor Bag", "A reinforced bag that can hold more items than the basic one", 8);
    }

    @Override
    public void onCollect(Bag bag) {

    }

    @Override
    public void onCollect() {
        super.onCollect();
        System.out.println("Your carrying capacity has increased to " + getCapacity() + " items!");
    }
}
