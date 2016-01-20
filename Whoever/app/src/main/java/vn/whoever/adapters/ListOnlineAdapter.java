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
 * Created by spider man on 1/20/2016.
 */
public class ListOnlineAdapter extends BaseAdapter {

    private ArrayList<Contact>  userOnlineList;

    private Context context;

    public ListOnlineAdapter(Context context) {
        this.context = context;
        loadListOnline();
    }

    @Override
    public int getCount() {
        if(userOnlineList !=  null) {
            return userOnlineList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(userOnlineList != null) {
            return userOnlineList.get(position);
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
                    .inflate(R.layout.item_online_layout, null);
        }

        return convertView;
    }

    public void loadListOnline() {
        ArrayContact arrayContact = new ArrayContact();
        userOnlineList = arrayContact.getContacts();
    }
}
