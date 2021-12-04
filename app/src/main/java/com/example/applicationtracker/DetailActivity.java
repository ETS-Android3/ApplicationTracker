package com.example.applicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationtracker.models.Application;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = "DetailActivity";

    TextView tvCompanyName;
    TextView tvDateApplied;
    TextView tvStatus;
    TextView tvNotes;
    TextView tvJobTitle;
    Application app;
    Button btnEditActivity;
    Button btnDeleteApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        app = Parcels.unwrap(getIntent().getParcelableExtra(Application.class.getSimpleName()));

        tvCompanyName = findViewById(R.id.companyName);
        tvDateApplied = findViewById(R.id.dateApplied);
        tvStatus = findViewById(R.id.status);
        tvNotes = findViewById(R.id.tvNotes);
        tvJobTitle = findViewById(R.id.jobTitle);
        btnEditActivity = findViewById(R.id.btnEditActivity);
        btnDeleteApp = findViewById(R.id.btnDeleteApp);

        btnEditActivity.setOnClickListener(view -> {
            Intent i = new Intent(this, EditActivity.class);
            i.putExtra(Application.class.getSimpleName(), Parcels.wrap(app));
            startActivity(i);
            Log.i(TAG, "clicked edit button");
        });

        // Deletes currently opened application from Parse
        btnDeleteApp.setOnClickListener(view -> app.deleteInBackground(e -> {
            if (e != null) {
                Log.e(TAG, "done: ", e);
            } else {
                Intent i = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(i);
                Log.i(TAG, "done: Deleted application");
                finish();
            }
        }));

        tvCompanyName.setText(app.getCompName());
        SimpleDateFormat dateOnly = new SimpleDateFormat("EEEE MMMM dd, yyyy", Locale.getDefault());
        tvDateApplied.setText(dateOnly.format(app.getDateApplied()));
        tvNotes.setText(app.getNotes());
        tvJobTitle.setText(app.getJobTitle());
        tvStatus.setBackgroundColor(app.statusToColor(app.getStatus()));
    }
}
