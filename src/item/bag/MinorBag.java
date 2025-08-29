package item.bag;

import item.Collectible;

public class MinorBag extends Bag {
   private int capacity;
   private int weight;
   private String name;

    public MinorBag() {
        this.name = "Minor Bag";
        this.capacity = 5;
        this.weight = 1;
    }

    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public int getCapacity() {
        return 5;
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
