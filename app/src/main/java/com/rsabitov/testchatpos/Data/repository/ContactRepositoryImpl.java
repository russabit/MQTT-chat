package com.rsabitov.testchatpos.Data.repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.ContactMqttDao;
import com.rsabitov.testchatpos.Data.MqttHelper;
import com.rsabitov.testchatpos.Data.RoomDao.ContactDao;
import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.Domain.repository.ContactRepository;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;
    private ContactMqttDao mContactMqttDao;
    private MqttHelper mqttHelper;

    private MutableLiveData<String> mContactName;

    public LiveData<String> getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        this.mContactName.setValue(contactName);
    }

    public ContactRepositoryImpl(Application application) {
        ChatDatabase db = ChatDatabase.getDatabase(application);
        mContactDao = db.getContactDao();
        mAllContacts = mContactDao.getAll();

        mqttHelper = MqttHelper.getInstance(application);
        mContactMqttDao = mqttHelper.getContactMqttDao();
    }

    @Override
    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    @Override
    public void insert(Contact contact) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mContactDao.insert(contact));
    }

    @Override
    public Contact getContactByName(String name) {
        return mContactDao.getByName(name);
    }

    @Override
    public LiveData<String> getIncomingContactName() {
        return mContactMqttDao.getIncomingContact();
    }

/*    private Boolean isExistingContact(String topic) {
        String contactName = topic.substring(0, topic.length() - 1);
        for (Contact contact : getAllContacts().getValue()) {
            //if (contact.name.equals(contactName)) contactId = contact.id;
            return true;
        }
        return false;
    }*/
}
