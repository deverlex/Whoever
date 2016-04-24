package vn.whoever.adapters;

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
import vn.whoever.models.Comment;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.views.customviews.RoundedImageView;
import vn.whoever.views.fragments.ProfileFragment;

/**
 * Created by spider man on 4/24/2016.
 */
public class CommentAdapter extends AbstractAdapter<Comment> {

    public CommentAdapter(Fragment fragment, List<Comment> commentList, RecyclerView recyclerView) {
        super(fragment, commentList, recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if(viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_layout, parent, false);
            vh = new CommentViewHolder (view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_footer, parent, false);
            vh = new ProgressViewHolder (view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  CommentViewHolder) {
            final Comment singleComment = dataList.get(position);
            ((CommentViewHolder) holder).nickName.setText(singleComment.getNamePoster());
            ((CommentViewHolder) holder).contentText.setText(singleComment.getContent());
            ((CommentViewHolder) holder).timePost.setText(singleComment.getTimePost());
            ((CommentViewHolder) holder).totalLike.setText(String.valueOf(singleComment.getTotalLike()));
            ((CommentViewHolder) holder).totalDislike.setText(String.valueOf(singleComment.getTotalDislike()));

            ((CommentViewHolder) holder).comment = singleComment;

            if(singleComment.getInteract().equals("like")) {
                ((CommentViewHolder) holder).iconLike.setImageResource(R.drawable.icon_like_red);
            } else if(singleComment.getInteract().equals("dislike")) {
                ((CommentViewHolder) holder).iconDislike.setImageResource(R.drawable.icon_dislike_red);
            } else {
                ((CommentViewHolder) holder).iconLike.setImageResource(R.drawable.icon_like);
                ((CommentViewHolder) holder).iconDislike.setImageResource(R.drawable.icon_dislike);
            }

            ((CommentViewHolder) holder).btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(singleComment.getInteract().equals("like")) {
                        singleComment.setInteract("normal");
                        ((CommentViewHolder) holder).iconLike.setImageResource(R.drawable.icon_like);
                        singleComment.setTotalLike(singleComment.getTotalLike() - 1);
                        ((CommentViewHolder) holder).totalLike.setText(String.valueOf(singleComment.getTotalLike()));
                    } else if(singleComment.getInteract().equals("dislike")) {
                        singleComment.setInteract("like");
                        ((CommentViewHolder) holder).iconDislike.setImageResource(R.drawable.icon_dislike);
                        ((CommentViewHolder) holder).iconLike.setImageResource(R.drawable.icon_like_red);
                        singleComment.setTotalLike(singleComment.getTotalLike() + 1);
                        singleComment.setTotalDislike(singleComment.getTotalDislike() - 1);
                        ((CommentViewHolder) holder).totalLike.setText(String.valueOf(singleComment.getTotalLike()));
                        ((CommentViewHolder) holder).totalDislike.setText(String.valueOf(singleComment.getTotalDislike()));
                    } else if(singleComment.getInteract().equals("normal")){
                        singleComment.setInteract("like");
                        ((CommentViewHolder) holder).iconLike.setImageResource(R.drawable.icon_like_red);
                        singleComment.setTotalLike(singleComment.getTotalLike() + 1);
                        ((CommentViewHolder) holder).totalLike.setText(String.valueOf(singleComment.getTotalLike()));
                    }
                }
            });

            ((CommentViewHolder) holder).btnDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(singleComment.getInteract().equals("dislike")) {
                        singleComment.setInteract("normal");
                        ((CommentViewHolder) holder).iconDislike.setImageResource(R.drawable.icon_dislike);
                        singleComment.setTotalDislike(singleComment.getTotalDislike() - 1);
                        ((CommentViewHolder) holder).totalDislike.setText(String.valueOf(singleComment.getTotalDislike()));
                    } else if(singleComment.getInteract().equals("like")) {
                        singleComment.setInteract("dislike");
                        singleComment.setTotalLike(singleComment.getTotalLike() - 1);
                        singleComment.setTotalDislike(singleComment.getTotalDislike() + 1);
                        ((CommentViewHolder) holder).iconLike.setImageResource(R.drawable.icon_like);
                        ((CommentViewHolder) holder).iconDislike.setImageResource(R.drawable.icon_dislike_red);
                        ((CommentViewHolder) holder).totalLike.setText(String.valueOf(singleComment.getTotalLike()));
                        ((CommentViewHolder) holder).totalDislike.setText(String.valueOf(singleComment.getTotalDislike()));
                    } else if(singleComment.getInteract().equals("normal")){
                        singleComment.setInteract("dislike");
                        ((CommentViewHolder) holder).iconDislike.setImageResource(R.drawable.icon_dislike_red);
                        singleComment.setTotalDislike(singleComment.getTotalDislike() + 1);
                        ((CommentViewHolder) holder).totalDislike.setText(String.valueOf(singleComment.getTotalDislike()));
                    }
                    Log.d("clickEvent", "click on dislike button!!!");
                    Log.d("interact", singleComment.getInteract());
                }
            });

            if(!singleComment.getNamePoster().equals("anonymous")) {
                ((CommentViewHolder) holder).nickName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ssoidPoster", singleComment.getSsoIdPoster());
                        navigateToFragment(new ProfileFragment(), "statusFrameToProfile");
                    }
                });

                ((CommentViewHolder) holder).avatarPostCmt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Log.d("ssoIdPoster", singleComment.getSsoIdPoster());
                        bundle.putString("ssoidPoster", singleComment.getSsoIdPoster());
                        navigateToFragment(new ProfileFragment(), "statusFrameToProfile");
                    }
                });
            }
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView nickName;
        public JTextView contentText;
        public ImageView avatarPostCmt;
        public TextView timePost;
        public ImageView iconLike;
        public ImageView iconDislike;
        public TextView totalLike;
        public TextView totalDislike;
        public LinearLayout btnLike;
        public LinearLayout btnDislike;

        public Comment comment;

        public CommentViewHolder(View view) {
            super(view);
            nickName = (TextView) view.findViewById(R.id.nickNameItemReplyOnListComment);
            contentText = (JTextView) view.findViewById(R.id.contentCommentDetailOnListComment);
            avatarPostCmt = (RoundedImageView) view.findViewById(R.id.avatarItemOnCommentList);
            timePost = (TextView) view.findViewById(R.id.timePostCommentOnCommentList);
            totalLike = (TextView) view.findViewById(R.id.textCountLikeOnItemComment);
            totalDislike = (TextView) view.findViewById(R.id.textCountDislikeOnItemComment);
            btnLike = (LinearLayout) view.findViewById(R.id.btnLikeOnItemComment);
            btnDislike = (LinearLayout) view.findViewById(R.id.btnDislikeOnItemComment);
            iconLike = (ImageView) btnLike.findViewById(R.id.iconLikeOnItemComment);
            iconLike.setImageResource(R.drawable.icon_like);
            iconDislike = (ImageView) btnDislike.findViewById(R.id.iconDislikeOnItemComment);
            iconDislike.setImageResource(R.drawable.icon_dislike);
        }

    }

}
