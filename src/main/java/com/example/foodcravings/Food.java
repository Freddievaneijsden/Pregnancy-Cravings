package com.example.foodcravings;

import java.sql.*;
import java.sql.Connection;

public class Food {
    private static ResultSet rs;
    private static Connection connection;

    public static String selectFood(String sql) {
        String printFood = "";
        try {
            connection = SetConnection.connect();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                printFood = ("MatID: " + rs.getInt("matID") + "\t" +
                        "Mat: " + rs.getString("matNamn") + "\t" +
                        "Favorit: " + rs.getInt("matFavorit") + "\t" +
                        "Kategori: " + rs.getInt("matKategoriID"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            // Always close resources
            try {
                if (rs != null) rs.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return printFood;
    }
}


