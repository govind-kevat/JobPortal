package com.jobportal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    // ==========================================
    // ADD NEW JOB
    // ==========================================

    public boolean addJob(Job job) {

        String sql = "INSERT INTO jobs " +
                "(title, description, skills, salary, location, job_type, company_id, posted_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE())";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, job.getTitle());
            statement.setString(2, job.getDescription());
            statement.setString(3, job.getSkills());
            statement.setString(4, job.getSalary());
            statement.setString(5, job.getLocation());
            statement.setString(6, job.getJobType());
            statement.setInt(7, job.getCompanyId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==========================================
    // GET ALL JOBS
    // ==========================================

    public List<Job> getAllJobs() {

        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT * FROM jobs";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Job job = new Job(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("skills"),
                        resultSet.getString("salary"),
                        resultSet.getString("location"),
                        resultSet.getString("job_type"),
                        resultSet.getInt("company_id"),
                        resultSet.getString("posted_date")
                );

                jobs.add(job);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return jobs;
    }


    // ==========================================
    // UPDATE JOB
    // ==========================================

    public boolean updateJob(Job job) {

        String sql = "UPDATE jobs SET " +
                "title = ?, " +
                "description = ?, " +
                "skills = ?, " +
                "salary = ?, " +
                "location = ?, " +
                "job_type = ? " +
                "WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, job.getTitle());
            statement.setString(2, job.getDescription());
            statement.setString(3, job.getSkills());
            statement.setString(4, job.getSalary());
            statement.setString(5, job.getLocation());
            statement.setString(6, job.getJobType());
            statement.setInt(7, job.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }


    // ==========================================
    // DELETE JOB
    // ==========================================

    public boolean deleteJob(int jobId) {

        String sql =
                "DELETE FROM jobs WHERE id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, jobId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}