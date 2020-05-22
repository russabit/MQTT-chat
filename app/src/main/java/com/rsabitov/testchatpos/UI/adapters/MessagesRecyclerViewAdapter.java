package com.rsabitov.testchatpos.UI.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rsabitov.testchatpos.Domain.Message;
import com.rsabitov.testchatpos.R;

import java.util.List;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<MessagesRecyclerViewAdapter.ViewHolder> {
    private List<Message> mMessagesList;

    public void setMessagesList(List<Message> messagesList) {
        mMessagesList = messagesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.messageName.setText(mMessagesList.get(position).message);
    }

    @Override
    public int getItemCount() {
        if (mMessagesList != null) return mMessagesList.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageName;
        RelativeLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageName = itemView.findViewById(R.id.message);
            parentLayout = itemView.findViewById(R.id.message_layout);
        }
    }
}
