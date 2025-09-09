package item.bag;

import item.ICollectible;
import item.Item;
import item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class for all types of bags
 */
public abstract class Bag implements ICollectible {
    protected String name;
    protected String description;
    protected int slotCapacity;        // Nombre d'emplacements
    protected double weightCapacity;   // Capacité en poids
    protected List<ItemStack> items;

    public Bag(String name, String description, int slotCapacity, double weightCapacity) {
        this.name = name;
        this.description = description;
        this.slotCapacity = slotCapacity;
        this.weightCapacity = weightCapacity;
        this.items = new ArrayList<>();
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getDescription() { return description; }

    @Override
    public double getWeight() {
        return getCurrentWeight() + getEmptyWeight();
    }

    protected abstract double getEmptyWeight(); // Poids du sac vide

    @Override
    public boolean isStackable() { return false; }

    @Override
    public int getMaxStackSize() { return 1; }

    @Override
    public void onCollect(Bag bag) {
        System.out.println("You collected a " + name + "! " + description);
    }

    public boolean addItem(Item item, int quantity) {
        if (item == null || quantity <= 0) return false;

        // Vérifier les contraintes de poids
        double additionalWeight = item.getWeight() * quantity;
        if (getCurrentWeight() + additionalWeight > weightCapacity) {
            System.out.println("Your " + name + " is too heavy! Cannot add " +
                    item.getName() + " (would exceed weight limit)");
            return false;
        }

        // Si l'objet est empilable, essayer de l'ajouter aux piles existantes
        if (item.isStackable()) {
            int remainingQuantity = quantity;

            // Chercher des piles existantes du même objet
            for (ItemStack stack : items) {
                if (stack.getItem().getName().equals(item.getName()) &&
                        stack.canAddMore()) {
                    remainingQuantity = stack.addQuantity(remainingQuantity);
                    if (remainingQuantity == 0) {
                        System.out.println("Added " + quantity + "x " + item.getName() +
                                " to existing stack in " + name);
                        return true;
                    }
                }
            }

            // Créer de nouvelles piles si nécessaire
            while (remainingQuantity > 0 && items.size() < slotCapacity) {
                int stackSize = Math.min(remainingQuantity, item.getMaxStackSize());
                items.add(new ItemStack(item, stackSize));
                remainingQuantity -= stackSize;
            }

            if (remainingQuantity == 0) {
                System.out.println("Added " + quantity + "x " + item.getName() + " to " + name);
                return true;
            } else {
                System.out.println("Your " + name + " is full! Could only add " +
                        (quantity - remainingQuantity) + "x " + item.getName());
                return false;
            }
        } else {
            // Objet non empilable
            if (items.size() + quantity > slotCapacity) {
                System.out.println("Your " + name + " doesn't have enough slots!");
                return false;
            }

            for (int i = 0; i < quantity; i++) {
                items.add(new ItemStack(item, 1));
            }

            System.out.println("Added " + quantity + "x " + item.getName() + " to " + name);
            return true;
        }
    }

    public boolean addItem(Item item) {
        return addItem(item, 1);
    }

    public boolean removeItem(String itemName, int quantity) {
        int remainingToRemove = quantity;
        Iterator<ItemStack> iterator = items.iterator();

        while (iterator.hasNext() && remainingToRemove > 0) {
            ItemStack stack = iterator.next();
            if (stack.getItem().getName().equals(itemName)) {
                int removeFromStack = Math.min(remainingToRemove, stack.getQuantity());
                stack.removeQuantity(removeFromStack);
                remainingToRemove -= removeFromStack;

                if (stack.getQuantity() == 0) {
                    iterator.remove();
                }
            }
        }

        int removed = quantity - remainingToRemove;
        if (removed > 0) {
            System.out.println("Removed " + removed + "x " + itemName + " from " + name);
            return true;
        }

        System.out.println("Item " + itemName + " not found in " + name);
        return false;
    }

    public boolean removeItem(String itemName) {
        return removeItem(itemName, 1);
    }

    public List<ItemStack> getItems() {
        return new ArrayList<>(items);
    }

    public double getCurrentWeight() {
        return items.stream()
                .mapToDouble(ItemStack::getTotalWeight)
                .sum();
    }

    public int getCurrentSlots() {
        return items.size();
    }

    public int getSlotCapacity() { return slotCapacity; }
    public double getWeightCapacity() { return weightCapacity; }

    public boolean hasItem(String itemName) {
        return items.stream()
                .anyMatch(stack -> stack.getItem().getName().equals(itemName));
    }

    public int getItemQuantity(String itemName) {
        return items.stream()
                .filter(stack -> stack.getItem().getName().equals(itemName))
                .mapToInt(ItemStack::getQuantity)
                .sum();
    }

    public void displayInventory() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("Slots: " + getCurrentSlots() + "/" + slotCapacity);
        System.out.println("Weight: " + String.format("%.1f", getCurrentWeight()) +
                "/" + String.format("%.1f", weightCapacity) + " lbs");
        System.out.println("Items:");

        if (items.isEmpty()) {
            System.out.println("  (empty)");
        } else {
            for (ItemStack stack : items) {
                System.out.println("  - " + stack.toString());
            }
        }
        System.out.println();
    }
}