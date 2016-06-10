package vn.whoever.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.ArrayInbox;
import vn.whoever.models.Inbox;

/**
 * Created by Nguyen Van Do on 1/14/2016.
 * This class isn't complete at now
 */
public class InboxAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Inbox> listInbox;

    public InboxAdapter(Context context) {
        this.context = context;
        loadInboxMessage();
    }

    @Override
    public int getCount() {
        if (listInbox != null) {
            return listInbox.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (listInbox != null) {
            return listInbox.get(position);
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
        Inbox inbox = (Inbox) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_inbox_layout, null);
        }
        return convertView;
    }

    private void loadInboxMessage() {
        ArrayInbox arrayInbox = new ArrayInbox();
        listInbox = arrayInbox.getArrayList();
    }
}
