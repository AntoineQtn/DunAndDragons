package item;

public abstract class Bag {

    protected String name;
    protected int capacity;
    protected int weight;

    public abstract void addToBag(Collectible item);

    abstract public void displayStats();

    abstract public void addToBag();

    abstract public void setName(String name);
    abstract public void setWeight(int weight);
    abstract public void setCapacity(int capacity);

    abstract public int getCapacity();
    abstract public String getName();
    abstract public int getWeight();
}
