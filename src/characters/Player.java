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
    private boolean equipped = false;
    private Bag basicbag;
    private int maxLife;

    public Player(String name, int life, int damage) {
        super(name, life, damage);
        this.weapon = null;
        this.spell = null;
        this.shield = null;
        this.equipped = false;
        this.equippedPotion = null;
        this.basicbag = new BasicBag();
        this.maxLife = life;
        BasicPotion startingPotion = new BasicPotion();
        this.basicbag.addItem(startingPotion);
    }

//    public void isWeaponEquipped() {
//     if(equipped) {
//         damage = getAttack();
//         life = getLife();
//     }else{
//         damage = weapon.getDamage();
//         life = equippedPotion.getLife();
//     }
//        return equipped;
//    }

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

    public int getPotion() {
        if (!basicbag.getItems().isEmpty()) {
            usePotion(0);
        }
        return getLife();
    }
public Weapon getEquippedWeapon(){
        return weapon;
}
public Spell getEquippedSpell(){
        return spell;
}

public Weapon setWeapon(Weapon weapon){return this.weapon = weapon;}

    public Shield getEquippedShield (){
        return shield;
    }

    public Shield setShield(Shield shield) {return this.shield = shield;}

    public void heal(int amount) {
        int oldLife = this.getLife();
        this.setLife(Math.min(this.getLife() + amount, this.maxLife));
        int actualHealing = this.getLife() - oldLife;
        if (actualHealing > 0) {
            System.out.println(getName() + " healed for " + actualHealing + " points! (" + getLife() + "/" + maxLife + ")");
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void runAway() {
        int runAway = (int)(Math.random() * 6) + 1;
        int currentPosition = Board.getPlayerPosition();
        int newPosition = currentPosition - runAway;
        if (newPosition < 0) {
            newPosition = 0;
        }
        System.out.println("You run away from " + currentPosition + " to " + newPosition + ". Fight is over.");
    }


    @Override
    public void displayStats() {
        System.out.println("=== Player Stats ===");
        System.out.println(getName() + " | Life: " + getLife() + "/" + maxLife + " | Damage: " + getAttack());
        System.out.println("Weapon: " + (weapon != null ? weapon.getName() : "None"));
        System.out.println("Spell: " + (spell != null ? spell.getName() : "None"));
        System.out.println("Equipped Potion: " + (equippedPotion != null ? equippedPotion.getName() : "None"));
        System.out.println("Bag capacity: " + basicbag.getCapacity() + " items");
        System.out.println("====================");
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                ", life=" + getLife() +
                ", damage=" + getAttack() +
                ", weapon=" + (weapon != null ? weapon.getName() : "None") +
                ", spell=" + (spell != null ? spell.getName() : "None") +
                ", shield=" + (shield != null ? shield.getName() : "None") +
                '}';
    }
}
