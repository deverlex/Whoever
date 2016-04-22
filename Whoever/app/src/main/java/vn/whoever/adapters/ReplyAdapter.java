package vn.whoever.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.ArrayReply;
import vn.whoever.models.Comment;

/**
 * Created by spider man on 1/13/2016.
 */
public class ReplyAdapter extends BaseAdapter {

    private ArrayList<Comment> commentList;
    private Activity activity;

    private int count;
    private int stepNumber;
    private int startCount;

    public ReplyAdapter(Activity activity, int startCount, int stepNumber) {
        this.activity = activity;

        ArrayReply arrayReply = new ArrayReply();
        commentList = arrayReply.getReplies();

        this.startCount = Math.min(startCount, commentList.size());
        this.count = this.startCount;
        this.stepNumber = stepNumber;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return commentList.get(position).getIdReply();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comment comment = (Comment) getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_reply_layout, null);
        }

        //View rowView = LayoutInflater.from(parent.getContext()).
          //      inflate(R.layout.item_status_layout, null);

        return convertView;
    }

    public boolean showMore() {
        if(count == commentList.size()) {
            return true;
        } else {
            count = Math.min(count + stepNumber, commentList.size());
            notifyDataSetChanged();
            return endReached();
        }
    }

    public boolean endReached() {
        return count == commentList.size();
    }

    public void reset() {
        count = startCount;
        notifyDataSetChanged();
    }

    public void loadReplyList() {
        /**
         * TODO: load database from sqlite show to
         */
    }

    public int getSize() {
        return commentList.size();
    }
}
