package com.example.foodcravings;

import java.sql.*;
import java.sql.Connection;
import java.util.List;

public class Food {
    private static ResultSet rs;
    private static Connection connection;

    public static List<String> selectFood(String sql) {
        List<String> listOfFood = new java.util.ArrayList<>(List.of());
        try {
            connection = SetConnection.connect();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listOfFood.add((rs.getString("matNamn") + "\t"));
//                        "Favorit: " + rs.getInt("matFavorit") + "\t" +
//                        "Kategori: " + rs.getInt("matKategoriID")));
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
        return listOfFood;
    }
}


