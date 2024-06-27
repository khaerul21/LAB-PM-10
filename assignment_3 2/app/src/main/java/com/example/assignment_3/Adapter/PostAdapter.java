package com.example.assignment_3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_3.DetailAccount;
import com.example.assignment_3.Model.AccountModel;
import com.example.assignment_3.R;
import com.example.assignment_3.StoryView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private ArrayList<AccountModel> posts;
    public PostAdapter(Context context, ArrayList<AccountModel> post) {
        this.posts = post;
        this.context = context;
    }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AccountModel account = posts.get(position);
        holder.postProfile.setImageResource(account.getImage());
        holder.postImage.setImageResource(account.getImage());
        holder.postUsername.setText(account.getUsername());
        holder.postCaption.setText(account.getCaption());

        holder.postUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailAccount.class);
                intent.putExtra("post", account);
                context.startActivity(intent);
            }
        });

        holder.postProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StoryView.class);
                intent.putExtra("story", account);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView postImage, postProfile;
        private TextView postUsername, postCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postProfile = itemView.findViewById(R.id.postProfile);
            postImage = itemView.findViewById(R.id.postImage);
            postUsername = itemView.findViewById(R.id.postUsername);
            postCaption = itemView.findViewById(R.id.postCaption);
        }
    }
}
