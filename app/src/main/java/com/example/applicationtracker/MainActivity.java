package com.example.applicationtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.applicationtracker.adapters.ApplicationsAdapter;
import com.example.applicationtracker.models.Application;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvApplications;
    ApplicationsAdapter adapter;
    LinearLayoutManager layoutManager;
    List<Application> applications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvApplications = findViewById(R.id.rvApplications);
        applications = new ArrayList<>();
        adapter = new ApplicationsAdapter(applications, this);
        layoutManager = new LinearLayoutManager(this);
        Date testDate =  new Date();
        testDate.setDate(50);
        rvApplications.setAdapter(adapter);
        rvApplications.setLayoutManager(layoutManager);



        applications.add(new Application("SWE", "HP", testDate, 1, "banana"));
        applications.add(new Application("SWER", "HPE", testDate, 2, ""));
        applications.add(new Application("SWERT", "HPF", testDate, 3, "apple"));
        applications.add(new Application("SWEERE", "HPG", testDate, 4, "meta"));
        adapter.notifyDataSetChanged();

//        Intent i = new Intent(this, DetailActivity.class);
//        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            goLogoutActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goLogoutActivity() {
        Intent i = new Intent(this, LogoutActivity.class);
        startActivity(i);
        finish();

    }
}