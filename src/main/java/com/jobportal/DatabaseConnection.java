package com.jobportal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/job_portal";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "Govind@123";

    public static Connection getConnection() {

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            System.out.println("Database Connected Successfully!");

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL JDBC Driver Not Found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return null;
    }
}