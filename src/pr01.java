import java.sql.*;
import java.util.*;

public class pr01 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/movies";
        String user = "root";
        String password = "admin";

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");

            if (connection != null) {
                System.out.println("Connected");
            }

            String create = "CREATE TABLE IF NOT EXISTS movies (id INT AUTO_INCREMENT PRIMARY KEY,moviename VARCHAR(255))";
            PreparedStatement ps = connection.prepareStatement(create);
            ps.executeUpdate();

            System.out.println("Table created successfully.");

            System.out.println("Select What you Want:");
            System.out.println("1.Insert Movie Name \n2.Delete Movie Name \n3.Update Movie Name \n4.Display All Movies");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();

            switch (option) {
                case 1: {
                    System.out.println("Enter Movie Name");
                    String movieName = sc.next();

                    String insertName = "INSERT INTO movies (moviename) VALUES('" + movieName + "')";
                    PreparedStatement ps2 = connection.prepareStatement(insertName);
                    ps2.executeUpdate();
                    System.out.println("Moviename Insterted Successfully");
                    ps2.close();
                    break;
                }
                case 2: {
                    System.out.println("Enter Movie Name");
                    String movieName = sc.next();
                    String deleteName = "DELETE FROM movies WHERE moviename = '" + movieName + "'";
                    PreparedStatement ps3 = connection.prepareStatement(deleteName);
                    ps3.executeUpdate();
                    System.out.println("Moviename Deleted Successfully");
                    ps3.close();
                    break;
                }
                case 3: {
                    System.out.println("Enter Movie Name (Which you want to update)");
                    String movieName = sc.next();
                    System.out.println("Enter Movie Name (What you want to update)");
                    String newName = sc.next();

                    String updateName = "UPDATE movies SET moviename = '" + newName + "' WHERE moviename = '" + movieName + "'";
                    PreparedStatement ps4 = connection.prepareStatement(updateName);
                    ps4.executeUpdate();
                    System.out.println("Moviename Updated Successfully");
                    ps4.close();
                    break;
                }
                case 4: {
                    String select = "SELECT * FROM movies";
                    PreparedStatement ps5 = connection.prepareStatement(select);
                    ResultSet name = ps5.executeQuery();
                    while (name.next()) {
                        String movieName = name.getString("moviename");
                        System.out.println("Movie: " + movieName);
                    }
                    ps5.close();
                    break;
                }

            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Disconnected from database.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}
