package com.example.foodcravings;

import java.sql.*;
import java.sql.Connection;

import static javax.management.remote.JMXConnectorFactory.connect;

public class Food {
    private ResultSet rs;
    private Connection connection;

    public void selectFood(String sql) {
        try {
            connection = SetConnection.connect();
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println("EventID: " + rs.getInt("eventID") + "\t" +
                        "EventTitle: " + rs.getString("eventTitle") + "\t" +
                        "Price: " + rs.getString("eventPrice") + "\t" +
                        "CategoryID: " + rs.getString("eventCategoryID") + "\t" +
                        "Favourite: " + rs.getInt("eventFavourite"));
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
    }
}


