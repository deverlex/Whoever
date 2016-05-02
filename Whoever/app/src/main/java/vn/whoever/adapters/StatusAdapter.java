package vn.whoever.adapters;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vn.whoever.R;
import vn.whoever.TransConnection.StatusTransaction;
import vn.whoever.models.Status;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.fragments.ProfileFragment;
import vn.whoever.views.fragments.CommentFragment;

/**
 * Created by spider man on 4/23/2016.
 */
public class StatusAdapter extends AbstractAdapter<Status> {

    private StatusTransaction statusTransaction;

    public StatusAdapter(Fragment fragment, List<Status> listStatus, RecyclerView recyclerView) {
        super(fragment, listStatus,recyclerView);
        statusTransaction = new StatusTransaction(fragment.getActivity());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status_layout, parent, false);
            vh = new StatusViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_footer, parent, false);
            vh = new ProgressViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof StatusViewHolder) {
            final Status singleStatus = (Status) dataList.get(position);
            ((StatusViewHolder) holder).nickName.setText(singleStatus.getNamePoster());
            ((StatusViewHolder) holder).timeUp.setText(singleStatus.getTimePost());
            ((StatusViewHolder) holder).totalLike.setText(String.valueOf(singleStatus.getTotalLike()));
            ((StatusViewHolder) holder).totalDislike.setText(String.valueOf(singleStatus.getTotalDislike()));
            ((StatusViewHolder) holder).totalComment.setText(String.valueOf(singleStatus.getTotalComment()));
            final String strContent;
            if(singleStatus.getContentImage().equals("null")) {
                // TODO: add image for status
                if(singleStatus.getContentText().length() > 682)
                    strContent = singleStatus.getContentText().substring(0, 682) + "...";
                else strContent = singleStatus.getContentText();
            } else {
                if(singleStatus.getContentText().length() > 268)
                    strContent = singleStatus.getContentText().substring(0,268) + "...";
                else strContent = singleStatus.getContentText();
            }
            ((StatusViewHolder) holder).contentText.setText(strContent);
            ((StatusViewHolder) holder).status = singleStatus;

            if(singleStatus.getInteract().equals("like")) {
                ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like_red);
                ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike);
            } else if(singleStatus.getInteract().equals("dislike")) {
                ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike_red);
                ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like);
            } else {
                ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like);
                ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike);
            }

            ((StatusViewHolder) holder).btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues values = new ContentValues();
                    if(singleStatus.getInteract().equals("like")) {
                        singleStatus.setInteract("normal");
                        ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like);
                        singleStatus.setTotalLike(singleStatus.getTotalLike() - 1);
                        ((StatusViewHolder) holder).totalLike.setText(String.valueOf(singleStatus.getTotalLike()));
                        values.put("interact", "normal");
                        values.put("totalLike", singleStatus.getTotalLike());
                    } else if(singleStatus.getInteract().equals("dislike")) {
                        singleStatus.setInteract("like");
                        ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike);
                        ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like_red);
                        singleStatus.setTotalLike(singleStatus.getTotalLike() + 1);
                        singleStatus.setTotalDislike(singleStatus.getTotalDislike() - 1);
                        ((StatusViewHolder) holder).totalLike.setText(String.valueOf(singleStatus.getTotalLike()));
                        ((StatusViewHolder) holder).totalDislike.setText(String.valueOf(singleStatus.getTotalDislike()));
                        values.put("interact", "like");
                        values.put("totalLike", singleStatus.getTotalLike());
                        values.put("totalDislike", singleStatus.getTotalDislike());
                    } else if(singleStatus.getInteract().equals("normal")){
                        singleStatus.setInteract("like");
                        ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like_red);
                        singleStatus.setTotalLike(singleStatus.getTotalLike() + 1);
                        ((StatusViewHolder) holder).totalLike.setText(String.valueOf(singleStatus.getTotalLike()));
                        values.put("interact", "like");
                        values.put("totalLike", singleStatus.getTotalLike());
                    }
                    String[] args = {singleStatus.getIdStatus()};
                    ConnDB.getConn().getWritableDatabase().update("Status", values, "idStatus=?", args);
                    statusTransaction.interactStatus("like", singleStatus.getIdStatus());
                }
            });

            ((StatusViewHolder) holder).btnDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContentValues values = new ContentValues();
                    if(singleStatus.getInteract().equals("dislike")) {
                        singleStatus.setInteract("normal");
                        ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike);
                        singleStatus.setTotalDislike(singleStatus.getTotalDislike() - 1);
                        ((StatusViewHolder) holder).totalDislike.setText(String.valueOf(singleStatus.getTotalDislike()));
                        values.put("interact", "normal");
                        values.put("totalDislike", singleStatus.getTotalDislike());
                    } else if(singleStatus.getInteract().equals("like")) {
                        singleStatus.setInteract("dislike");
                        singleStatus.setTotalLike(singleStatus.getTotalLike() - 1);
                        singleStatus.setTotalDislike(singleStatus.getTotalDislike() + 1);
                        ((StatusViewHolder) holder).imgLike.setImageResource(R.drawable.icon_like);
                        ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike_red);
                        ((StatusViewHolder) holder).totalLike.setText(String.valueOf(singleStatus.getTotalLike()));
                        ((StatusViewHolder) holder).totalDislike.setText(String.valueOf(singleStatus.getTotalDislike()));
                        values.put("interact", "dislike");
                        values.put("totalLike", singleStatus.getTotalLike());
                        values.put("totalDislike", singleStatus.getTotalDislike());
                    } else if(singleStatus.getInteract().equals("normal")){
                        singleStatus.setInteract("dislike");
                        ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike_red);
                        singleStatus.setTotalDislike(singleStatus.getTotalDislike() + 1);
                        ((StatusViewHolder) holder).totalDislike.setText(String.valueOf(singleStatus.getTotalDislike()));
                        values.put("interact", "dislike");
                        values.put("totalDislike", singleStatus.getTotalDislike());
                    }
                    String[] args = {singleStatus.getIdStatus()};
                    ConnDB.getConn().getWritableDatabase().update("Status", values, "idStatus=?", args);
                    statusTransaction.interactStatus("dislike", singleStatus.getIdStatus());
                }
            });

            ((StatusViewHolder) holder).btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idStatus", singleStatus.getIdStatus());
                    bundle.putString("database", "Status");
                    CommentFragment commentFragment = new CommentFragment();
                    commentFragment.setArguments(bundle);
                    navigateToFragment(commentFragment, "statusFrameToComment");
                }
            });

            ((StatusViewHolder) holder).contentText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idStatus", singleStatus.getIdStatus());
                    bundle.putString("database", "Status");
                    CommentFragment commentFragment = new CommentFragment();
                    commentFragment.setArguments(bundle);
                    navigateToFragment(commentFragment, "statusFrameToComment");
                }
            });

            if(singleStatus.getSsoIdPoster() == null) {
                System.exit(1);
            }

            if(!singleStatus.getSsoIdPoster().equals("null")) {
                ((StatusViewHolder) holder).nickName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ssoidPoster", singleStatus.getSsoIdPoster());
                        navigateToFragment(new ProfileFragment(), "statusFrameToProfile");
                    }
                });

                ((StatusViewHolder) holder).avatarPoster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Log.d("ssoIdPoster", singleStatus.getSsoIdPoster());
                        bundle.putString("ssoidPoster", singleStatus.getSsoIdPoster());
                        navigateToFragment(new ProfileFragment(), "statusFrameToProfile");
                    }
                });
            } else {
                ((StatusViewHolder) holder).nickName.setClickable(true);
                ((StatusViewHolder) holder).avatarPoster.setClickable(true);
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public static class StatusViewHolder extends RecyclerView.ViewHolder {

        public TextView nickName;
        public TextView timeUp;
        public JTextView contentText;
        public ImageView contentImage;
        public ImageView avatarPoster;
        public TextView totalLike;
        public TextView totalDislike;
        public TextView totalComment;
        public ImageView imgLike;
        public ImageView imgDislike;
        public LinearLayout btnComment;
        public LinearLayout btnLike;
        public LinearLayout btnDislike;

        public Status status;

        public StatusViewHolder(View view) {
            super(view);

            avatarPoster = (ImageView) view.findViewById(R.id.imageAvatarOnStatus);
            nickName = (TextView) view.findViewById(R.id.nickNameAndExtendOnStatus);
            timeUp = (TextView) view.findViewById(R.id.timeUploadStatus);
            contentText = (JTextView) view.findViewById(R.id.contentStatus);
            contentImage = (ImageView) view.findViewById(R.id.imageInStatus);
            totalLike = (TextView) view.findViewById(R.id.totalLikeStatus);
            totalDislike = (TextView) view.findViewById(R.id.totalDislikeStatus);
            totalComment = (TextView) view.findViewById(R.id.totalCommentStatus);

            btnComment = (LinearLayout) view.findViewById(R.id.btnCommentStatus);
            btnLike = (LinearLayout) view.findViewById(R.id.btnLikeStatus);
            btnDislike = (LinearLayout) view.findViewById(R.id.btnDislikeStatus);

            imgLike = (ImageView) btnLike.findViewById(R.id.iconLikeStatus);
            imgLike.setImageResource(R.drawable.icon_like);
            imgDislike = (ImageView) btnDislike.findViewById(R.id.iconDisklikeStatus);
            imgDislike.setImageResource(R.drawable.icon_dislike);
        }
    }
}
