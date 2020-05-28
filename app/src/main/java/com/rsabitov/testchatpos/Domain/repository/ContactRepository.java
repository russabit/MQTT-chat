package com.rsabitov.testchatpos.Domain.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Contact;

import java.util.List;

public interface ContactRepository {
    LiveData<List<Contact>> getAllContacts();
    void insert(Contact contact);
    Contact getContactByName(String name);
    LiveData<String> getIncomingContactName();
    /*void update(Contact contact);
    void delete(Contact contact);
    void deleteAll();*/
}
