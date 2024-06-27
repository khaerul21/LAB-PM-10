package com.example.assignment_5.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.assignment_5.Account;
import com.example.assignment_5.AccountAdapter;
import com.example.assignment_5.DataSource;
import com.example.assignment_5.MainActivity;
import com.example.assignment_5.ProfileActivity;
import com.example.assignment_5.R;

public class HomeFragment extends Fragment {

    private RecyclerView rv_post;
    private AccountAdapter userAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_post = view.findViewById(R.id.rv_post);

        rv_post.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv_post.setHasFixedSize(true);

        userAdapter = new AccountAdapter(DataSource.accounts);
        rv_post.setAdapter(userAdapter);
        return view;
    }
}