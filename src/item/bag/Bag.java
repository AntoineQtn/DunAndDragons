package item.bag;

import item.Collectible;

public abstract class Bag {

    protected String name;
    protected int capacity;


    public abstract void addToBag(Collectible item);

    abstract public void displayStats();

    abstract public void addToBag();

    abstract public void setName(String name);
    abstract public void setCapacity(int capacity);

    abstract public int getCapacity();

    public abstract void setWeight(int weight);

    abstract public String getName();

    public abstract int getWeight();
}
