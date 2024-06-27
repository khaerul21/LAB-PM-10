package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment_3.Model.AccountModel;

public class StoryView extends AppCompatActivity {
    private ImageView storyImage;
    private TextView storyUsername;
    private int profileImage;
    private String username;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);

        storyImage = findViewById(R.id.storyImageView);
        storyUsername = findViewById(R.id.storyUsernameView);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("story")) {
            AccountModel account = intent.getParcelableExtra("story");
            storyImage.setImageResource(account.getImage());
            storyUsername.setText(account.getUsername());
            getWindow().setBackgroundDrawableResource(account.getStoryBackground());
            storyUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StoryView.this, DetailAccount.class);
                    intent.putExtra("post", account);
                    startActivity(intent);
                }
            });
        } else if (intent != null && intent.hasExtra("post")) {
            AccountModel account = intent.getParcelableExtra("post");
            storyImage.setImageResource(account.getImage());
            storyUsername.setText(account.getUsername());
            getWindow().setBackgroundDrawableResource(account.getStoryBackground());
            storyUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StoryView.this, DetailAccount.class);
                    intent.putExtra("post", account);
                    startActivity(intent);
                }
            });
        }



    }
}