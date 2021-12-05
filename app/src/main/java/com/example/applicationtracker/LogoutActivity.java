package com.example.applicationtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class LogoutActivity extends AppCompatActivity {
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> {
            ParseUser.logOut();
            goLoginActivity();
        });
    }
    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        //prevent user from accessing applications after logging out
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

}