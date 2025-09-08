package game.dice;

import characters.Player;

public class SixSidedIDice implements IDice {
    private int diceValue;

    public SixSidedIDice(){
        this.diceValue = 1;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int roll(Player player) {
        diceValue = (int)(Math.random() * 6) +1;
        return diceValue;
    }

}
