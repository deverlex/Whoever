package vn.whoever.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.views.customviews.RoundedImageView;
import vn.whoever.models.ArrayMessage;
import vn.whoever.models.Message;

/**
 * Created by Nguyen Van Do on 1/26/2016.
 * This class isn't complete at now.
 */
public class MessageAdapter extends BaseAdapter {

    private ArrayList<Message> messages;
    private ArrayList<ViewHolder> viewHolders;
    private Activity activity;
    private int position;
    private boolean oldState;
    private int oldPosition;

    private int stepNumber;
    private int startCount;

    public MessageAdapter(Activity activity, int startCount, int stepNumber) {
        this.activity = activity;
        viewHolders = new ArrayList<>();
        loadListMessageChat();
        oldPosition = 0;

        this.stepNumber = stepNumber;
        this.startCount = startCount;
    }

    public MessageAdapter(Activity activity, ArrayList<Message> messages) {
        this.activity = activity;
        this.messages = messages;
        viewHolders = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (messages != null) {
            return messages.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (messages != null) {
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

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_layout, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
            viewHolders.add(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == 0 && oldPosition == 0) {
            oldState = message.getIsme();
            viewHolders.get(position).space.setVisibility(View.GONE);
        }
        if (position > oldPosition) {
            setAlignment(holder, message.getIsme());
            holder.message.setText(message.getMessage());
            oldPosition = position;
        }

        return convertView;
    }

    public void add(Message message) {
        this.messages.add(message);
    }

    public void add(ArrayList<Message> messages) {
        this.messages.addAll(messages);
    }

    public void setAlignment(ViewHolder holder, boolean isMe) {
        ViewHolder lholder;

        if (position > 0) {
            // TODO: need config later
            if (messages.get(position).getDate().getTime() - messages.get(position - 1).getDate().getTime() < 10000) {
                viewHolders.get(position - 1).layoutState.setVisibility(View.GONE);
            }

            if (position == 1) {
                Log.d("isMe: ", String.valueOf(isMe));
                Log.d("oldState: ", String.valueOf(oldState));
            }

            if (oldState != isMe) {
                viewHolders.get(position - 1).space.setVisibility(View.INVISIBLE);
            }
            oldState = isMe; // isMe of position - 1
        }

        viewHolders.get(position).space.setVisibility(View.GONE);

        // TODO: change position layout for item message and avatar message
        /**
         * TODO: me is sender
         */
        if (isMe) {
            if (position > 0 && messages.get(position - 1).getIsme()) {
                lholder = viewHolders.get(position - 1);
                lholder.imageSender.setVisibility(View.INVISIBLE);
                lholder.layoutMessage.setBackgroundResource(R.drawable.in_message_ex);
                lholder.layoutState.setVisibility(View.GONE);
            }
            holder.imageReceiver.setVisibility(View.INVISIBLE);
            // TODO: change icon of background content message and change gravity of layout content msg
            holder.layoutMessage.setBackgroundResource(R.drawable.in_message);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.layoutMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.layoutMessage.setLayoutParams(layoutParams);

            holder.message.setTextColor(Color.parseColor("#ffffff"));

        } else {
            if (position > 0 && !messages.get(position - 1).getIsme()) {
                lholder = viewHolders.get(position - 1);
                lholder.imageReceiver.setVisibility(View.INVISIBLE);
                lholder.layoutMessage.setBackgroundResource(R.drawable.out_message_ex);
                lholder.layoutState.setVisibility(View.GONE);
            }

            holder.imageSender.setVisibility(View.INVISIBLE);
            holder.layoutMessage.setBackgroundResource(R.drawable.out_message);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.layoutMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.layoutMessage.setLayoutParams(layoutParams);

            holder.message.setTextColor(Color.parseColor("#000000"));
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
        holder.space = (Space) view.findViewById(R.id.spaceBetweenItemMessageChat);
        return holder;
    }

    /**
     * TODO: get data message
     */

    public void loadListMessageChat() {
        ArrayMessage arrayMessage = new ArrayMessage();
        messages = arrayMessage.getMessages();
    }

    class ViewHolder {
        public TextView message;
        public LinearLayout layoutMessage;
        public TextView state;
        public LinearLayout layoutState;
        public RoundedImageView imageSender; // TODO: This me
        public RoundedImageView imageReceiver;
        public Space space;
    }
}
