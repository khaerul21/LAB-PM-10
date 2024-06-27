package com.example.assignment_7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity {

    EditText nim_login, pass_login;
    Button login_button, regis_intent;
    private SharedPreferences sharedPreferences;
    private static final String KEY_DARK_MODE = "darkMode";
    private static final String KEY_LOGIN_STATUS = "isLoggedIn";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nim_login = findViewById(R.id.nim_login);
        pass_login = findViewById(R.id.pass_login);
        login_button = findViewById(R.id.login_button);
        regis_intent = findViewById(R.id.regis_intent);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
                finish();
            }
        });

        regis_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        boolean isDarkModeEnable = sharedPreferences.getBoolean(KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(isDarkModeEnable ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_LOGIN_STATUS, false);
        if (isLoggedIn) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }

    private void loginUser() {
        String savedNIM = sharedPreferences.getString("NIM", "");
        String savedPassword = sharedPreferences.getString("Password", "");

        String inputNIM = nim_login.getText().toString().trim();
        String inputPassword = pass_login.getText().toString().trim();

        if (inputNIM.equals(savedNIM) && inputPassword.equals(savedPassword)) {
            Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_LOGIN_STATUS, true);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "NIM atau password salah!", Toast.LENGTH_SHORT).show();
        }
    }
}