package com.example.assignment_5;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private ArrayList<Account> searchs;
    private HashSet<String> uniqueUsernames;

    public SearchAdapter(ArrayList<Account> searchs) {
        this.searchs = searchs;
        this.uniqueUsernames = new HashSet<>();
    }

    public void setFilteredUsers(ArrayList<Account> filteredUsers) {
        uniqueUsernames.clear();
        ArrayList<Account> filteredList = new ArrayList<>();
        for (Account user : filteredUsers) {
            if (!uniqueUsernames.contains(user.getUsername())) {
                filteredList.add(user);
                uniqueUsernames.add(user.getUsername());
            }
        }
        this.searchs = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Account search = searchs.get(position);
        holder.fullname.setText(search.getFullname());
        holder.username.setText(search.getUsername());
        holder.profile.setImageResource(search.getProfile());
        holder.searchlinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ProfileActivity.class);
                intent.putExtra(ProfileActivity.PROFILE_DATA, search);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fullname, username;
        ImageView profile;
        LinearLayout searchlinear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.searchFullname);
            username = itemView.findViewById(R.id.searchUsername);
            profile = itemView.findViewById(R.id.searchProfile);
            searchlinear = itemView.findViewById(R.id.searchlinier);
        }
    }
}
