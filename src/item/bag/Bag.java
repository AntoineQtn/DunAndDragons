// Classe abstraite Bag
package item.bag;

import item.Collectible;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all types of bags
 */
public abstract class Bag implements Collectible {
    protected String name;
    protected String description;
    protected int capacity;
    protected List<Object> items;

    public Bag(String name, String description, int capacity) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    // Méthodes de l'interface Collectible
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

    // Méthodes de gestion des objets
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

    public int getCurrentSize() {
        return items.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return items.size() >= capacity;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void displayContents() {
        System.out.println("\n=== " + name + " Contents ===");
        System.out.println("Capacity: " + items.size() + "/" + capacity);
        if (isEmpty()) {
            System.out.println("Empty");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).toString());
            }
        }
        System.out.println("========================");
    }
}