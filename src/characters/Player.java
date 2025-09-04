package characters;

import game.Board;
import item.bag.Bag;
import item.bag.BasicBag;
import item.defensiveequipment.Potion;
import item.defensiveequipment.Shield;
import item.defensiveequipment.potion.BasicPotion;
import item.offensiveequipment.Spell;
import item.offensiveequipment.Weapon;

import java.util.List;

public class Player extends Character {
    protected Weapon weapon;
    protected Spell spell;
    protected Shield shield;
    private Potion equippedPotion;
    private int basicPotions;
    private Bag basicbag;
    protected int maxLife;

    public Player(String name, int life, int damage) {
        super(name, life, damage);
        this.weapon = null;
        this.spell = null;
        this.equippedPotion = null;
        this.basicbag = new BasicBag();
        this.maxLife = life;
        BasicPotion startingPotion = new BasicPotion();
        this.basicbag.addItem(startingPotion);
    }

    public void upgradeBag(Bag newBag) {
        if (newBag.getCapacity() > basicbag.getCapacity()) {
            List<Object> oldItems = basicbag.getItems();
            basicbag = newBag;
            newBag.onCollect();

            for (Object item : oldItems) {
                basicbag.addItem(item);
            }
            System.out.println("Successfully upgraded to " + newBag.getName() + "!");
        } else {
            System.out.println("This bag is not better than your current one.");
        }
    }

    public int getPotion() {
        int potion = 0;
        usePotion(potion);
        return life;
    }

    public void heal(int amount) {
        int oldLife = this.life;
        this.life = Math.min(this.life + amount, this.maxLife);
        int actualHealing = this.life - oldLife;
        if (actualHealing > 0) {
            System.out.println(name + " healed for " + actualHealing + " points! (" + life + "/" + maxLife + ")");
        }
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public boolean usePotion(int potionIndex) {
        List<Object> items = basicbag.getItems();
        if (potionIndex < 0 || potionIndex >= items.size()) {
            System.out.println("Invalid potion selection!");
            return false;
        }

        Object item = items.get(potionIndex);
        if (item instanceof Potion) {
            Potion potion = (Potion) item;
            potion.usePotion(this);
            basicbag.removeItem(potion);
            return true;
        } else {
            System.out.println("Selected item is not a potion!");
            return false;
        }
    }

    public void displayStats() {

        System.out.println("Weapon: " + (weapon != null ? weapon.getName() : "None"));
        System.out.println("Spell: " + (spell != null ? spell.getName() : "None"));
        System.out.println("Equipped Potion: " + (equippedPotion != null ? equippedPotion.getName() : "None"));
        System.out.println("Basic Potions: " + basicPotions);
        System.out.println("Bag: " + basicbag + " items to store");

    }

    public void runAway(){
        int runAway = (int)(Math.random() * 6) + 1;
        int currentPosition = Board.getPlayerPosition();
        int newPosition = currentPosition - runAway;
        if (newPosition < 0) {
            newPosition = 0;
        }
        System.out.println("You run away from" + currentPosition + " to" + newPosition);

    }

    @Override
    public String toString() {
        return "Player{" +
                "weapon=" + weapon +
                ", spell=" + spell +
                ", equippedPotion=" + equippedPotion +
                ", basicPotions=" + basicPotions +
                '}';
    }

}