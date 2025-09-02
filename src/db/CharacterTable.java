package db;

import java.sql.*;
import java.util.Scanner;

public class CharacterTable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dungeons_and_dragons";
    static final String USER = "toinou";
    static final String PASS = "azerty123!!!";

    /**
     * Method to display all characters in console
     */
    public static void displayCharacters() {
        String query = "SELECT * FROM `character`";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Here are the characters in the database:");
            System.out.println("========================================");

            boolean hasCharacters = false;
            while (rs.next()) {
                hasCharacters = true;
                System.out.println("------------");
                System.out.println("ID: " + rs.getInt("character_id"));
                System.out.println("Name: " + rs.getString("character_name"));
                System.out.println("Type: " + rs.getString("class_id"));
                System.out.println("Level: " + rs.getInt("level"));
                System.out.println("Health: " + rs.getInt("current_health"));
                System.out.println("Offensive Equipment: " + rs.getInt("offensive_equipment_id"));
                System.out.println("Defensive Equipment: " + rs.getInt("defensive_equipment_id"));
            }

            if (!hasCharacters) {
                System.out.println("No characters found in the database.");
            }

        } catch (SQLException e) {
            System.err.println("Error displaying characters: " + e.getMessage());
        }
    }

    /**
     * Method to create a new character with user input
     */
    public static void createCharacter() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating a new character:");
        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        System.out.print("Enter character type (Warrior/Mage/etc.): ");
        String type = scanner.nextLine();

        System.out.print("Enter life points: ");
        int lifePoints = scanner.nextInt();

        System.out.print("Enter max life points: ");
        int max_health = scanner.nextInt();

        System.out.print("Enter strength: ");
        int attack_power = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter defense: ");
        int defense_power = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter offensive equipment (or 'null' for none): ");
        String offensiveEquipment = scanner.nextLine();
        if ("null".equalsIgnoreCase(offensiveEquipment)) {
            offensiveEquipment = null;
        }

        System.out.print("Enter defensive equipment (or 'null' for none): ");
        String defensiveEquipment = scanner.nextLine();
        if ("null".equalsIgnoreCase(defensiveEquipment)) {
            defensiveEquipment = null;
        }

        String sql = "INSERT INTO `character` (character_name, class_id, current_health, max_health, attack_power,defense_power, offensive_equipment_id, defensive_equipment_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, type);
            pstmt.setInt(3, lifePoints);
            pstmt.setInt(4, max_health);
            pstmt.setInt(5, attack_power);
            pstmt.setInt(6, defense_power);

            pstmt.setString(7, offensiveEquipment);
            pstmt.setString(8, defensiveEquipment);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Character '" + name + "' created successfully!");
            } else {
                System.out.println("Failed to create character.");
            }

        } catch (SQLException e) {
            System.err.println("Error creating character: " + e.getMessage());
        }
    }

    /**
     * Method to list all character names for selection
     */
    public static void listCharacterNames() {
        String query = "SELECT character_id, character_name FROM `character`";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Available characters:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("character_id") + " - Name: " + rs.getString("character_name"));
            }

        } catch (SQLException e) {
            System.err.println("Error listing characters: " + e.getMessage());
        }
    }

    /**
     * Method to modify a character's life points
     */
    public static void updateLifePoints() {
        Scanner scanner = new Scanner(System.in);

        // First, show available characters
        listCharacterNames();

        System.out.print("Enter the ID of the character to update: ");
        int character_id = scanner.nextInt();

        System.out.print("Enter new life points: ");
        int newLifePoints = scanner.nextInt();

        String sql = "UPDATE `character` SET current_health = ? WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newLifePoints);
            pstmt.setInt(2, character_id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Life points updated successfully!");

                String selectSql = "SELECT character_name, current_health FROM `character` WHERE character_id = ?";
                try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                    selectStmt.setInt(1, character_id);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("Character: " + rs.getString("character_name") +
                                " - New Life Points: " + rs.getInt("current_health"));
                    }
                }
            } else {
                System.out.println("No character found with ID: " + character_id);
            }

        } catch (SQLException e) {
            System.err.println("Error updating life points: " + e.getMessage());
        }
    }

    /**
     * Method to delete a character
     */
    public static void deleteCharacter() {
        Scanner scanner = new Scanner(System.in);

        listCharacterNames();

        System.out.print("Enter the ID of the character to delete: ");
        int character_id = scanner.nextInt();

        String sql = "DELETE FROM `character` WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, character_id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Character deleted successfully!");
            } else {
                System.out.println("No character found with ID: " + character_id);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting character: " + e.getMessage());
        }
    }

    /**
     * Display menu and handle user choices
     */
    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== D&D Character Manager ===");
            System.out.println("1. Display all characters");
            System.out.println("2. Create new character");
            System.out.println("3. Update character life points");
            System.out.println("4. Delete character");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCharacters();
                    break;
                case 2:
                    createCharacter();
                    break;
                case 3:
                    updateLifePoints();
                    break;
                case 4:
                    deleteCharacter();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    public static void main(String[] args) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Database driver loaded successfully.");

            // Test connection
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
                System.out.println("Database connection successful!");
            }

            showMenu();

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found: " + e.getMessage());
            System.err.println("Make sure to add MySQL Connector/J to your classpath.");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            System.err.println("Check your database URL, username, and password.");
        }
    }
}