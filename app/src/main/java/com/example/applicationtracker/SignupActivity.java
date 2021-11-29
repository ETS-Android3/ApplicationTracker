package com.example.applicationtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class SignupActivity extends AppCompatActivity {
    EditText etNewUsername;
    EditText etNewPassword;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(view -> {
            String username = etNewUsername.getText().toString();
            String password = etNewPassword.getText().toString();
            signupUser(username, password);
        });
    }
    private void signupUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(e -> {
            if(e == null)
            {
                goMainActivity();
            }
            else
            {
                Toast.makeText(SignupActivity.this, "Signup Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}