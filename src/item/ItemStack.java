package item;

public class ItemStack {
    private final Item item;
    private int quantity;

    public ItemStack(Item item, int quantity) {
        this.item = item;
        this.quantity = Math.min(quantity, item.getMaxStackSize());
    }

    public Item getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getTotalWeight() { return item.getWeight() * quantity; }

    public boolean canAddMore() {
        return quantity < item.getMaxStackSize();
    }

    public int addQuantity(int amount) {
        int maxAdd = item.getMaxStackSize() - quantity;
        int actualAdd = Math.min(amount, maxAdd);
        quantity += actualAdd;
        return amount - actualAdd; // Retourne le surplus
    }

    public boolean removeQuantity(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (quantity == 1) {
            return item.toString();
        }
        return item.getName() + " x" + quantity + " (" + getTotalWeight() + " lbs total)";
    }
}