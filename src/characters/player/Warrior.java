package characters.player;

import characters.Player;
import item.defensiveequipment.Shield;
import item.offensiveequipment.Weapon;

public abstract class Warrior extends Player {

    public Warrior(String name, int life, int damage) {
        super(name, life, damage);
    }

    public void equipWeapon(Weapon weapon) {
        Weapon currentWeapon = getEquippedWeapon();
        if (currentWeapon != null) {
            setAttack(getAttack() - currentWeapon.getDamage());
        }
        setWeapon(weapon);
        if (weapon != null) {
            setAttack(getAttack() + weapon.getDamage());
            System.out.println(getName() + " equips " + weapon.getName() +
                    " (+" + weapon.getDamage() + " damage)!");
        }
    }

    public int getWeapon(Weapon weapon) {
        equipWeapon(weapon);
        return getAttack();
    }

    public void equipShield(Shield shield) {
        Shield currentShield = getEquippedShield();
        if (currentShield != null) {
            setLife(getLife() - currentShield.getLife());
        }
        setShield(shield);
        if (shield != null) {
            setLife(getLife() + shield.getLife());
            System.out.println(getName() + " equips " + shield.getName() +
                    " (+" + shield.getLife() + " life)!");
        }
    }

    public int getShield(Shield shield) {
        equipShield(shield);
        return getLife();
    }
}

