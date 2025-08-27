package characters;

import defensiveequipment.Potion;
import offensiveequipment.Spell;
import offensiveequipment.Weapon;

public class Player extends Character {
    private Weapon weapon;
    private Spell spell;
    private Potion equippedPotion;
    private int basicPotions;

    public Player(String name, int life, int damage) {
        super(name, life, damage);
        this.weapon = null;
        this.spell = null;
        this.equippedPotion = null;
        this.basicPotions = 3;
    }



    public void equipWeapon(Weapon weapon) {
        if (this.weapon != null) {
            this.damage -= this.weapon.getDamage();
        }
        this.weapon = weapon;
        this.damage += weapon.getDamage();
        System.out.println(name + " equips " + weapon.getName() +
                " (+" + weapon.getDamage() + " damage)!");
    }

    public void learnSpell(Spell spell) {
        if (this.spell != null) {
            this.damage -= this.spell.getDamage();
        }
        this.spell = spell;
        this.damage += spell.getDamage();
        System.out.println(name + " learns " + spell.getName() +
                " (+" + spell.getDamage() + " damage)!");
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

    public int getWeapon(Weapon weapon) {
        equipWeapon(weapon);
        return damage;
    }

    public int getSpell(Spell spell) {
        learnSpell(spell);
        return damage;
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
    public Weapon getCurrentWeapon() { return weapon; }
    public Spell getCurrentSpell() { return spell; }
    public Potion getEquippedPotion() { return equippedPotion; }
}
