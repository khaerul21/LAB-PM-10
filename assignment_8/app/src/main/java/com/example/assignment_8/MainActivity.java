package com.example.assignment_8;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    FloatingActionButton add_button;
    RecyclerView rv_notes;
    DatabaseHelper myDB;
    ArrayList<String> id, time, title, description;
    NotesAdapter notesAdapter;
    SearchView searchView;
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == 101){
                    getData();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_notes = findViewById(R.id.rv_notes);
        searchView = findViewById(R.id.searchView2);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                resultLauncher.launch(intent);
            }
        });

        myDB = new DatabaseHelper(this);
        id = new ArrayList<>();
        time = new ArrayList<>();
        title = new ArrayList<>();
        description = new ArrayList<>();
        storeDataInArrays();

        notesAdapter = new NotesAdapter(this, id, time, title, description);
        notesAdapter.setOnNoteClickListener(position -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            intent.putExtra("id", id.get(position));
            intent.putExtra("title", title.get(position));
            intent.putExtra("description", description.get(position));
            intent.putExtra("time", time.get(position));
            resultLauncher.launch(intent);
        });
        rv_notes.setAdapter(notesAdapter);
        rv_notes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                notesAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Tidak ada Data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                description.add(cursor.getString(2));
                time.add(cursor.getString(3));
            }
        }
    }


    void getData() {
        id.clear();
        time.clear();
        title.clear();
        description.clear();
        storeDataInArrays();
        notesAdapter.notifyDataSetChanged();
    }
}