package game.dice;

import characters.Player;

public class SixSidedDice implements Dice {
    private int diceValue;

    public SixSidedDice(){
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
