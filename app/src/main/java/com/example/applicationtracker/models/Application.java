package com.example.applicationtracker.models;

import java.util.Date;

public class Application {
    String jobTitle;
    String companyName;
    Date dateApplied;
    int status;

    public Application(String jobTitle, String companyName, Date dateApplied, int status) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.dateApplied = dateApplied;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(Date dateApplied) {
        this.dateApplied = dateApplied;
    }
}

