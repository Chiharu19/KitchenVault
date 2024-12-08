package dbms.project.kitchenvault_project.classes;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String DB_PATH = "inventory.db";

    public static Connection connect() throws SQLException {
        Connection connection = null;
        try {

            // Construct database URL
            String dbUrl = "jdbc:sqlite:" + DB_PATH;

            // Connect to SQLite database
            connection = DriverManager.getConnection(dbUrl);
            System.out.println("Connection to SQLite has been established.");

            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("SQLite connection failed: " + e.getMessage());
        }

        return connection;
    }
}
