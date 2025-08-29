package characters.player;

import characters.Player;
import item.offensiveequipment.Spell;

public abstract class Wizard extends Player {

    public Wizard( String name, int life, int damage ) {

        super( name, life, damage );

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

    public int getSpell(Spell spell) {
        learnSpell(spell);
        return damage;
    }
}
