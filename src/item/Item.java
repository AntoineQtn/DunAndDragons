package item;

import item.bag.Bag;

public abstract class Item implements ICollectible {
    protected String name;
    protected String description;
    protected double weight;
    protected boolean stackable;
    protected int maxStackSize;

    public Item(String name, String description, double weight) {
        this(name, description, weight, false, 1);
    }

    public Item(String name, String description, double weight, boolean stackable, int maxStackSize) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.stackable = stackable;
        this.maxStackSize = stackable ? maxStackSize : 1;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public double getWeight() { return weight; }

    @Override
    public boolean isStackable() { return stackable; }

    @Override
    public int getMaxStackSize() { return maxStackSize; }

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You collected: " + name);
    }

    @Override
    public String toString() {
        return name + " (" + weight + " lbs)";
    }
}

