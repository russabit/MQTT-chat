package com.rsabitov.mqtt_chat.UI.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rsabitov.mqtt_chat.R;
import com.rsabitov.mqtt_chat.UI.viewModels.MessagesViewModel;
import com.rsabitov.mqtt_chat.UI.adapters.MessagesRecyclerViewAdapter;

public class MessagesFragment extends Fragment {

    private MessagesRecyclerViewAdapter mAdapter;
    private EditText textToSend;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MessagesViewModel messagesViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        initRecyclerView(view);
        messagesViewModel.getAllMessages().observe(getViewLifecycleOwner(), messages -> mAdapter.setMessagesList(messages));
        Button send = view.findViewById(R.id.send_button);
        textToSend = view.findViewById(R.id.text_to_send);
        send.setOnClickListener(v -> {
            //messagesViewModel.sendMessage(new Message(textToSend.getText().toString(), messagesViewModel.getContactTopic()));
            messagesViewModel.publishMessage(textToSend.getText().toString());
            textToSend.getText().clear();
        });
        getActivity().setTitle(messagesViewModel.getContactTopic());
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.messages_recycler);
        mAdapter = new MessagesRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
