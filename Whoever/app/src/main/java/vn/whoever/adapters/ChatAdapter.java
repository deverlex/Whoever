package vn.whoever.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.customviews.RoundedImageView;
import vn.whoever.models.Message;

/**
 * Created by spider man on 1/26/2016.
 */
public class ChatAdapter extends BaseAdapter {

    private ArrayList<Message> messages;
    private ArrayList<ViewHolder> viewHolders;
    private Context context;
    private int position;

    public ChatAdapter(Context context) {
        this.context = context;
        viewHolders = new ArrayList<>();
    }

    public ChatAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
        viewHolders = new ArrayList<>();
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

        ViewHolder holder;
        Message message = messages.get(position);
        this.position = position;

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_layout, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.imageReceiver.setImageBitmap(null);
        //holder.imageSender.setImageBitmap(null);
        setAlignment(holder, true);

        return convertView;
    }

    public void add(Message message) {
        this.messages.add(message);
    }

    public void add(ArrayList<Message> messages) {
        this.messages.addAll(messages);
    }

    public void setAlignment(ViewHolder holder, boolean isMe) {
        if(isMe) {
            if(messages.get(position-1).getIsme()) {

            }
        } else {

        }
    }

    public ViewHolder createViewHolder(View view) {
        ViewHolder holder = new ViewHolder();
        holder.message = (TextView) view.findViewById(R.id.textMessageContentChat);
        holder.layoutMessage = (LinearLayout) view.findViewById(R.id.layoutContentMessageItemChat);
        holder.state = (TextView) view.findViewById(R.id.textStateMessageChat);
        holder.layoutState = (LinearLayout) view.findViewById(R.id.layoutStateMessageChat);
        holder.imageSender = (RoundedImageView) view.findViewById(R.id.avatarSenderItemMessageChat);
        holder.imageReceiver = (RoundedImageView) view.findViewById(R.id.avatarReceiverItemMessageChat);
        return holder;
    }

    class ViewHolder {
        public TextView message;
        public LinearLayout layoutMessage;
        public TextView state;
        public LinearLayout layoutState;
        public RoundedImageView imageSender; // TODO: This me
        public RoundedImageView imageReceiver;
    }
}
