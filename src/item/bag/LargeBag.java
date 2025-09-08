package item.bag;



public class LargeBag extends Bag {

    public LargeBag() {
        super("Large Bag", "A spacious adventurer's backpack made from premium materials", 12);
    }

    @Override
    public void onCollect(Bag bag) {

    }

    @Override
    public void onCollect() {
        super.onCollect();
        System.out.println("Your carrying capacity has significantly increased to " + capacity + " items!");
        System.out.println("This is a rare find - treasure it well!");
    }
}