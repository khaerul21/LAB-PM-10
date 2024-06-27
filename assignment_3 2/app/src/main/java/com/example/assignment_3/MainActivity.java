package com.example.assignment_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assignment_3.Adapter.PostAdapter;
import com.example.assignment_3.Adapter.StoryAdapter;
import com.example.assignment_3.Model.AccountModel;

public class MainActivity extends AppCompatActivity{

    private RecyclerView rv_story, rv_post;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StoryAdapter storyAdapter = new StoryAdapter(MainActivity.this, DataSource.account);
        rv_story = findViewById(R.id.rv_story);
        rv_story.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        rv_story.setAdapter(storyAdapter);



        PostAdapter postAdapter = new PostAdapter(MainActivity.this, DataSource.account);
        rv_post = findViewById(R.id.rv_post);
        rv_post.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        rv_post.setAdapter(postAdapter);

    }
}