package com.example.applicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;
import java.util.List;

public class AddAppActivity extends AppCompatActivity {

    public static final String TAG = "AddAppActivity";
    private EditText etCompName;
    private EditText etJobTitle;
    private EditText etStatus;
    private CalendarView evDate;
    private EditText etNotes;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app);

        etCompName = findViewById(R.id.etCompName);
        etJobTitle = findViewById(R.id.etJobTitle);
        etStatus = findViewById(R.id.etStatus);
        evDate = findViewById(R.id.cvDate);
        etNotes = findViewById(R.id.etNotes);
        btnSubmit = findViewById(R.id.btnSubmit);

        //queryApplication();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CompName = etCompName.getText().toString();
                String JobTitle = etJobTitle.getText().toString();
                String sStatus = etStatus.getText().toString();
                int status = Integer.parseInt(sStatus);
                Date apDate = new Date(evDate.getDate());
                String Notes = etNotes.getText().toString();
                if(CompName.isEmpty() || JobTitle.isEmpty()){
                    Toast.makeText(AddAppActivity.this, "Company Name and Job Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                saveApplication(CompName, JobTitle, currentUser, status, apDate, Notes);
            }
        });

    }

    private void saveApplication(String compName, String jobTitle, ParseUser currentUser, int status, Date apDate, String notes) {
        Application application = new Application();
        application.setCompName(compName);
        application.setJobTitle(jobTitle);
        application.setUser(currentUser);
        application.setStatus(status);
        application.setDateApplied(apDate);
        application.setNotes(notes);
        application.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(AddAppActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "post save was successful!!");
                etCompName.setText("");
                etJobTitle.setText("");
                etStatus.setText("");
                etNotes.setText("");
            }
        });
    }

    private void queryApplication() {
        ParseQuery<Application> query = ParseQuery.getQuery(Application.class);
        query.include(Application.KEY_USER);
        query.findInBackground(new FindCallback<Application>() {
            @Override
            public void done(List<Application> applications, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(Application application : applications){
                    Log.i(TAG, "Application:" + application.getCompName() + ", username: " + application.getUser().getUsername());
                }
            }
        });
    }
}