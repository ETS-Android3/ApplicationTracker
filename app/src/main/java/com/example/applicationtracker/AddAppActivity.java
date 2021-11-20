package com.example.applicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.applicationtracker.models.Application;
import com.parse.ParseUser;

import java.util.Calendar;
import java.util.Date;


public class AddAppActivity extends AppCompatActivity {

    public static final String TAG = "AddAppActivity";
    private EditText etCompName;
    private EditText etJobTitle;

    private RadioButton rbtnInterviewing;
    private RadioButton rbtnOffer;
    private RadioButton rbtnWaiting;
    private RadioButton rbtnRejected;

    private CalendarView cvDate;
    private EditText etNotes;
    private Button btnSubmit;
    long appDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);

        etCompName = findViewById(R.id.etCompName);
        etJobTitle = findViewById(R.id.etJobTitle);

        rbtnInterviewing = findViewById(R.id.rbtnInterviewing);
        rbtnOffer = findViewById(R.id.rbtnOffer);
        rbtnRejected = findViewById(R.id.rbtnRejected);
        rbtnWaiting = findViewById(R.id.rbtnWaiting);
        rbtnWaiting.setChecked(true);

        cvDate = findViewById(R.id.cvDate);
        etNotes = findViewById(R.id.etNotes);
        btnSubmit = findViewById(R.id.btnSubmit);

        cvDate.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            Calendar c = Calendar.getInstance();
            c.set(i, i1, i2);
            appDate = c.getTimeInMillis();
        });

        btnSubmit.setOnClickListener(view -> {
            String CompName = etCompName.getText().toString();
            String JobTitle = etJobTitle.getText().toString();

            int status;
            if(rbtnInterviewing.isChecked()){
                status = 2;
            } else if (rbtnOffer.isChecked()) {
                status = 1;
            } else if (rbtnRejected.isChecked()) {
                status = 3;
            } else {
                status = 4;
            }

            String Notes = etNotes.getText().toString();
            Date date = new Date(appDate);
            if(CompName.isEmpty() || JobTitle.isEmpty()){
                Toast.makeText(AddAppActivity.this, "Company Name and Job Title cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            ParseUser currentUser = ParseUser.getCurrentUser();
            saveApplication(CompName, JobTitle, currentUser, status, Notes, date);
        });
    }

    private void saveApplication(String compName, String jobTitle, ParseUser currentUser, int status, String notes, Date date) {
        Application application = new Application();
        application.setCompName(compName);
        application.setJobTitle(jobTitle);
        application.setUser(currentUser);
        application.setStatus(status);
        application.setDateApplied(date);
        application.setNotes(notes);
        application.saveInBackground(e -> {
            if (e != null){
                Log.e(TAG, "Error while saving", e);
                Toast.makeText(AddAppActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.i(TAG, "post save was successful!!");
            Intent i = new Intent(AddAppActivity.this, MainActivity.class);
            startActivity(i);
        });
    }
}