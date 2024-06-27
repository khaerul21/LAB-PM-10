package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment_3.Model.AccountModel;

public class DetailAccount extends AppCompatActivity {
    ImageView accountProfileImage, imagePostingan, storyImageView;
    TextView accountUsername, followers, following, storyUsernameVIew;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_account);

        accountProfileImage = findViewById(R.id.accountProfileImage);
        accountUsername = findViewById(R.id.accountUsername);
        imagePostingan = findViewById(R.id.imagePostingan);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        storyImageView = findViewById(R.id.storyImageView);
        storyUsernameVIew = findViewById(R.id.storyUsernameView);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("post")) {
            AccountModel account = intent.getParcelableExtra("post");
            accountProfileImage.setImageResource(account.getImage());
            accountUsername.setText(account.getUsername());
            followers.setText(account.getFollowers());
            following.setText(account.getFollowing());
            imagePostingan.setImageResource(account.getImage());

            accountProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailAccount.this, StoryView.class);
                    intent.putExtra("post", account);
                    startActivity(intent);
                }
            });

            imagePostingan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(DetailAccount.this, Postingan.class);
                    intent.putExtra("post", account);
                    startActivity(intent);
                }
            });
        } else if (intent != null && intent.hasExtra("story")) {
            AccountModel story = intent.getParcelableExtra("story");
            storyImageView.setImageResource(story.getImage());
            storyUsernameVIew.setText(story.getUsername());
            getWindow().setBackgroundDrawableResource(story.getStoryBackground());
        }
    }
}