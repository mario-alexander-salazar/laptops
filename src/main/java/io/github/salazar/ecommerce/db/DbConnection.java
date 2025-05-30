package io.github.salazar.ecommerce.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/bd_laptops";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private DbConnection() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER); // Optional since JDBC 4.0, but kept for compatibility
        } catch (ClassNotFoundException e) {
            System.err.println("Database Driver not found.");
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
