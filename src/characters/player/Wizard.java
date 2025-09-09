package characters.player;

import characters.Player;
import item.offensiveequipment.Spell;
import item.offensiveequipment.Weapon;

public abstract class Wizard extends Player {

    public Wizard( String name, int life, int damage ) {

        super( name, life, damage );

    }

    public void learnSpell(Spell spell) {
        Spell currentSpell = getEquippedSpell();
        if (currentSpell != null) {
            setAttack(getAttack() - currentSpell.getDamage());
        }
        setWeapon(weapon);
        if (weapon != null) {
            setAttack(getAttack() + weapon.getDamage());
            System.out.println(getName() + " equips " + weapon.getName() +
                    " (+" + weapon.getDamage() + " damage)!");
        }
    }

    public int getSpell(Spell spell) {
        learnSpell(spell);
        return getAttack();
    }
}
