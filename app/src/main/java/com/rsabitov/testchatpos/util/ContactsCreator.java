package com.rsabitov.testchatpos.util;

import com.rsabitov.testchatpos.DB.Contact;

import java.util.ArrayList;
import java.util.Arrays;

//a test method, will remove later
public class ContactsCreator {
    public static ArrayList<Contact> getContactsList() {
        ArrayList<Contact> contactNames;

        {
            contactNames = new ArrayList<>(Arrays.asList(new Contact("Ruslan"), new Contact("Anton"), new Contact("Andrei"), new Contact("Dasha"), new Contact("Katya"), new Contact("Sasha"), new Contact("Masha")));
        }

        return contactNames;
    }
}
