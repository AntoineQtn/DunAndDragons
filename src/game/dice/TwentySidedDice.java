package game.dice;

import characters.Player;

public class TwentySidedDice implements Dice {

    private int diceValue;

    @Override
    public String getName() {
        return "Twenty Sided Dice";
    }

    @Override
    public String getDescription() {
        return "A 20-faced dice used to determine attack success and damage bonuses.";
    }

    @Override
    public int roll(Player player) {
        diceValue = (int)(Math.random() * 20) + 1;

        int baseAttack = player.getAttack();
        int damage = 0;

        System.out.println("Player " + player.getName() + " attacks!");
        System.out.println("You throw a 20-sided dice and roll " + diceValue + ".");

        if (diceValue == 1) {
            System.out.println("Critical failure! " + player.getName() + " inflicts 0 damage.");
            damage = 0;
        } else if (diceValue > 5) {
            damage = baseAttack + 2;
            System.out.println("Critical success! " + player.getName() + " inflicts " + damage + " damage! (+2 bonus)");
        } else {
            damage = baseAttack;
            System.out.println(player.getName() + " inflicts " + damage + " damage.");
        }

        return damage;
    }


}
