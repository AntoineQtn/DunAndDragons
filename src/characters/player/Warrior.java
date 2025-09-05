package characters.player;

import characters.Player;
import item.defensiveequipment.Shield;
import item.offensiveequipment.Weapon;

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

    public void equipShield(Shield shield){
        if (this.shield != null) {
            this.life -= shield.getLife();
        }
        this.shield = shield;
        this.life += shield.getLife();
            System.out.println(name + " equips " + shield.getName() +
                    " (+" + shield.getLife() + " life)!");
        }

        public int getShield(Shield shield){
            equipShield(shield);
            return life;
        }
    }

