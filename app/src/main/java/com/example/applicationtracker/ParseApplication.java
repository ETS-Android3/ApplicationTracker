package com.example.applicationtracker;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(com.example.applicationtracker.Application.class);

        // Register your parse models
        //ParseObject.registerSubclass(Application.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qyNnfVMVIkKLp9uEmKCZ5NTZPzGM5YqtatQSd0F2")
                .clientKey("E0uVfyjtvvPVHkrBglBbFFIspF4W82mr41NvdnGn")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}