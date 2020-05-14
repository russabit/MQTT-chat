package com.rsabitov.testchatpos.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.DB.Contact;

import java.util.List;

public interface ContactRepository {
    LiveData<List<Contact>> getAllContacts();
    void insertFakeContacts(List<Contact> contactsList); //a test method, will remove later
    /*Contact getContactByName(String name);
    void insert(Contact contact);
    void update(Contact contact);
    void delete(Contact contact);
    void deleteAll();*/
}
