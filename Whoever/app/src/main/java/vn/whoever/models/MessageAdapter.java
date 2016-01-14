package vn.whoever.models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by spider man on 1/13/2016.
 */
public class MessageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Message> messageList;

    public MessageAdapter(Context context) {
        this.context = context;
        messageList = new ArrayList<Message>();
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1;// messageList.get(position).
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

        }

        return convertView;
    }

    public void loadMessageList() {

    }
}
