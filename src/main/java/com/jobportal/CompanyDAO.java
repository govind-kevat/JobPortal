package com.jobportal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDAO {

    // Add company
    public boolean addCompany(Company company) {

        String sql = "INSERT INTO companies " +
                "(company_name, location, website, recruiter_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getLocation());
            statement.setString(3, company.getWebsite());
            statement.setInt(4, company.getRecruiterId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get company by ID
    public Company getCompanyById(int companyId) {

        String sql = "SELECT * FROM companies WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, companyId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Company(
                        resultSet.getInt("id"),
                        resultSet.getString("company_name"),
                        resultSet.getString("location"),
                        resultSet.getString("website"),
                        resultSet.getInt("recruiter_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}