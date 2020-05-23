package com.rsabitov.testchatpos.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.ChatDatabase_Impl;
import com.rsabitov.testchatpos.Data.ContactDao;
import com.rsabitov.testchatpos.Domain.Contact;
import com.rsabitov.testchatpos.Domain.ContactRepository;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    public ContactRepositoryImpl(Application application) {
        ChatDatabase db = ChatDatabase.getDatabase(application);
        mContactDao = db.getContactDao();
        mAllContacts = mContactDao.getAll();
    }

    @Override
    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    @Override
    public void insert(Contact contact) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mContactDao.insert(contact));
    }
}
