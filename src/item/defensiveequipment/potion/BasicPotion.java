package item.defensiveequipment.potion;

import item.defensiveequipment.Potion;

/**
 * Basic healing potion that every player starts with
 */
public class BasicPotion extends Potion {

    public BasicPotion() {
        super("Basic Healing Potion", 3, 1, "A simple healing potion that restores 3 life points");
    }

    @Override
    public void onCollect() {
        System.out.println("You start with a " + name + " in your bag!");
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
}
