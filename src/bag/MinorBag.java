package bag;

import item.Bag;

public class MinorBag extends Bag {
   protected int capacity;
   protected int weight;
   protected String name;

    public MinorBag(String name, int capacity, int weight) {
        this.name = name;
        this.capacity = capacity;
        this.weight = weight;
    }

    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public int getCapacity() {
        return 5;
    }

    @Override
    public void displayStats() {

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
