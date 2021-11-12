package com.example.applicationtracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applicationtracker.adapters.ApplicationsAdapter;
import com.example.applicationtracker.models.Application;

import java.util.ArrayList;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }

}
