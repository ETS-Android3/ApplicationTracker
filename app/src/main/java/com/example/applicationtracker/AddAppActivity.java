package com.example.applicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

public class AddAppActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
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

        queryApplication();

    }

    private void queryApplication() {
        ParseQuery<Application> query = ParseQuery.getQuery(Application.class);
        query.findInBackground(new FindCallback<Application>() {
            @Override
            public void done(List<Application> applications, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(Application application : applications){
                    Log.i(TAG, "Application:" + application.getCompName());
                }
            }
        });
    }
}