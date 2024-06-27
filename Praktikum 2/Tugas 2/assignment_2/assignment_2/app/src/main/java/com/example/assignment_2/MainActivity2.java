package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("MissingInflatedId")
public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private String nama;
    private String username;
    private String gambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button buttonTwo = findViewById(R.id.buttonTwo);
        buttonTwo.setOnClickListener(this);

        nama = getIntent().getStringExtra("nama");
        username = getIntent().getStringExtra("username");
        gambar = getIntent().getStringExtra("gambar");

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonTwo) {
            EditText editTextTitle = findViewById(R.id.title);
            String title = editTextTitle.getText().toString();

            EditText editTextContent = findViewById(R.id.content);
            String content = editTextContent.getText().toString();
            if (!title.isEmpty() && !content.isEmpty()) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("nama", nama);
                intent.putExtra("username", username);
                intent.putExtra("gambar", gambar);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Semua inputan wajib diisi!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}