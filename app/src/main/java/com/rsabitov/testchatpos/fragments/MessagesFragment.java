package com.rsabitov.testchatpos.fragments;

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

import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.adapters.MessagesRecyclerViewAdapter;

import java.util.Objects;

public class MessagesFragment extends Fragment {

    private MessagesRecyclerViewAdapter mAdapter;

    public static MessagesFragment newInstance() {
        return new MessagesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MessagesViewModel mMessagesViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
        View view = inflater.inflate(R.layout.messages_fragment, container, false);
        initRecyclerView(view);
        mMessagesViewModel.getAllMessages().observe(getViewLifecycleOwner(), messages -> mAdapter.setMessagesList(messages));
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.messages_recycler);
        mAdapter = new MessagesRecyclerViewAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
