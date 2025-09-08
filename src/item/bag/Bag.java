package item.bag;

import item.ICollectible;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all types of bags
 */
public abstract class Bag implements ICollectible {
    private String name;
    private String description;
    private int capacity;
    private List<Object> items;

    public Bag(String name, String description, int capacity) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void onCollect() {
        System.out.println("You collected a " + name + "! " + description);
    }

    public boolean addItem(Object item) {
        if (items.size() < capacity) {
            items.add(item);
            System.out.println("Added " + item.toString() + " to your " + name);
            return true;
        } else {
            System.out.println("Your " + name + " is full! Cannot add more items.");
            return false;
        }
    }

    public boolean removeItem(Object item) {
        if (items.remove(item)) {
            System.out.println("Removed " + item.toString() + " from your " + name);
            return true;
        }
        return false;
    }

    public List<Object> getItems() {
        return new ArrayList<>(items); // Retourne une copie pour éviter les modifications externes
    }


    public int getCapacity() {
        return capacity;
    }

}