package com.rsabitov.testchatpos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsabitov.testchatpos.R;

import java.util.ArrayList;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {
    private OnViewListener onViewListener;
    private ArrayList<String> contactsList;

    public ContactsRecyclerViewAdapter(OnViewListener onViewListener, ArrayList<String> contactsList) {
        this.onViewListener = onViewListener;
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view, onViewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contactName.setText(contactsList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnViewListener onViewListener;
        TextView contactName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView,
                          OnViewListener onViewListener) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contact_name);
            this.onViewListener = onViewListener;
            parentLayout = itemView.findViewById(R.id.contact_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }

    public interface OnViewListener {
        void onViewClick(int position);
    }
}
