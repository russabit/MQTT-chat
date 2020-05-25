package com.rsabitov.testchatpos.Data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.dao.ContactDao;
import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.Domain.repository.ContactRepository;

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
