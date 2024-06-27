package com.example.assignment_5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profile;
    private TextView fullname, username;
    ProgressBar spinner;
    Account userprofile;
    LinearLayout linearLayout;

    Handler handler = new Handler(Looper.getMainLooper());

    public static final String PROFILE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.imageProfile);
        fullname = findViewById(R.id.name);
        username = findViewById(R.id.username);
        spinner = findViewById(R.id.spinner);
        linearLayout = findViewById(R.id.linearlayout);

        userprofile = getIntent().getParcelableExtra(PROFILE_DATA);
        linearLayout.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);

                profile.setImageResource(userprofile.getProfile());
                fullname.setText(userprofile.getFullname());
                username.setText(userprofile.getUsername());

            }
        }, 1000);


    }
}