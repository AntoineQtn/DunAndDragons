package item.defensiveequipment.shield;

import item.bag.Bag;
import item.defensiveequipment.Shield;

public class IronShield extends Shield {
    public IronShield(String name, int life) {
        super(name, life);
    }


    @Override
    public void onCollect(Bag bag) {

    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void onCollect() {

    }
}
