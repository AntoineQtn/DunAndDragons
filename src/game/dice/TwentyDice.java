package game.dice;

import characters.Player;

public class TwentyDice implements Dice {

    private int diceValue;

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int roll(Player player){
        diceValue = (int)(Math.random() * 20) +1;
        int failureValue = 0;
        int getAttack = player.getDamage();
        int successValue = getAttack + 2;
        System.out.println("Player " + player.getName() + " attacks!");
        System.out.println("You throw a 20 faces dice and make " + diceValue + ".");
        if(diceValue == 1){
            System.out.println("Critical failure ! " + player.getName() + " inflict " + failureValue + " damages."  );
            return diceValue;
        }
        if(diceValue == 20){
            System.out.println("Critical success ! "+ player.getName() + " inflict " + successValue + " ( +2 damages )");
            return diceValue;
        }else{
            System.out.println("You rolled " + diceValue +" You inflict " + getAttack + " damages.");
        }
        return diceValue;
    }
}
