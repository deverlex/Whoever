package vn.whoever.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.Message;

/**
 * Created by spider man on 1/26/2016.
 */
public class ChatAdapter extends BaseAdapter {

    private ArrayList<Message> messages;
    private Activity context;

    public ChatAdapter(Activity context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        if(messages != null) {
            return messages.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(messages != null) {
            return messages.get(position);
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

        Message message = messages.get(position);


        return convertView;
    }


}
