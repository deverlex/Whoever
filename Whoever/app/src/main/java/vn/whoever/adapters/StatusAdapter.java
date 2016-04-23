package vn.whoever.adapters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.models.Status;
import vn.whoever.views.customviews.RoundedImageView;
import vn.whoever.views.fragments.ProfileFragment;
import vn.whoever.views.fragments.RepliesFragment;

/**
 * Created by spider man on 1/13/2016.
 */
public class StatusAdapter extends BaseAdapter {

    private ArrayList<Status> statusList;
    private Fragment fragment;

    private int startCount;
    private int count;
    private int stepNumber;
    private String dbLoad;

    public StatusAdapter(Fragment fragment,int startCount, int stepCount, String dbLoad) {
        this.fragment = fragment;
        loadStatusList(dbLoad, 0);
        this.dbLoad = dbLoad;
        this.startCount = Math.min(startCount, statusList.size());
        this.count = this.startCount;
        this.stepNumber = stepCount;
    }

    public void reload() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public Object getItem(int position) {
        return statusList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return statusList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Status status = (Status) getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_status_layout, null);
        }

        /**
         * TODO: avatar, nickName, timePost, contentText - contentImage, totalLike, totalDislike
         */
        String avtartPoster = "";

        TextView nickName = (TextView) convertView.findViewById(R.id.nickNameAndExtendOnStatus);
        nickName.setText(status.getNamePoster());
        TextView timeUp = (TextView) convertView.findViewById(R.id.timeUploadStatus);
        timeUp.setText(status.getTimePost());

        final JTextView contentText = (JTextView) convertView.findViewById(R.id.contentStatus);
        String strContent = "";

        //TODO: add image to this
        ImageView contentImage = (ImageView) convertView.findViewById(R.id.imageInStatus);

        if(status.getContentImage().equals("null")) {
            // TODO: add image for status
            if(status.getContentText().length() > 682)
                strContent = status.getContentText().substring(0, 682) + "...";
            else strContent = status.getContentText();

        } else {
            if(status.getContentText().length() > 268)
                strContent = status.getContentText().substring(0,268) + "...";
            else strContent = status.getContentText();
        }
        contentText.setText(strContent, true);
        final TextView totalLike = (TextView) convertView.findViewById(R.id.totalLikeStatus);
        totalLike.setText(String.valueOf(status.getTotalLike()));
        final TextView totalDislike = (TextView) convertView.findViewById(R.id.totalDislikeStatus);
        totalDislike.setText(String.valueOf(status.getTotalDislike()));
        TextView totalComment = (TextView) convertView.findViewById(R.id.totalCommentStatus);
        totalComment.setText(String.valueOf(status.getTotalComment()) + " comment");

        /**
         * TODO: btnComment, btnLike, btnDislike
         */
        LinearLayout btnComment = (LinearLayout) convertView.findViewById(R.id.btnCommentStatus);
        LinearLayout btnLike = (LinearLayout) convertView.findViewById(R.id.btnLikeStatus);
        LinearLayout btnDislike = (LinearLayout) convertView.findViewById(R.id.btnDislikeStatus);

        final ImageView imgLike = (ImageView) btnLike.findViewById(R.id.iconLikeStatus);
        imgLike.setImageResource(R.drawable.icon_like);
        final ImageView imgDislike = (ImageView) btnDislike.findViewById(R.id.iconDisklikeStatus);
        imgDislike.setImageResource(R.drawable.icon_dislike);

        if(status.getInteract().equals("like")) {
            imgLike.setImageResource(R.drawable.icon_like_red);
        } else if(status.getInteract().equals("dislike")) {
            imgDislike.setImageResource(R.drawable.icon_dislike_red);
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: up to server a action: like, don't care state of it
                 */
                /**
                 * up server
                 */

                if(status.getInteract().equals("like")) {
                    status.setInteract("normal");
                    imgLike.setImageResource(R.drawable.icon_like);
                    status.setTotalLike(status.getTotalLike() - 1);
                    totalLike.setText(String.valueOf(status.getTotalLike()));
                } else if(status.getInteract().equals("dislike")) {
                    status.setInteract("like");
                    imgDislike.setImageResource(R.drawable.icon_dislike);
                    imgLike.setImageResource(R.drawable.icon_like_red);
                    status.setTotalLike(status.getTotalLike() + 1);
                    status.setTotalDislike(status.getTotalDislike() - 1);
                    totalLike.setText(String.valueOf(status.getTotalLike()));
                    totalDislike.setText(String.valueOf(status.getTotalDislike()));
                } else {
                    status.setInteract("like");
                    imgLike.setImageResource(R.drawable.icon_like_red);
                    status.setTotalLike(status.getTotalLike() + 1);
                    totalLike.setText(String.valueOf(status.getTotalLike()));
                }
            }
        });

        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: up to server a action: dislike, don't care state of it
                 */
                /**
                 * up server
                 */

                if(status.getInteract().equals("dislike")) {
                    status.setInteract("normal");
                    imgDislike.setImageResource(R.drawable.icon_dislike);
                    status.setTotalDislike(status.getTotalDislike() - 1);
                    totalDislike.setText(String.valueOf(status.getTotalDislike()));
                } else if(status.getInteract().equals("like")) {
                    status.setInteract("dislike");
                    status.setTotalLike(status.getTotalLike() - 1);
                    status.setTotalDislike(status.getTotalDislike() + 1);
                    imgLike.setImageResource(R.drawable.icon_like);
                    imgDislike.setImageResource(R.drawable.icon_dislike_red);
                    totalLike.setText(String.valueOf(status.getTotalLike()));
                    totalDislike.setText(String.valueOf(status.getTotalDislike()));
                } else {
                    status.setInteract("dislike");
                    imgDislike.setImageResource(R.drawable.icon_dislike_red);
                    status.setTotalDislike(status.getTotalDislike() + 1);
                    totalDislike.setText(String.valueOf(status.getTotalDislike()));
                }
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("idStatus", status.getIdStatus());
                bundle.putString("database", dbLoad);
                RepliesFragment repliesFragment = new RepliesFragment();
                repliesFragment.setArguments(bundle);
                navigateToFragment(repliesFragment, "statusFrameToComment");
            }
        });

        contentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("idStatus", status.getIdStatus());
                bundle.putString("database", dbLoad);
                RepliesFragment repliesFragment = new RepliesFragment();
                repliesFragment.setArguments(bundle);
                navigateToFragment(repliesFragment, "statusFrameToComment");
            }
        });

        contentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * TODO: Avatar & nickName
         */
        RoundedImageView avatar = (RoundedImageView) convertView.findViewById(R.id.imageAvatarOnStatus);
        //cho nay can thay the nguon anh

        if(!status.getNamePoster().equals("anonymous")) {
            nickName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("ssoidPoster", status.getSsoIdPoster());
                    navigateToFragment(new ProfileFragment(), "statusFrameToProfile");
                }
            });

            avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Log.d("ssoIdPoster", status.getSsoIdPoster());
                    bundle.putString("ssoidPoster", status.getSsoIdPoster());
                    navigateToFragment(new ProfileFragment(), "statusFrameToProfile");
                }
            });
        }
        return convertView;
    }

    public void navigateToFragment(Fragment frag, String strStack) {
        fragment.getParentFragment().getChildFragmentManager().beginTransaction().replace(R.id.majorFrame, frag)
                .addToBackStack(strStack).commit();
        fragment.getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    public boolean showMore() {
        if(this.count == statusList.size()) {
            return true;
        } else {
            count = Math.min(count + stepNumber, statusList.size());
            notifyDataSetChanged();
            return endReached();
        }
    }

    public boolean endReached() {
        return this.count == statusList.size();
    }

    public int getSize() {
        return statusList.size();
    }

    public void reset() {
        count = startCount;
        notifyDataSetChanged();
    }

    public void loadStatusList(String dbLoad, int posBegin) {
        /**
         * TODO: load database from sqlite show to
         */
        statusList = new ArrayList<>();
        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        String arg[] = {String.valueOf(posBegin)};
        Cursor cursor = db.rawQuery("select id, idStatus, ssoIdPoster, avatarPoster, namePoster," +
                " timePost, contentText, contentImage, totalLike, totalDislike, totalComment," +
                " interact from "+ dbLoad +" where id >=?", arg);
        while (cursor.moveToNext()) {
            Status status = new Status();
            status.setId(cursor.getInt(0));
            status.setIdStatus(cursor.getString(1));
            status.setSsoIdPoster(cursor.getString(2));
            status.setAvatarPoster(cursor.getString(3));
            status.setNamePoster(cursor.getString(4));
            status.setTimePost(cursor.getString(5));
            status.setContentText(cursor.getString(6));
            status.setContentImage(cursor.getString(7));
            status.setTotalLike(cursor.getInt(8));
            status.setTotalDislike(cursor.getInt(9));
            status.setTotalComment(cursor.getInt(10));
            status.setInteract(cursor.getString(11));
            statusList.add(status);
        }
        cursor.close();
        db.close();
    }
}
