package game.dice;

import characters.Player;

public class MoveDice implements Dice {
    private int diceValue;

    public MoveDice(){
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

//    public void displayDice(){
//        int result = roll();
//        System.out.println("You have make " + result);
//    }

//    public static void main(String[] args){
//        MoveDice dice = new MoveDice();
//        dice.displayDice();
//    }

}
