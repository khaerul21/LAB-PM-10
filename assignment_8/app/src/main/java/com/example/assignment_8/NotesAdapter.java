package com.example.assignment_8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private ArrayList id, time, title, description;
    private OnNoteClickListener listener;
    private ArrayList<String> idFull, timeFull, titleFull, descriptionFull;

    NotesAdapter(Context context, ArrayList<String> id, ArrayList<String> time, ArrayList<String> title, ArrayList<String> description) {
        this.context = context;
        this.id = id;
        this.time = time;
        this.title = title;
        this.description = description;
        this.idFull = new ArrayList<>(id);
        this.timeFull = new ArrayList<>(time);
        this.titleFull = new ArrayList<>(title);
        this.descriptionFull = new ArrayList<>(description);
    }
    public interface OnNoteClickListener {
        void onNoteClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_notes, parent, false);

        return new MyViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.MyViewHolder holder, int position) {
        holder.tv_time.setText(String.valueOf(time.get(position)));
        holder.tv_title.setText(String.valueOf(title.get(position)));
        holder.tv_description.setText(String.valueOf(description.get(position)));

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onNoteClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView item;
        TextView tv_time, tv_title, tv_description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            tv_time = itemView.findViewById(R.id.time);
            tv_title = itemView.findViewById(R.id.title);
            tv_description = itemView.findViewById(R.id.description);
        }
    }

    public Filter getFilter() {
        return notesFilter;
    }

    private Filter notesFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> filteredList = new ArrayList<>();
            List<String> filteredIds = new ArrayList<>();
            List<String> filteredTimes = new ArrayList<>();
            List<String> filteredDescriptions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(titleFull);
                filteredIds.addAll(idFull);
                filteredTimes.addAll(timeFull);
                filteredDescriptions.addAll(descriptionFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (int i = 0; i < titleFull.size(); i++) {
                    if (titleFull.get(i).toLowerCase().contains(filterPattern)) {
                        filteredList.add(titleFull.get(i));
                        filteredIds.add(idFull.get(i));
                        filteredTimes.add(timeFull.get(i));
                        filteredDescriptions.add(descriptionFull.get(i));
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = new Object[]{filteredList, filteredIds, filteredTimes, filteredDescriptions};
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Object[] resultArray = (Object[]) results.values;
            title.clear();
            id.clear();
            time.clear();
            description.clear();
            title.addAll((List) resultArray[0]);
            id.addAll((List) resultArray[1]);
            time.addAll((List) resultArray[2]);
            description.addAll((List) resultArray[3]);
            notifyDataSetChanged();
        }
    };
}
