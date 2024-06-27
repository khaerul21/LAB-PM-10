package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("MissingInflatedId")
public class MainActivity3 extends AppCompatActivity {
    private String nama;
    private String username;
    private String gambar;
    private String title;
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nama = getIntent().getStringExtra("nama");
        username = getIntent().getStringExtra("username");
        gambar = getIntent().getStringExtra("gambar");
        Uri uriGambar = Uri.parse(gambar);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        ImageView imageHasil = findViewById(R.id.imageHasil);
        imageHasil.setImageURI(uriGambar);

        TextView namaHasil = findViewById(R.id.namaHasil);
        namaHasil.setText(nama);

        TextView usernameHasil = findViewById(R.id.usernameHasil);
        usernameHasil.setText(username);

        TextView titleHasil = findViewById(R.id.titleHasil);
        titleHasil.setText(title);

        TextView contentHasil = findViewById(R.id.contentHasil);
        contentHasil.setText(content);
    }
}