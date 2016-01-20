package vn.whoever.models;

import java.util.ArrayList;

import vn.whoever.dao.ConnectionDB;

/**
 * Created by spider man on 1/20/2016.
 */
public class ArrayContact {

    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts() {
        contacts = new ArrayList<>();

        Contact contact = new Contact();

        for(int i = 0 ; i < 15; ++i) {
            contacts.add(contact);
        }

        return contacts;
    }
}
