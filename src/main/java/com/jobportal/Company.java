package com.jobportal;

public class Company {

    private int id;
    private String companyName;
    private String location;
    private String website;
    private int recruiterId;

    public Company() {
    }

    public Company(String companyName, String location,
                   String website, int recruiterId) {

        this.companyName = companyName;
        this.location = location;
        this.website = website;
        this.recruiterId = recruiterId;
    }

    public Company(int id, String companyName, String location,
                   String website, int recruiterId) {

        this.id = id;
        this.companyName = companyName;
        this.location = location;
        this.website = website;
        this.recruiterId = recruiterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(int recruiterId) {
        this.recruiterId = recruiterId;
    }
}