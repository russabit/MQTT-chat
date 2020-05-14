package com.rsabitov.testchatpos.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsabitov.testchatpos.OnNextFragment;
import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.adapters.ContactsRecyclerViewAdapter;

public class ContactsFragment extends Fragment implements ContactsRecyclerViewAdapter.OnViewListener {
    //NavController navController;
    private ContactsViewModel contactsViewModel;
    private MessagesViewModel messagesViewModel;
    private OnNextFragment listener;

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (OnNextFragment) getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);
        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        messagesViewModel = new ViewModelProvider(getActivity()).get(MessagesViewModel.class);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.contacts_recycler);
        recyclerView.setAdapter(new ContactsRecyclerViewAdapter(this, contactsViewModel.contactNames));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onViewClick(int position) {
        messagesViewModel.setContact(contactsViewModel.contactNames.get(position));
        listener.onNextFragment();
    }
}
