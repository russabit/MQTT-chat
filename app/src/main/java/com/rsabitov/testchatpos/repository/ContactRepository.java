package com.rsabitov.testchatpos.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.DB.Contact;

import java.util.List;

public interface ContactRepository {
    LiveData<List<Contact>> getAllContacts();
    void insert(Contact contact);
    /*Contact getContactByName(String name);
    void update(Contact contact);
    void delete(Contact contact);
    void deleteAll();*/
}
