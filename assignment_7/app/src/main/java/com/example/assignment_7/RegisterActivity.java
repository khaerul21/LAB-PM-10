package com.example.assignment_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText nim_register, pass_register;
    private Button register;
    private final String KEY_DARK_MODE = "darkMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nim_register = findViewById(R.id.nim_register);
        pass_register = findViewById(R.id.pass_register);
        register = findViewById(R.id.register);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = nim_register.getText().toString().trim();
                String password = pass_register.getText().toString().trim();

                if (!nim.isEmpty() && !password.isEmpty()) {
                    saveData(nim, password);
                }

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        boolean isDarkModeEnable = sharedPreferences.getBoolean(KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(isDarkModeEnable ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void saveData(String nim, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NIM", nim);
        editor.putString("Password", password);
        editor.apply();
    }
}