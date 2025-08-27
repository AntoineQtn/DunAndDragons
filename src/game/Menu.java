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
    public boolean askForConfirmation(String prompt) {
        String response;
        do {
            System.out.print(prompt + " (y/n) : ");
            response = scanner.nextLine().trim().toLowerCase();

            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.println("Please answer by 'y' (yes) or 'n' (no)");
            }
        } while (true);
    }

    public int displayMenu(String title, String[] options) {
        System.out.println("\n=== " + title + " ===");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        return askForInt("Your choice", 1, options.length);
    }
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
