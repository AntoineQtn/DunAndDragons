package bag;

import item.Bag;

public class BasicBag extends Bag {
    private int capacity;
    private int weight;
    private String name;

    public BasicBag(String name, int capacity, int weight) {
        this.name = name;
        this.capacity = capacity;
        this.weight = weight;
    }


    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public int getCapacity() {
        return 3;
    }

    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Weight: " + weight);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getWeight() {
        return 1;
    }
}
