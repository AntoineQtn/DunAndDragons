package item.bag;

import item.Collectible;

public class LargeBag extends Bag {
    protected int capacity;
    protected int weight;
    protected String name;

    public LargeBag(String name, int capacity, int weight) {
        this.name = "Large Bag";
        this.capacity = 7;
        this.weight = 0;
    }

    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public int getCapacity() {
        return 7;
    }

    @Override
    public void addToBag(Collectible item) {

    }

    @Override
    public void displayStats() {

    }

    @Override
    public void addToBag() {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getWeight() {
        return 3;
    }
}
