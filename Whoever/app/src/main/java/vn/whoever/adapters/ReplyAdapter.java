package vn.whoever.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.Reply;

/**
 * Created by spider man on 1/13/2016.
 */
public class ReplyAdapter extends BaseAdapter {

    private ArrayList<Reply> replyList;
    private Context context;

    public ReplyAdapter(Context context) {
        this.context = context;
        replyList = new ArrayList<Reply>();
    }

    @Override
    public int getCount() {
        return replyList.size();
    }

    @Override
    public Object getItem(int position) {
        return replyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return replyList.get(position).getIdReply();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

        }

        //View rowView = LayoutInflater.from(parent.getContext()).
          //      inflate(R.layout.item_status_layout, null);



        return convertView;
    }

    public void loadReplyList() {
        /**
         * TODO: load database from sqlite show to
         */
    }
}
