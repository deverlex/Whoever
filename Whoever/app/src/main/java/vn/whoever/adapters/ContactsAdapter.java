package vn.whoever.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.ArrayContact;
import vn.whoever.models.Contact;

/**
 * Created by spider man on 1/13/2016.
 */
public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> contacts;

    public ContactsAdapter(Context context) {
        this.context = context;
        loadContactList();
    }

    @Override
    public int getCount() {
        if(contacts != null) {
            return contacts.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(contacts != null) {
            return contacts.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_contact_layout, null);
        }

        return convertView;
    }

    public void loadContactList() {
        ArrayContact arrayContact = new ArrayContact();
        contacts = arrayContact.getContacts();
    }
}
