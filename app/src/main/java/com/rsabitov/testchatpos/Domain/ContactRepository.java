package com.rsabitov.testchatpos.Domain;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface ContactRepository {
    LiveData<List<Contact>> getAllContacts();
    void insert(Contact contact);
    /*Contact getContactByName(String name);
    void update(Contact contact);
    void delete(Contact contact);
    void deleteAll();*/
}
