package vn.whoever.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.ArrayReply;
import vn.whoever.models.Comment;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.customviews.RoundedImageView;
import vn.whoever.views.fragments.ProfileFragment;

/**
 * Created by spider man on 1/13/2016.
 */
public class CommentAdapter extends BaseAdapter {

    private ArrayList<Comment> commentList;
    private Fragment fragment;

    private int count;
    private int stepNumber;
    private int startCount;

    public CommentAdapter(Fragment fragment, int startCount, int stepNumber) {
        this.fragment = fragment;

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
        return commentList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Comment comment = (Comment) getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_comment_layout, null);
        }

        TextView nickName = (TextView) convertView.findViewById(R.id.nickNameItemReplyOnListComment);
        nickName.setText(comment.getNamePoster());
        JTextView contentText = (JTextView) convertView.findViewById(R.id.contentCommentDetailOnListComment);
        contentText.setText(comment.getContent());
        RoundedImageView avatarPostCmt = (RoundedImageView) convertView.findViewById(R.id.avatarItemOnCommentList);
        ///TODO: set later

        TextView timePost = (TextView) convertView.findViewById(R.id.timePostCommentOnCommentList);
        timePost.setText(comment.getTimePost());

        ImageView iconLike = (ImageView) convertView.findViewById(R.id.iconLikeOnItemComment);
        iconLike.setImageResource(R.drawable.icon_like);
        ImageView iconDislike = (ImageView) convertView.findViewById(R.id.iconDislikeOnItemComment);
        iconDislike.setImageResource(R.drawable.icon_dislike);

        if(comment.getInteract().equals("like")) {
            iconLike.setImageResource(R.drawable.icon_like_red);
        } else if(comment.getInteract().equals("dislike")) {
            iconDislike.setImageResource(R.drawable.icon_dislike_red);
        }

        TextView totalLike = (TextView) convertView.findViewById(R.id.textCountLikeOnItemComment);
        totalLike.setText(String.valueOf(comment.getTotalLike()));
        TextView totalDislike = (TextView) convertView.findViewById(R.id.textCountDislikeOnItemComment);
        totalDislike.setText(String.valueOf(comment.getTotalDislike()));

        LinearLayout btnLike = (LinearLayout) convertView.findViewById(R.id.btnLikeOnItemComment);
        LinearLayout btnDislike = (LinearLayout) convertView.findViewById(R.id.btnDislikeOnItemComment);

        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("ssoId", "");
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                navigateToProfile(profileFragment, "commentFrameToProfile");
            }
        });

        avatarPostCmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("ssoId", "");
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle);
                navigateToProfile(profileFragment, "commentFrameToProfile");
            }
        });

        return convertView;
    }

    public void navigateToProfile(Fragment frag, String strStack) {
        fragment.getParentFragment().getChildFragmentManager().beginTransaction().
                replace(R.id.majorFrame, frag).addToBackStack(strStack).commit();
        fragment.getParentFragment().getChildFragmentManager().executePendingTransactions();
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
        commentList = new ArrayList<>();
    }

    public int getSize() {
        return commentList.size();
    }
}
