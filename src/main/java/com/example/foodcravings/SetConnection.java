package com.example.foodcravings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SetConnection {

    private static Connection conn = null;
    private static final String url = "jdbc:sqlite:/Users/freddievaneijsden/Desktop/ITHS/Utveckling mot databaser/Vecka 4 - SQLite/FoodCravings.db";

    public static Connection connect() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
