package item;

public abstract class Bag {

    protected String name;
    protected int capacity;
    protected int weight;

    abstract public void setCapacity(int capacity);

    abstract public int getCapacity();
    abstract public void displayStats();
    abstract public String getName();
    abstract public int getWeight();
}
