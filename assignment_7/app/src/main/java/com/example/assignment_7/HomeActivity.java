package com.example.assignment_7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    private TextView selamat_datang;
    private Button logout, setting;
    private SharedPreferences sharedPreferences;
    private static final String KEY_DARK_MODE = "darkMode";
    private static final String KEY_LOGIN_STATUS = "isLoggedIn";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.logout);
        setting = findViewById(R.id.setting);
        selamat_datang = findViewById(R.id.selamat_datang);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        selamat_datang.setText("Selamat Datang " + sharedPreferences.getString("NIM", ""));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(KEY_LOGIN_STATUS, false);
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SettingActivity.class));
            }
        });

        boolean isDarkModeEnable = sharedPreferences.getBoolean(KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(isDarkModeEnable ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}