package com.example.applicationtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.applicationtracker.adapters.ApplicationsAdapter;
import com.example.applicationtracker.models.Application;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
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

        rvApplications.setAdapter(adapter);
        rvApplications.setLayoutManager(layoutManager);

        queryPosts();

    }

    private void queryPosts() {
        ParseQuery<Application> query = ParseQuery.getQuery(Application.class);
        query.whereEqualTo(Application.KEY_USER, ParseUser.getCurrentUser());
        Log.i("MainActivity", "queryPosts: starting query");
        query.findInBackground((parseApplications, e) -> {
            if(e != null) {
                Log.e("MainActivity", "Issue with getting applications", e);
                Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                return;
            }
            for(Application application : parseApplications) {
                Toast.makeText(MainActivity.this, application.getCompName(), Toast.LENGTH_LONG).show();
            }
            applications.addAll(parseApplications);
            adapter.notifyDataSetChanged();
        });
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
        if(item.getItemId() == R.id.add){
            goAddActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goLogoutActivity() {
        Intent i = new Intent(this, LogoutActivity.class);
        startActivity(i);
        finish();

    }
    private void goAddActivity() {
        Intent i = new Intent(this, AddAppActivity.class);
        startActivity(i);
    }
}