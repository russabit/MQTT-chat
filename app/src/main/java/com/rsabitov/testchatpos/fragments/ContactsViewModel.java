package com.rsabitov.testchatpos.fragments;

import androidx.lifecycle.ViewModel;

import com.rsabitov.testchatpos.util.ContactsCreator;

import java.util.ArrayList;
import java.util.Arrays;

public class ContactsViewModel extends ViewModel {
    ArrayList<String> contactNames = ContactsCreator.getContactsList();
}
