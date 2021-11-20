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

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    public static final String TAG = "EditActivity";
    private EditText etEditCompName;
    private EditText etEditJobTitle;

    private RadioButton rbtnEditInterviewing;
    private RadioButton rbtnEditOffer;
    private RadioButton rbtnEditWaiting;
    private RadioButton rbtnEditRejected;

    private CalendarView cvEditDate;
    private EditText etEditNotes;
    private Button btnEditSubmit;
    Application app;
    long appDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        app = Parcels.unwrap(getIntent().getParcelableExtra(Application.class.getSimpleName()));

        etEditCompName = findViewById(R.id.etEditCompName);
        etEditJobTitle = findViewById(R.id.etEditJobTitle);

        rbtnEditInterviewing = findViewById(R.id.rbtnEditInterviewing);
        rbtnEditOffer = findViewById(R.id.rbtnEditOffer);
        rbtnEditRejected = findViewById(R.id.rbtnEditRejected);
        rbtnEditWaiting = findViewById(R.id.rbtnEditWaiting);

        cvEditDate = findViewById(R.id.cvEditDate);
        etEditNotes = findViewById(R.id.etEditNotes);
        btnEditSubmit = findViewById(R.id.btnEditSubmit);

        etEditCompName.setText(app.getCompName());
        etEditJobTitle.setText(app.getJobTitle());

        switch(app.getStatus()) {
            case 1:
                rbtnEditOffer.setChecked(true);
                break;
            case 2:
                rbtnEditInterviewing.setChecked(true);
                break;
            case 3:
                rbtnEditRejected.setChecked(true);
                break;
            case 4:
            default:
                rbtnEditWaiting.setChecked(true);
        }

        appDate = app.getDateApplied().getTime();
        cvEditDate.setDate(appDate);
        etEditNotes.setText(app.getNotes());

        btnEditSubmit.setOnClickListener(view -> {
            String CompName = etEditCompName.toString();
            String JobTitle = etEditJobTitle.toString();
            if (CompName.isEmpty() || JobTitle.isEmpty()) {
                Toast.makeText(EditActivity.this, "Company Name and Job Title cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            app.setCompName(etEditCompName.getText().toString());
            app.setJobTitle(etEditJobTitle.getText().toString());

            if(rbtnEditInterviewing.isChecked()){
                app.setStatus(2);
            } else if (rbtnEditOffer.isChecked()) {
                app.setStatus(1);
            } else if (rbtnEditRejected.isChecked()) {
                app.setStatus(3);
            } else {
                app.setStatus(4);
            }

            app.setNotes(etEditNotes.getText().toString());
            app.setDateApplied(new Date(appDate));

            app.saveInBackground(e -> {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(EditActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "post save was successful!!");
                Intent i = new Intent(EditActivity.this, MainActivity.class);
                startActivity(i);
            });

        });

        cvEditDate.setOnDateChangeListener((calendarView, i, i1, i2) -> {
            Calendar c = Calendar.getInstance();
            c.set(i, i1, i2);
            appDate = c.getTimeInMillis();
        });
    }
}