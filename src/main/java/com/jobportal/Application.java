package com.jobportal;

public class Application {

    private int id;
    private int jobId;
    private int userId;
    private String applicationDate;
    private String status;

    // Job details
    private String jobTitle;
    private String companyName;
    private String location;
    private String salary;


    public Application() {
    }


    public Application(int jobId, int userId) {
        this.jobId = jobId;
        this.userId = userId;
    }


    public Application(
            int id,
            int jobId,
            int userId,
            String applicationDate,
            String status
    ) {

        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.applicationDate = applicationDate;
        this.status = status;
    }


    // Constructor with Job details

    public Application(
            int id,
            int jobId,
            int userId,
            String applicationDate,
            String status,
            String jobTitle,
            String companyName,
            String location,
            String salary
    ) {

        this.id = id;
        this.jobId = jobId;
        this.userId = userId;
        this.applicationDate = applicationDate;
        this.status = status;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.salary = salary;
    }


    // Getters

    public int getId() {
        return id;
    }

    public int getJobId() {
        return jobId;
    }

    public int getUserId() {
        return userId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getSalary() {
        return salary;
    }


    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}