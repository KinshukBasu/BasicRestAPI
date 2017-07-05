package services;

/**
 * Created by KinshukBasu on 04-Jul-17.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {

    private static String url = "jdbc:mysql://localhost:3306/javabase";
    private static String username = "admin";
    private static String password = "password";

    public Connection conn;

    public DBAccess(){
        conn = null;
    }

    public void establishConnection(){
        System.out.println("Connecting database...");

        try{
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void closeConnection(){
        if (this.conn != null) try {
            this.conn.close();
        } catch (SQLException logOrIgnore) {
        }
    }
}
