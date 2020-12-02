package com.rsabitov.mqtt_chat.UI.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.rsabitov.mqtt_chat.Domain.model.Topic;
import com.rsabitov.mqtt_chat.R;
import com.rsabitov.mqtt_chat.UI.viewModels.ContactsViewModel;

public class NewContactFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newcontact_fragment, container, false);
        ContactsViewModel contactsViewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);

        final EditText editText = view.findViewById(R.id.contact_name_text);
        final Button save = view.findViewById(R.id.save_button);

        save.setOnClickListener(v -> {
            String topicName = editText.getText().toString();
            contactsViewModel.insert(new Topic(topicName));
            contactsViewModel.subscribeToTopic(topicName);
            Navigation.findNavController(getView()).navigate(R.id.contacts);
        });

        return view;
    }
}
