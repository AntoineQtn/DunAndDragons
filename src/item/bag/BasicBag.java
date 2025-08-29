package item.bag;

import item.Collectible;
import java.util.ArrayList;
import java.util.List;

public class BasicBag extends Bag {
    private String name;
    private int capacity;
    private int weight;
    private List<Collectible> items;

    public BasicBag(String name, int capacity, int weight) {
        this.name = "BasicBag";
        this.capacity = 3;
        this.weight = 3;
        this.items = new ArrayList<>();
    }

    @Override
    public void addToBag(Collectible item){

    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getWeight() {
        return 0;
    }


    @Override
    public void addToBag(Collectible item) {
        if (items.size() < capacity) {
            items.add(item);
            System.out.println(item.getName() + " has been added to your bag!");
        } else {
            System.out.println("The bag is full! You cannot add " + item.getName());
        }
    }

    public void displayStats() {
        System.out.println("Bag name: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Current items:");
        if (items.isEmpty()) {
            System.out.println(" - The bag is empty.");
        } else {
            for (Collectible i : items) {
                System.out.println(" - " + i.getName());
            }
        }
    }

    @Override
    public void addToBag() {

    }

}
