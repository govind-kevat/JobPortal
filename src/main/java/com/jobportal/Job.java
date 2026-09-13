package com.jobportal;

public class Job {

    private int id;
    private String title;
    private String description;
    private String skills;
    private String salary;
    private String location;
    private String jobType;
    private int companyId;
    private String postedDate;

    public Job() {
    }

    public Job(String title, String description, String skills,
               String salary, String location, String jobType,
               int companyId) {

        this.title = title;
        this.description = description;
        this.skills = skills;
        this.salary = salary;
        this.location = location;
        this.jobType = jobType;
        this.companyId = companyId;
    }

    public Job(int id, String title, String description, String skills,
               String salary, String location, String jobType,
               int companyId, String postedDate) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.salary = salary;
        this.location = location;
        this.jobType = jobType;
        this.companyId = companyId;
        this.postedDate = postedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}