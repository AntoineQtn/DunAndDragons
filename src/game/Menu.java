package game;

import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu(){
        this.scanner = new Scanner(System.in);
    }


    public void displayMessage(String message){
        System.out.println(message);
    }
    public void displayWelcome(){
        System.out.println("Welcome to Dungeons and Dragons !");
        System.out.println("Get ready for a new adventure !");
    }
    public String askForString(String prompt) {
        System.out.print(prompt + " : ");
        return scanner.nextLine().trim();
    }

    /**
     * Ask for an integer between min and max.
     * If the input is invalid, the user is asked to enter a new value.
     * The method returns the value entered by the user.
     *
     * @param prompt
     * @param min
     * @param max
     * @return
     */
    public int askForInt(String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt + " (" + min + "-" + max + ") : ");
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.println("Invalid field ! Choose between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number !");
                value = -1;
            }
        } while (value < min || value > max);

        return value;
    }
    public void askForEnter(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
    }

}
