package com.rsabitov.testchatpos.fragments;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rsabitov.testchatpos.util.MessagesCreator;

import java.util.ArrayList;

public class MessagesViewModel extends ViewModel {
    ArrayList<String> listOfMessages = MessagesCreator.getMessagesList();

    private MutableLiveData<String> selectedContact = new MutableLiveData<>();

    public MutableLiveData<String> getContact() {
        return selectedContact;
    }

    public void setContact(String selectedContact) {
        this.selectedContact.setValue(selectedContact);
        if (listOfMessages.size() == 4) {  listOfMessages.remove(1); }
        listOfMessages.add(1, getContact().getValue());
    }
}
