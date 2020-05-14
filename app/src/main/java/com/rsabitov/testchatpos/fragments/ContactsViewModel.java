package com.rsabitov.testchatpos.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.DB.Contact;
import com.rsabitov.testchatpos.repository.ContactRepositoryImpl;
import com.rsabitov.testchatpos.repository.ContactRepository;
import com.rsabitov.testchatpos.util.ContactsCreator;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private ContactRepository mContactRepository;

    private LiveData<List<Contact>> mAllContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepositoryImpl(application); //later will inject a singleton
        mAllContacts = mContactRepository.getAllContacts();
        mContactRepository.insertFakeContacts(ContactsCreator.getContactsList());
    }

    LiveData<List<Contact>> getAllContacts() { return mAllContacts; }
}
