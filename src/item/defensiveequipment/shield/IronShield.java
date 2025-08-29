package item.defensiveequipment.shield;

import item.defensiveequipment.Shield;

public class IronShield extends Shield {
    public IronShield(String name, int life) {
        super(name, life);
    }

    public int ironShieldBonus() {
        System.out.println(getName() + " Gives +" + getLife() + " life point!");
        return getLife();
    }
}
