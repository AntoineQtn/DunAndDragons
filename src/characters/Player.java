package characters;

import defensiveequipment.Potion;
import offensiveequipment.Spell;
import offensiveequipment.Weapon;

public class Player extends Character {
    protected Weapon weapon;
    protected Spell spell;
    private Potion equippedPotion;
    private int basicPotions;
    private int basicbag;

    public Player(String name, int life, int damage) {
        super(name, life, damage);
        this.weapon = null;
        this.spell = null;
        this.equippedPotion = null;
        this.basicbag = 1;
    }

    public void equipPotion(Potion potion) {
        if (this.equippedPotion != null) {
            this.life -= this.equippedPotion.getLife();
        }
        this.equippedPotion = potion;
        this.life += potion.getLife();
        System.out.println(name + " equips " + potion.getName() +
                " (+" + potion.getLife() + " permanent life)!");
    }

    public void useBasicPotion() {
        if (basicPotions > 0) {
            int healAmount = 20;
            this.life += healAmount;
            basicPotions--;
            System.out.println(name + " drinks a basic potion and regains " +
                    healAmount + " life points! (" + basicPotions + " left)");
        } else {
            System.out.println(name + " has no basic potions left!");
        }
    }

    public void usePotion(Potion potion) {
        this.life += potion.getLife();
        System.out.println(name + " drinks " + potion.getName() +
                " and regains " + potion.getLife() + " life points!");
    }

    public void addBasicPotions(int amount) {
        basicPotions += amount;
        System.out.println(name + " gains " + amount + " basic potions! (" +
                basicPotions + " total)");
    }

    public int getPotion(Potion potion) {
        usePotion(potion);
        return life;
    }

    public void getPotion() {
        useBasicPotion();
    }

    @Override
    public void displayStats() {
        super.displayStats();
        System.out.println("Weapon: " + (weapon != null ? weapon.getName() : "None"));
        System.out.println("Spell: " + (spell != null ? spell.getName() : "None"));
        System.out.println("Equipped Potion: " + (equippedPotion != null ? equippedPotion.getName() : "None"));
        System.out.println("Basic Potions: " + basicPotions);
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
    public int getBasicPotionCount() { return basicPotions; }
    public Potion getEquippedPotion() { return equippedPotion; }
}
