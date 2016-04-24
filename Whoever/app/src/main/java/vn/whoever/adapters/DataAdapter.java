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
import vn.whoever.models.SearchContact;
import vn.whoever.views.customviews.RoundedImageView;

/**
 * Created by spider man on 4/24/2016.
 */
public class DataAdapter extends AbstractAdapter<SearchContact> {

    public DataAdapter(Fragment fragment, List<SearchContact> dataList, RecyclerView recyclerView) {
        super(fragment, dataList, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addcontact_layout, parent, false);
            vh = new ContactViewHolder (view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_footer, parent, false);
            vh = new ProgressViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ContactViewHolder) {
            SearchContact singleSearchContact = dataList.get(position);

            ((ContactViewHolder) holder).nickNameAddAccount.setText(singleSearchContact.getNickName());
            ((ContactViewHolder) holder).searchContact = singleSearchContact;

            ((ContactViewHolder) holder).nickNameAddAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            ((ContactViewHolder) holder).avatartAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            ((ContactViewHolder) holder).btnFlowAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        public RoundedImageView avatartAccount;
        public TextView nickNameAddAccount;
        public ImageButton btnFlowAccount;

        public SearchContact searchContact;

        public ContactViewHolder(View view) {
            super(view);

            avatartAccount = (RoundedImageView) view.findViewById(R.id.avatarAddContact);
            nickNameAddAccount = (TextView) view.findViewById(R.id.nickNameAddAccount);
            btnFlowAccount = (ImageButton) view.findViewById(R.id.btnFollowNewAccount);
        }
    }
}
