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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.Data.MqttHelper;
import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.UI.ViewModels.ContactsViewModel;
import com.rsabitov.testchatpos.UI.ViewModels.MessagesViewModel;
import com.rsabitov.testchatpos.UI.adapters.ContactsRecyclerViewAdapter;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class ContactsFragment extends Fragment implements ContactsRecyclerViewAdapter.OnViewListener {

    private ContactsViewModel mContactsViewModel;
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
        mContactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        mMessagesViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
        initRecyclerView(view);
        mContactsViewModel.getAllContacts().observe(getViewLifecycleOwner(), contacts -> {
            mAdapter.setContactsList(contacts);
            mContactNames = contacts;
        });
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.new_contact));
        startMqtt();
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

    private void startMqtt() {
        MqttHelper mqttHelper = new MqttHelper(getContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.w("Debug", message.toString());
                Toast.makeText(getActivity(), "new message from " + topic + ": " + message.toString(), Toast.LENGTH_SHORT).show();
                mContactsViewModel.insert(new Contact(topic));
                //mMessagesViewModel.sendMessage(new Message(message, ));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

}
