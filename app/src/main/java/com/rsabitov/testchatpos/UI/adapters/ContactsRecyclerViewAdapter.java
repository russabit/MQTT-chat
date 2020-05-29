package com.rsabitov.testchatpos.UI.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsabitov.testchatpos.Domain.model.Topic;
import com.rsabitov.testchatpos.R;

import java.util.List;

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {
    private OnViewListener mOnViewListener;
    private List<Topic> mContactsList;

    public ContactsRecyclerViewAdapter(OnViewListener onViewListener) {
        this.mOnViewListener = onViewListener;
    }

    public void setContactsList(List<Topic> contactsList) {
        mContactsList = contactsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view, mOnViewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contactName.setText(mContactsList.get(position).name);
    }

    @Override
    public int getItemCount() {
        if (mContactsList != null) return mContactsList.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnViewListener onViewListener;
        TextView contactName;
        RelativeLayout parentLayout;

        ViewHolder(@NonNull View itemView,
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
