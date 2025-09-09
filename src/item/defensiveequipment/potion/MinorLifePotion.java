package item.defensiveequipment.potion;

import item.bag.Bag;
import item.defensiveequipment.Potion;

/**
 * Minor life potion found in chests
 */
public class MinorLifePotion extends Potion {

    public MinorLifePotion(String customName, int customLife) {
        super(customName, customLife, 1, "An enhanced healing potion that restores " + customLife + " life points");
    }

    @Override
    public void usePotion(characters.Player player) {
        if (canBeUsed(player)) {
            player.heal(life);
            System.out.println(player.getName() + " drinks a " + name + " and recovers " + life + " life points!");
        } else {
            System.out.println(player.getName() + " is already at full health!");
        }
    }

    @Override
    public void onCollect(Bag bag) {

    }
}