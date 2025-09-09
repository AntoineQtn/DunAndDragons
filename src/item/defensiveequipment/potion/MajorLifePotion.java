package item.defensiveequipment.potion;

import item.bag.Bag;
import item.defensiveequipment.Potion;

/**
 * Major life potion found in chests (rare)
 */
public class MajorLifePotion extends Potion {

    public MajorLifePotion(String customName, int customLife) {
        super(customName, customLife, 2, "A powerful healing potion that restores " + customLife + " life points");
    }

    @Override
    public void onCollect(Bag bag) {

    }

    @Override
    public void onCollect() {
        super.onCollect();
        System.out.println("This is a powerful healing potion - use it wisely!");
    }

    @Override
    public void usePotion(characters.Player player) {
        if (canBeUsed(player)) {
            player.heal(life);
            System.out.println(player.getName() + " drinks a " + name + " and recovers " + life + " life points!");
            System.out.println("The powerful magic surges through " + player.getName() + "'s body!");
        } else {
            System.out.println(player.getName() + " is already at full health!");
        }
    }
}