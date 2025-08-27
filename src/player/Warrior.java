package player;

import characters.Player;
import offensiveequipment.Weapon;

public abstract class Warrior extends Player {

    public Warrior( String name, int life, int damage ) {
        super( name, life, damage );

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

    public int getWeapon(Weapon weapon) {
        equipWeapon(weapon);
        return damage;
    }
}
