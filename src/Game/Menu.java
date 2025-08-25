package Game;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Affiche le message de bienvenue
     */
    public void displayWelcome() {
        System.out.println("=".repeat(60));
        System.out.println(" Welcome in Dungeons and Dragons !");
        System.out.println("=".repeat(60));
        System.out.println("Get ready for a new adventure !");
        System.out.println("=".repeat(60));
    }

    /**
     * Affiche un message à l'utilisateur
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Demande une chaîne de caractères à l'utilisateur
     */
    public String askForString(String prompt) {
        System.out.print(prompt + " : ");
        return scanner.nextLine().trim();
    }

    /**
     * Demande un entier à l'utilisateur avec validation
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
                value = -1; // Valeur invalide pour continuer la boucle
            }
        } while (value < min || value > max);

        return value;
    }

    /**
     * Demande un entier sans contrainte
     */
    public int askForInt(String prompt) {
        int value;
        do {
            System.out.print(prompt + " : ");
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number !");
            }
        } while (true);

        return value;
    }

    /**
     * Demande à l'utilisateur d'appuyer sur Entrée
     */
    public void askForEnter(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
    }

    /**
     * Demande une confirmation (oui/non)
     */
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

    /**
     * Affiche un menu avec options et retourne le choix
     */
    public int displayMenu(String title, String[] options) {
        System.out.println("\n=== " + title + " ===");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        return askForInt("Your choice", 1, options.length);
    }

    /**
     * Affiche une barre de progression
     */
    public void displayProgressBar(String label, int current, int max) {
        int barLength = 20;
        int progress = (int) ((double) current / max * barLength);

        System.out.print(label + " [");
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                System.out.print("█");
            } else {
                System.out.print("░");
            }
        }
        System.out.println("] " + current + "/" + max);
    }

    /**
     * Efface l'écran (simulation)
     */
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Ferme le scanner
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}