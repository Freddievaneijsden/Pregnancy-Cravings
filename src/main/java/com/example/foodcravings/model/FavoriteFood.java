package com.example.foodcravings.model;

import java.sql.*;
import java.util.List;

import static com.example.foodcravings.SetConnection.connect;

public class FavoriteFood {
    private static ResultSet rs;
    private static Connection connection;

    public static List<String> selectFavoriteFood() {

        String sql = "SELECT * FROM mat WHERE matMums = 1";
        List<String> listOfFavorites = new java.util.ArrayList<>(List.of());
        try {
            connection = connect();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listOfFavorites.add((rs.getString("matNamn") + "\t"));
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
        return listOfFavorites;
    }

    public static void changeToFavorite(String foodName) {
        String sql = "UPDATE mat SET matMums = 1, matOgillar = 0 WHERE matNamn = ?";
        try {
            connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, foodName);
            pstmt.executeUpdate();
            System.out.println("Du har ändrat " + foodName + " till mums!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

