package com.example.foodcravings.model;

import java.sql.*;
import java.sql.Connection;
import java.util.List;

import static com.example.foodcravings.SetConnection.connect;

public class Food {
    private static ResultSet rs;
    private static Connection connection;

    public static List<String> selectFood(String sql) {
        List<String> listOfFood = new java.util.ArrayList<>(List.of());
        try {
            connection = connect();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listOfFood.add((rs.getString("matNamn") + "\t"));
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

    public static void insertNewtFood(String newFood) {
        String sql = "INSERT INTO mat (matNamn) VALUES(?)"; //Prepared statements
        try {
            connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, newFood);
            pstmt.executeUpdate();
            System.out.println("Du har lagt till " + newFood + "!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteFood(String food) {
        String sql = "DELETE FROM mat WHERE matNamn = ?";
        try {
            connection = connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, food);
            pstmt.executeUpdate();
            System.out.println("Du har tagit bort " + food + "!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


