package game;

public class Dice {
    private int diceValue;

    public Dice(){
        this.diceValue = 1;
    }

    public int rollDice(){
        diceValue = (int)(Math.random() * 6) +1;
        return diceValue;
    }

    public void displayDice(){
        int result = rollDice();
        System.out.println("You have make " + result);
    }

    public static void main(String[] args){
        Dice dice = new Dice();
        dice.displayDice();
    }

}
