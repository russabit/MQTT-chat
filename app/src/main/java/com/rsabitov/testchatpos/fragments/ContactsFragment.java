package com.rsabitov.testchatpos.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsabitov.testchatpos.OnNextFragment;
import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.adapters.ContactsRecyclerViewAdapter;

public class ContactsFragment extends Fragment implements ContactsRecyclerViewAdapter.OnViewListener {

    private ContactsViewModel mContactsViewModel;
    //private MessagesViewModel mMessagesViewModel;
    private ContactsRecyclerViewAdapter mAdapter;
    private OnNextFragment mListener;

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnNextFragment) getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);
        mContactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        //mMessagesViewModel = new ViewModelProvider(getActivity()).get(MessagesViewModel.class);
        initRecyclerView(view);
        mContactsViewModel.getAllContacts().observe(getViewLifecycleOwner(), contacts -> mAdapter.setContactsList(contacts));
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.contacts_recycler);
        mAdapter = new ContactsRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onViewClick(int position) {
        //mMessagesViewModel.setContact(mContactsViewModel.contactNames.get(position));
        mListener.onNextFragment();
    }
}
