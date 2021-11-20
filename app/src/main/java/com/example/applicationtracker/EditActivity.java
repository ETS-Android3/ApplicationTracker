package com.example.applicationtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applicationtracker.models.Application;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    public static final String TAG = "EditActivity";
    private EditText etEditCompName;
    private EditText etEditJobTitle;
    private EditText etEditStatus;
    private CalendarView cvEditDate;
    private EditText etEditNotes;
    private Button btnEditSubmit;
    Application app;
    long appDate;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        app = Parcels.unwrap(getIntent().getParcelableExtra(Application.class.getSimpleName()));

        etEditCompName = findViewById(R.id.etEditCompName);
        etEditJobTitle = findViewById(R.id.etEditJobTitle);
        etEditStatus = findViewById(R.id.etEditStatus);
        cvEditDate = findViewById(R.id.cvEditDate);
        etEditNotes = findViewById(R.id.etEditNotes);
        btnEditSubmit = findViewById(R.id.btnEditSubmit);

        etEditCompName.setText(app.getCompName());
        etEditJobTitle.setText(app.getJobTitle());
        etEditStatus.setText(String.valueOf(app.getStatus()));
        appDate = app.getDateApplied().getTime();
        cvEditDate.setDate(appDate);
        etEditNotes.setText(app.getNotes());

        btnEditSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CompName = etEditCompName.toString();
                String JobTitle = etEditJobTitle.toString();
                //TODO:status check if empty
                if (CompName.isEmpty() || JobTitle.isEmpty()) {
                    Toast.makeText(EditActivity.this, "Company Name and Job Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                app.setCompName(etEditCompName.getText().toString());
                app.setJobTitle(etEditJobTitle.getText().toString());
                String sStatus = etEditStatus.getText().toString();
                app.setStatus(Integer.parseInt(sStatus));
                app.setNotes(etEditNotes.getText().toString());
                app.setDateApplied(new Date(appDate));


                ParseUser currentUser = ParseUser.getCurrentUser();
                app.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "Error while saving", e);
                            Toast.makeText(EditActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.i(TAG, "post save was successful!!");
                        Intent i = new Intent(EditActivity.this, MainActivity.class);
                        startActivity(i);
                    }

                });

            }
        });


        cvEditDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Calendar c = Calendar.getInstance();
                c.set(i, i1, i2);
                appDate = c.getTimeInMillis();
            }
        });
    }
}