package vn.whoever.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.whoever.R;
import vn.whoever.models.Contact;
import vn.whoever.views.customviews.RoundedImageView;

/**
 * Created by Nguyen Van Do on 1/13/2016.
 * Class set adapter update database to UI Contact
 */
public class ContactsAdapter extends AbstractAdapter<Contact> {

    public ContactsAdapter(Fragment fragment, List<Contact> dataList, RecyclerView recyclerView) {
        super(fragment, dataList, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_layout, parent, false);
            vh = new ContactViewHandler(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_footer, parent, false);
            vh = new ProgressViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactViewHandler) {
            Contact contact = (Contact) dataList.get(position);
            if (!contact.getGroupName().equals("")) {
                ((ContactViewHandler) holder).groupName.setText(contact.getGroupName());
                ((ContactViewHandler) holder).groupName.setVisibility(View.VISIBLE);
                ((ContactViewHandler) holder).lineTop.setVisibility(View.VISIBLE);
            } else {
                ((ContactViewHandler) holder).groupName.setVisibility(View.GONE);
                ((ContactViewHandler) holder).lineTop.setVisibility(View.GONE);
            }
            ((ContactViewHandler) holder).nickName.setText(contact.getNickName());
            ((ContactViewHandler) holder).latestOnline.setText(contact.getLatestOnline());
            ((ContactViewHandler) holder).latestStatus.setText(contact.getLatestStatus());
            ((ContactViewHandler) holder).contact = contact;
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    // Set class describe contact view object
    public class ContactViewHandler extends RecyclerView.ViewHolder {

        public TextView groupName;
        public TextView nickName;
        public TextView latestStatus;
        public TextView latestOnline;
        public RoundedImageView avatartContact;
        public Contact contact;
        public View lineTop;

        public ContactViewHandler(View view) {
            super(view);
            groupName = (TextView) view.findViewById(R.id.groupTextContactList);
            nickName = (TextView) view.findViewById(R.id.nickNameOnContactList);
            latestStatus = (TextView) view.findViewById(R.id.textLatestStatus);
            latestOnline = (TextView) view.findViewById(R.id.timeLatestActive);
            avatartContact = (RoundedImageView) view.findViewById(R.id.imageAvatarOnContacts);
            lineTop = (View) view.findViewById(R.id.lineBetweenContacts);
        }
    }
}
