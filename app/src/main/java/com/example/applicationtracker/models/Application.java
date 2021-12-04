package com.example.applicationtracker.models;

import android.graphics.Color;

import com.example.applicationtracker.R;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Application")
public class Application extends ParseObject {

    public static final String KEY_COMPANY_NAME = "compName";
    public static final String KEY_DATE_APPLIED = "dateApplied";
    public static final String KEY_JOB_TITLE = "jobTitle";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_STATUS = "Status";
    public static final String KEY_USER = "user";


    public String getCompName(){
        return getString(KEY_COMPANY_NAME);
    }

    public void setCompName(String compName){
        put(KEY_COMPANY_NAME, compName);
    }

    public Date getDateApplied() {
        return getDate(KEY_DATE_APPLIED);
    }

    public void setDateApplied(Date dateApplied){
        put(KEY_DATE_APPLIED, dateApplied);
    }

    public String getJobTitle(){
        return getString(KEY_JOB_TITLE);
    }

    public void setJobTitle(String jobTitle){
        put(KEY_JOB_TITLE, jobTitle);
    }

    public int getStatus(){ return getInt(KEY_STATUS);}

    public void setStatus(int status){
        put(KEY_STATUS, status);
    }

    public int statusToColor2(int status) {
        if (status == 1) {
            return R.drawable.green_circle;
        } else if (status == 2) {
            return R.drawable.yellow_circle;
        } else if (status == 3) {
            return R.drawable.red_circle;
        } else {
            return R.drawable.gray_circle;
        }
    }

    public String getNotes(){return getString(KEY_NOTES);}

    public void setNotes(String notes){put(KEY_NOTES, notes);}

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser (ParseUser user){
        put(KEY_USER, user);
    }
}
