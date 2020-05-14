package com.rsabitov.testchatpos.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.DB.ChatDatabase;
import com.rsabitov.testchatpos.DB.Contact;
import com.rsabitov.testchatpos.DB.ContactDao;

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
    public void insertFakeContacts(List<Contact> contactsList) {
        for (Contact contact : contactsList) {
            ChatDatabase.databaseWriteExecutor.execute(() -> mContactDao.insert(contact));
        }
    }
}
