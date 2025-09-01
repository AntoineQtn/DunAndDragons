package db;

import characters.Player;

import java.sql.*;

public class DataBase {

    static final String DB_URL = "jdbc:mysql://localhost:3306/dungeons_and_dragons";
    static final String USER = "toinou";
    static final String PASS = "azerty123!!!";
    static final String QUERY = "SELECT name FROM characters";

    /**
     * Method to display characters in console
     */
    public static void createHero(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dungeons_and_dragons", "toinou", "azerty123!!!");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM characters");
            System.out.println("Here are the characters in the database:");
            while (result.next()){
                System.out.println("------------");
                System.out.println(result.getString("Name"));
                System.out.println(result.getString("Type"));
                System.out.println(result.getInt("LifePoints"));
                System.out.println(result.getInt("Strength"));
                System.out.println(result.getString("OffensiveEquipment"));
                System.out.println(result.getString("DefensiveEquipment"));
            }
            result.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method that save a character in database according to the user's choice
     */
    public static void createCharacter(){
        try( Connection conn = DriverManager.getConnection( DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ) {
            System.out.println("Inserting records into the table...");
            String sql = "INSERT INTO characters(id, Name, Type, LifePoints, Strength, OffensiveEquipment, DefensiveEquipment) VALUES ('', 'Zara','Warrior', 5, 12, null, null)";
            stmt.executeUpdate(sql);
    } catch (Exception e) {
        System.out.println(e);}
    }

    /**
     * Method to modify a character choosen by the player in database
     */
    public static void editHero(){
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY);
    ){
        while(rs.next()){
            System.out.println("Name:" + rs.getString("name"));
        }
    }catch (Exception e){
        System.out.println(e);
    }
    }

    /**
     * Method to edit a maxLifePoints character's attribute
     */
    public void changeLifePoints(){

    }

    /**
     * main method of the class, use to connect to de DB
     * @param args
     */
    public static void main(String[] args) {
        createHero();
        createCharacter();
        editHero();

    }
}



