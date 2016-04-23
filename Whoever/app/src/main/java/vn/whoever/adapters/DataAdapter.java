package vn.whoever.adapters;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import vn.whoever.R;
import vn.whoever.models.Status;
import vn.whoever.views.customviews.JTextView;

/**
 * Created by spider man on 4/23/2016.
 */
public class DataAdapter extends RecyclerView.Adapter {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private List<Status> listStatus;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    private Fragment fragment;

    public DataAdapter(Fragment fragment,List<Status> listStatus, RecyclerView recyclerView) {
        this.listStatus = listStatus;
        this.fragment = fragment;

        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if(!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if(onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listStatus.get(position) != null ? VIEW_ITEM : VIEW_PROG;
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof StatusViewHolder) {
            Status singleStatus = (Status) listStatus.get(position);
            ((StatusViewHolder) holder).nickName.setText(singleStatus.getNamePoster());
            ((StatusViewHolder) holder).timeUp.setText(singleStatus.getTimePost());
            ((StatusViewHolder) holder).totalLike.setText(String.valueOf(singleStatus.getTotalLike()));
            ((StatusViewHolder) holder).totalDislike.setText(String.valueOf(singleStatus.getTotalDislike()));
            ((StatusViewHolder) holder).totalComment.setText(String.valueOf(singleStatus.getTotalComment()));
            String strContent;
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
            } else if(singleStatus.getInteract().equals("dislike")) {
                ((StatusViewHolder) holder).imgDislike.setImageResource(R.drawable.icon_dislike_red);
            }



        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return listStatus.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
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

        LinearLayout btnComment;
        LinearLayout btnLike;
        LinearLayout btnDislike;

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

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBarLoad);
            progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#FF4801"), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }
}
