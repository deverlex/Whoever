package vn.whoever.adapters;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;

import vn.whoever.R;
import vn.whoever.TransConnection.ContactTransaction;
import vn.whoever.models.SearchContact;
import vn.whoever.views.customviews.RoundedImageView;

/**
 * Created by Nguyen Van Do on 4/24/2016.
 * Class set adapter update database to UI Search Contact.
 */
public class SearchContactAdapter extends AbstractAdapter<SearchContact> {

    public SearchContactAdapter(Fragment fragment, List<SearchContact> dataList, RecyclerView recyclerView) {
        super(fragment, dataList, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addcontact_layout, parent, false);
            vh = new SearchContactViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_footer, parent, false);
            vh = new ProgressViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchContactViewHolder) {
            final SearchContact singleSearchContact = dataList.get(position);

            ((SearchContactViewHolder) holder).nickNameAddAccount.setText(singleSearchContact.getNickName());
            ((SearchContactViewHolder) holder).searchContact = singleSearchContact;

            // Set UI for show check user's friend
            if (singleSearchContact.isFriend()) {
                ((SearchContactViewHolder) holder).btnFlowAccount.setImageResource(R.drawable.icon_account_check);
                ((SearchContactViewHolder) holder).btnFlowAccount.setClickable(true);
            } else {
                ((SearchContactViewHolder) holder).btnFlowAccount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        (new ContactTransaction(fragment.getActivity())).followContact(singleSearchContact.getSsoId());
                        ((SearchContactViewHolder) holder).btnFlowAccount.setImageResource(R.drawable.icon_account_check);
                    }
                });
            }

            // two listener event isn't complete at now
//            ((SearchContactViewHolder) holder).nickNameAddAccount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {}
//            });
//
//            ((SearchContactViewHolder) holder).avatartAccount.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {}
//            });
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    /***
     * Set class describe search contact UI object
     */
    public static class SearchContactViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView avatartAccount;
        public TextView nickNameAddAccount;
        public ImageButton btnFlowAccount;
        public SearchContact searchContact;

        public SearchContactViewHolder(View view) {
            super(view);
            avatartAccount = (RoundedImageView) view.findViewById(R.id.avatarAddContact);
            nickNameAddAccount = (TextView) view.findViewById(R.id.nickNameAddAccount);
            btnFlowAccount = (ImageButton) view.findViewById(R.id.btnFollowNewAccount);
        }
    }
}
