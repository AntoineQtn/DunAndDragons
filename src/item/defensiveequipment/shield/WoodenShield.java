package item.defensiveequipment.shield;

import item.defensiveequipment.Shield;

public class WoodenShield extends Shield {
    public WoodenShield(String name, int life) {
        super(name, life);
    }
    public int woodenShieldBonus() {
        System.out.println(getName() + " Gives +" + getLife() + " life point!");
        return getLife();
    }
}
