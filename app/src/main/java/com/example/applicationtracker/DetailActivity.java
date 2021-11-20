package com.example.applicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationtracker.models.Application;
import com.parse.DeleteCallback;
import com.parse.ParseException;

import org.parceler.Parcels;

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
//        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", app.getCompanyName()));
//        Toast.makeText(this, app.getCompanyName(), Toast.LENGTH_SHORT).show();
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
        btnDeleteApp.setOnClickListener(view -> {
            app.deleteInBackground(new DeleteCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null) {
                        Log.e(TAG, "done: ", e);
                    } else {
                        Intent i = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(i);
                        Log.i(TAG, "done: Deleted application");
                        finish();
                    }
                }
            });
        });


        tvCompanyName.setText(app.getCompName());
        tvDateApplied.setText(app.getDateApplied().toString());
        tvNotes.setText(app.getNotes());
        tvJobTitle.setText(app.getJobTitle());
    }

}
