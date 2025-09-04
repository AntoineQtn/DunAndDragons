package game.db;

import java.sql.*;
import java.util.Scanner;

public class BoardTable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dungeons_and_dragons";
    static final String USER = "toinou";
    static final String PASS = "azerty123!!!";


    public static void displayBoard() {
        String query = "SELECT * FROM board";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Here is the database board:");
            System.out.println("========================================");

            boolean hasBoards = false;
            while (rs.next()) {
                hasBoards = true;
                System.out.println("------------");
                System.out.println("ID: " + rs.getInt("board_id"));
                System.out.println("Name: " + rs.getString("cell_type"));

            }

            if (!hasBoards) {
                System.out.println("No cells found in the database.");
            }

        } catch (SQLException e) {
            System.err.println("Error displaying boards: " + e.getMessage());
        }
    }

    public static void createBoard(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cell type : ");
        String type = scanner.nextLine();

        System.out.print("Enter board_id : ");
        int board_id = scanner.nextInt();

        System.out.print("Enter cell position : ");
        int position = scanner.nextInt();

        System.out.print("Enter display character : ");
        String display_char = scanner.nextLine();

        System.out.print("Enter if active : ");
        int is_active = scanner.nextInt();

        System.out.print("Enter cell's name : ");
        String cell_name = scanner.nextLine();

        System.out.print("Enter cell id: ");
        int cell_id = scanner.nextInt();

        String sql = "INSERT INTO `board` (board_id, length, player_position, game_state ) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cell_id);
            pstmt.setString(2, type);
            pstmt.setInt(3, board_id);
            pstmt.setInt(4, position);
            pstmt.setString(5, display_char);
            pstmt.setInt(6, is_active);
            pstmt.setString(7, cell_name);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cell '" + type + "' created successfully!");
            } else {
                System.out.println("Failed to create cell.");
            }

        } catch (SQLException e) {
            System.err.println("Error creating cell: " + e.getMessage());
        }
    }

    public static void listBoardNames(){
        String query = "SELECT cell_id, cell_type FROM `cell`";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Available cells:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("cell_id") + " - Name: " + rs.getString("cell_type"));
            }

        } catch (SQLException e) {
            System.err.println("Error listing cells: " + e.getMessage());
        }
    }

    public static void updateBoard(){
        Scanner scanner = new Scanner(System.in);

        listBoardNames();

        System.out.print("Enter the ID of the board to update: ");
        int cell_id = scanner.nextInt();

        System.out.print("Enter new board type: ");
        int newCellType = scanner.nextInt();

        String sql = "UPDATE `character` SET current_health = ? WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newCellType);
            pstmt.setInt(2, cell_id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cell type updated successfully!");

                String selectSql = "SELECT cell_id, cell_type FROM `character` WHERE cell_id = ?";
                try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                    selectStmt.setInt(1, cell_id);
                    ResultSet rs = selectStmt.executeQuery();

                    if (rs.next()) {
                        System.out.println("Cell: " + rs.getInt("cell_id") +
                                " - New Cell Type: " + rs.getString("cell_type"));
                    }
                }
            } else {
                System.out.println("No cell found with ID: " + cell_id);
            }

        } catch (SQLException e) {
            System.err.println("Error updating cell type : " + e.getMessage());
        }
    }

    public static void deleteBoard(){
        Scanner scanner = new Scanner(System.in);

        listBoardNames();

        System.out.print("Enter the ID of the cells to delete: ");
        int cell_id = scanner.nextInt();

        String sql = "DELETE FROM `cell` WHERE cell_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cell_id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cell deleted successfully!");
            } else {
                System.out.println("No cell found with ID: " + cell_id);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting cell: " + e.getMessage());
        }
    }

    public static void showMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== D&D Character Manager ===");
            System.out.println("1. Display all cells");
            System.out.println("2. Create new cell");
            System.out.println("3. Update cell's type");
            System.out.println("4. Delete cell");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayBoard();
                    break;
                case 2:
                    createBoard();
                    break;
                case 3:
                    updateBoard();
                    break;
                case 4:
                    deleteBoard();
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

    public static void main(String[] args){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Database driver loaded successfully.");

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



