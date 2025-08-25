package characters;

import items.LargeLifePotion;
import items.MinorLifePotion;
import items.Sword;
import items.Weapon;
import spells.BaseSpells;

public class PlayableCharacter extends Character {
    private int potions;
    private Weapon weapon;
    private BaseSpells spells;

    public PlayableCharacter(String name, int life, int attack) {
        super(name, life, attack);
        this.potions = 1;
        this.weapon = null;
    }

    public void getWeapon() {
        this.weapon = new Weapon("Basic Items.Sword", 5);
        this.attack += weapon.getBonusDamage();
        System.out.println(name + " picks up a " + weapon.getName() +
                " (+" + weapon.getBonusDamage() + " attack )!");
    }

    public void getSpell() {
        this.spells = new BaseSpells("Fireball", 7);
        this.attack += spells.getBonusDamage();
        System.out.println(name + " casts a fireball!" + spells.getName() +
                " (+" + spells.getBonusDamage() + " attack)!");
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

    public void setWeapon(Sword sword) {
        
    }

    public void setPotion(LargeLifePotion largeLifePotion) {
    }

    public void setPotion(MinorLifePotion minorLifePotion) {

    }
}
