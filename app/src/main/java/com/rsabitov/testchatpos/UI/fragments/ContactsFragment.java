package com.rsabitov.testchatpos.UI.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.UI.ViewModels.ContactsViewModel;
import com.rsabitov.testchatpos.UI.ViewModels.MessagesViewModel;
import com.rsabitov.testchatpos.UI.adapters.ContactsRecyclerViewAdapter;

import java.util.List;

public class ContactsFragment extends Fragment implements ContactsRecyclerViewAdapter.OnViewListener {

    private MessagesViewModel mMessagesViewModel;
    private ContactsRecyclerViewAdapter mAdapter;
    private List<Contact> mContactNames;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);
        ContactsViewModel mContactsViewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);
        mMessagesViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
        initRecyclerView(view);
        mContactsViewModel.getAllContacts().observe(getViewLifecycleOwner(), contacts -> {
            mAdapter.setContactsList(contacts);
            mContactNames = contacts;
        });
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.new_contact));
        mContactsViewModel.getNewContact().observe(getViewLifecycleOwner(), s -> {
            Toast.makeText(getContext(), "new message from " + s, Toast.LENGTH_SHORT).show();
            //mContactsViewModel.insert(new Contact(s));
        });
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
        mMessagesViewModel.setContactId(mContactNames.get(position).id);
        Navigation.findNavController(getView()).navigate(R.id.messages);
    }



}
