package com.example.assignment_5;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder>{
    private final ArrayList<Account> accounts;
    public AccountAdapter(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
    @NonNull
    @Override
    public AccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountAdapter.ViewHolder holder, int position) {
        Account user = accounts.get(position);

        holder.fullname.setText(user.getFullname());
        holder.username.setText(user.getUsername());
        holder.caption.setText(user.getCaption());
        holder.profile.setImageResource(user.getProfile());
        Integer post = user.getPost();
        Uri addpost = user.getAddpost();

        if (post != null) {
            holder.post.setImageResource(user.getPost());
        } else if (addpost != null) {
            holder.post.setImageURI(user.getAddpost());
        }

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.PROFILE_DATA, user);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.fullname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.PROFILE_DATA, user);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullname, username, caption;
        ImageView profile, post;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.postFullname);
            username = itemView.findViewById(R.id.postUsername);
            caption = itemView.findViewById(R.id.postCaption);
            profile = itemView.findViewById(R.id.postProfile);
            post = itemView.findViewById(R.id.postImage);
        }
    }
}
