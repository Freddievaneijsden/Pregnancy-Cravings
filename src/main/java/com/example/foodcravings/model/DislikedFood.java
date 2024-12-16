package com.example.foodcravings.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.example.foodcravings.SetConnection.connect;

public class DislikedFood {
    private static ResultSet rs;
    private static Connection connection;

    public static List<String> selectDislikedFood() {

        String sql = "SELECT * FROM mat WHERE matOgillar = 1";
        List<String> listOfDisliked = new java.util.ArrayList<>(List.of());
        try {
            connection = connect();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listOfDisliked.add((rs.getString("matNamn") + "\t"));
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
        return listOfDisliked;
    }
}
