package com.jobportal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // ==========================================
    // APPLY FOR JOB
    // ==========================================

    public boolean applyForJob(Application application) {

        String sql =
                "INSERT INTO applications " +
                        "(job_id, user_id, application_date, status) " +
                        "VALUES (?, ?, CURDATE(), 'APPLIED')";

        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    application.getJobId()
            );

            statement.setInt(
                    2,
                    application.getUserId()
            );

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }


    // ==========================================
    // GET USER APPLICATIONS
    // WITH JOB + COMPANY DETAILS
    // ==========================================

    public List<Application> getUserApplications(int userId) {

        List<Application> applications =
                new ArrayList<>();


        String sql =
                "SELECT " +
                        "a.id, " +
                        "a.job_id, " +
                        "a.user_id, " +
                        "a.application_date, " +
                        "a.status, " +
                        "j.title AS job_title, " +
                        "j.location AS job_location, " +
                        "j.salary AS job_salary, " +
                        "c.company_name " +
                        "FROM applications a " +
                        "JOIN jobs j ON a.job_id = j.id " +
                        "JOIN companies c ON j.company_id = c.id " +
                        "WHERE a.user_id = ? " +
                        "ORDER BY a.id DESC";


        try (
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(
                    1,
                    userId
            );


            ResultSet resultSet =
                    statement.executeQuery();


            while (resultSet.next()) {

                Application application =
                        new Application(

                                resultSet.getInt("id"),

                                resultSet.getInt("job_id"),

                                resultSet.getInt("user_id"),

                                resultSet.getString(
                                        "application_date"
                                ),

                                resultSet.getString(
                                        "status"
                                ),

                                resultSet.getString(
                                        "job_title"
                                ),

                                resultSet.getString(
                                        "company_name"
                                ),

                                resultSet.getString(
                                        "job_location"
                                ),

                                resultSet.getString(
                                        "job_salary"
                                )
                        );


                applications.add(
                        application
                );
            }


        } catch (SQLException e) {

            e.printStackTrace();
        }


        return applications;
    }
}