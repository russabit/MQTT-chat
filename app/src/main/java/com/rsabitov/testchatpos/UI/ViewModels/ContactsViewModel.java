package com.rsabitov.testchatpos.UI.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.Contact;
import com.rsabitov.testchatpos.Data.ContactRepositoryImpl;
import com.rsabitov.testchatpos.Domain.ContactRepository;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private ContactRepository mContactRepository;

    private LiveData<List<Contact>> mAllContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepositoryImpl(application); //later will inject a singleton
        mAllContacts = mContactRepository.getAllContacts();
    }

    public LiveData<List<Contact>> getAllContacts() { return mAllContacts; }

    public void insert(Contact contact) { mContactRepository.insert(contact); }
}
