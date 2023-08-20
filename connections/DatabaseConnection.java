/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connections;

/**
 *
 * @author hp
 */
import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "123abc");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: unable to establish a database connection.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("Error: database connection is not established.");
        }
        try {
            if (!connection.isValid(0)) {
                throw new SQLException("Error: database connection is not valid.");
            }
        } catch (SQLException e) {
            System.out.println("Error: database connection is not valid.");
        }
        return connection;
    }
}
