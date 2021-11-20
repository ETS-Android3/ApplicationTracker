package com.example.applicationtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applicationtracker.adapters.ApplicationsAdapter;
import com.example.applicationtracker.models.Application;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    TextView tvCompanyName;
    TextView tvDateApplied;
    TextView tvStatus;
    TextView tvNotes;
    TextView tvJobTitle;
    Application app;
    Button btnEditActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        app = Parcels.unwrap(getIntent().getParcelableExtra(Application.class.getSimpleName()));
//        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", app.getCompanyName()));
//        Toast.makeText(this, app.getCompanyName(), Toast.LENGTH_SHORT).show();
        tvCompanyName = findViewById(R.id.companyName);
        tvDateApplied = findViewById(R.id.dateApplied);
        tvStatus = findViewById(R.id.status);
        tvNotes = findViewById(R.id.tvNotes);
        tvJobTitle = findViewById(R.id.jobTitle);
        btnEditActivity = findViewById(R.id.btnEditActivity);

        btnEditActivity.setOnClickListener(view -> {
            Intent i = new Intent(this, EditActivity.class);
            i.putExtra(Application.class.getSimpleName(), Parcels.wrap(app));
            startActivity(i);
            Log.i("DetailActivity", "clicked edit button");
        });


        tvCompanyName.setText(app.getCompName());
        tvDateApplied.setText(app.getDateApplied().toString());
        tvNotes.setText(app.getNotes());
        tvJobTitle.setText(app.getJobTitle());
    }

}
