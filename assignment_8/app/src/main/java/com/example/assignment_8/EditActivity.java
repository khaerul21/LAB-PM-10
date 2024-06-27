package com.example.assignment_8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edit_title, edit_description;
    Button btn_edit_submit;
    String id, title, description, time;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_title = findViewById(R.id.edit_title);
        edit_description = findViewById(R.id.edit_description);
        btn_edit_submit = findViewById(R.id.btn_edit_submit);
        toolbar = findViewById(R.id.edit_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        String dateText = dateFormat.format(date);
        String editedDateText = "Edited at " + dateText;
        id = getIntent().getStringExtra("id");
        getIntentData();

        btn_edit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edit_title.getText().toString().isEmpty() && !edit_description.getText().toString().isEmpty()) {
                    title = edit_title.getText().toString();
                    description = edit_description.getText().toString();
                    DatabaseHelper myDB = new DatabaseHelper(EditActivity.this);
                    myDB.updateData(id, title, description, editedDateText);
                    Intent intent = new Intent(EditActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "Isi semua inputan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getIntentData() {
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("title")
                && getIntent().hasExtra("description") && getIntent().hasExtra("time")) {

            edit_title.setText(getIntent().getStringExtra("title"));
            edit_description.setText(getIntent().getStringExtra("description"));
        } else {
            Toast.makeText(this, "No data..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem = item.getItemId();
        if (idItem == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (idItem == R.id.action_delete) {
            new AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        DatabaseHelper myDB = new DatabaseHelper(EditActivity.this);
                        myDB.deleteNote(id);
                        Intent intent = new Intent(EditActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin kembali tanpa menyimpan perubahan?")
                .setPositiveButton("Ya", (dialog, which) -> super.onBackPressed())
                .setNegativeButton("Tidak", null)
                .show();
    }
}