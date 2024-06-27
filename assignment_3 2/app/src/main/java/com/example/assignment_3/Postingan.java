package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment_3.Model.AccountModel;

public class Postingan extends AppCompatActivity {
    private ImageView postProfile2, postImage2;
    private TextView postUsername2, postCaption2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postingan);

        postProfile2 = findViewById(R.id.postProfile2);
        postUsername2 = findViewById(R.id.postUsername2);
        postImage2 = findViewById(R.id.postImage2);
        postCaption2 = findViewById(R.id.postCaption2);

        Intent intent = getIntent();
        AccountModel account = intent.getParcelableExtra("post");
        postProfile2.setImageResource(account.getImage());
        postUsername2.setText(account.getUsername());
        postImage2.setImageResource(account.getImage());
        postCaption2.setText(account.getCaption());

        postUsername2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        postProfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Postingan.this, StoryView.class);
                intent.putExtra("story", account);
                startActivity(intent);
            }
        });
    }
}