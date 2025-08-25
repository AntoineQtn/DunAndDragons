package Characters;

import Items.Weapon;

public class PlayableCharacter extends Character {
    private int potions;
    private Weapon weapon; // ➕ ajout d'une arme

    public PlayableCharacter(String name, int life, int attack) {
        super(name, life, attack);
        this.potions = 1; // le joueur commence avec 1 potion
        this.weapon = null;
    }

    public void getWeapon() {
        // pour l'instant, on donne toujours la même arme
        this.weapon = new Weapon("Basic Items.Sword", 5);
        this.attack += weapon.getBonusDamage();
        System.out.println(name + " picks up a " + weapon.getName() +
                " (+" + weapon.getBonusDamage() + " ATK)!");
    }

    public void getPotion() {
        if (potions > 0) {
            this.life += 20;
            potions--;
            System.out.println(name + " drinks a potion! +20 HP (" + potions + " left)");
        } else {
            System.out.println(name + " has no potion left!");
        }
    }

    public void move() {
        System.out.println(name + " moves!");
    }

    public void addPotion() {
        potions++;
        System.out.println(name + " found a potion! (" + potions + " total)");
    }

    public int getPotions() {
        return potions;
    }
}
