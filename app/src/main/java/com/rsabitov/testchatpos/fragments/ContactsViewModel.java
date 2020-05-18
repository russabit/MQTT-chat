package com.rsabitov.testchatpos.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rsabitov.testchatpos.DB.Contact;
import com.rsabitov.testchatpos.repository.ContactRepositoryImpl;
import com.rsabitov.testchatpos.repository.ContactRepository;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private ContactRepository mContactRepository;

    private LiveData<List<Contact>> mAllContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepositoryImpl(application); //later will inject a singleton
        mAllContacts = mContactRepository.getAllContacts();
    }

    LiveData<List<Contact>> getAllContacts() { return mAllContacts; }

    public void insert(Contact contact) { mContactRepository.insert(contact); }
}
