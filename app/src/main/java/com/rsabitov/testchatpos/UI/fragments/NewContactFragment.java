package com.rsabitov.testchatpos.UI.fragments;

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

import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.UI.ViewModels.ContactsViewModel;

public class NewContactFragment extends Fragment {
    private ContactsViewModel mContactsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newcontact_fragment, container, false);
        mContactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        final EditText editText = view.findViewById(R.id.contact_name_text);
        final Button save = view.findViewById(R.id.save_button);
        save.setOnClickListener(v -> {
            mContactsViewModel.insert(new Contact(editText.getText().toString()));
            Navigation.findNavController(getView()).navigate(R.id.contacts);
        });

        return view;
    }
}
