package com.jobportal;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            System.out.println("JDBC Connection Successful!");
        }
    }
}