package game.db;

import characters.Player;

import java.sql.*;
import java.util.Scanner;

public class CharacterTable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dungeons_and_dragons";
    static final String USER = "toinou";
    static final String PASS = "azerty123!!!";

    public static void createTableCharacter(){
        String query = "CREATE TABLE IF NOT EXISTS `character` (" +
                "character_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "character_name VARCHAR(100) NOT NULL, " +
                "class VARCHAR(100), " +
                "current_health INT NOT NULL, " +
                "attack_power INT NOT NULL, " +
                "offensive_equipment_id INT DEFAULT NULL, " +
                "defensive_equipment_id INT DEFAULT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);
//            System.out.println("Table 'character' created successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                System.out.println("class: " + rs.getString("class"));
                System.out.println("Health: " + rs.getInt("current_health"));
                System.out.println("Attack Power: " + rs.getInt("attack_power"));
                System.out.println("Offensive Equipment ID: " + rs.getInt("offensive_equipment_id"));
                System.out.println("Defensive Equipment ID: " + rs.getInt("defensive_equipment_id"));
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
    public static void createCharacter(Player player) {
        String sql = "INSERT INTO `character` (character_name, class, current_health, attack_power) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, player.getName());
            pstmt.setString(2, player.getClass().getSimpleName()); // HeavyWarrior, FireWizard, etc.
            pstmt.setInt(3, player.getLife());
            pstmt.setInt(4, player.getAttack());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Character saved to database successfully!");
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
                System.out.println(" - ID: " + rs.getInt("character_id") +
                        ", Name: " + rs.getString("character_name"));
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

        listCharacterNames();

        System.out.print("Enter the ID of the character to update: ");
        int characterId = scanner.nextInt();

        System.out.print("Enter new health points: ");
        int newHealthPoints = scanner.nextInt();

        String sql = "UPDATE `character` SET current_health = ? WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newHealthPoints);
            pstmt.setInt(2, characterId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Health points updated successfully!");

                String selectSql = "SELECT character_name, current_health FROM `character` WHERE character_id = ?";
                try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                    selectStmt.setInt(1, characterId);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("Character: " + rs.getString("character_name") +
                                " - New Health Points: " + rs.getInt("current_health"));
                    }
                }
            } else {
                System.out.println("No character found with ID: " + characterId);
            }

        } catch (SQLException e) {
            System.err.println("Error updating health points: " + e.getMessage());
        }
    }

    /**
     * Method to delete a character
     */
    public static void deleteCharacter() {
        Scanner scanner = new Scanner(System.in);

        listCharacterNames();

        System.out.print("Enter the ID of the character to delete: ");
        int characterId = scanner.nextInt();

        String sql = "DELETE FROM `character` WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, characterId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Character deleted successfully!");
            } else {
                System.out.println("No character found with ID: " + characterId);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting character: " + e.getMessage());
        }
    }

    /**
     * Display menu and handle user choices
     */
//    public static void showMenu() {
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//
//        do {
//            System.out.println("\n=== D&D Character Manager ===");
//            System.out.println("1. Display all characters");
//            System.out.println("2. Create new character");
//            System.out.println("3. Update character health points");
//            System.out.println("4. Delete character");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice (1-5): ");
//
//            choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    displayCharacters();
//                    break;
//                case 2:
//                    createCharacter();
//                    break;
//                case 3:
//                    updateLifePoints();
//                    break;
//                case 4:
//                    deleteCharacter();
//                    break;
//                case 5:
//                    System.out.println("Goodbye!");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter 1-5.");
//            }
//        } while (choice != 5);
//
//        scanner.close();
//    }

//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Database driver loaded successfully.");
//
//            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//                System.out.println("Database connection successful!");
//            }
//
//            createTableCharacter();
//
//            showMenu();
//
//        } catch (ClassNotFoundException e) {
//            System.err.println("MySQL JDBC driver not found: " + e.getMessage());
//            System.err.println("Make sure to add MySQL Connector/J to your classpath.");
//        } catch (SQLException e) {
//            System.err.println("Database connection failed: " + e.getMessage());
//            System.err.println("Check your database URL, username, and password.");
//        }
//    }
}