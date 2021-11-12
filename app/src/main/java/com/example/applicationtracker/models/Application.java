package com.example.applicationtracker.models;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Application {
    String jobTitle;
    String companyName;
    Date dateApplied;
    int status;
    String notes;

    // Empty constructor for Parcelable class
    public Application() {}

    public Application(String jobTitle, String companyName, Date dateApplied, int status, String notes) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.dateApplied = dateApplied;
        this.status = status;
        this.notes = notes;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

