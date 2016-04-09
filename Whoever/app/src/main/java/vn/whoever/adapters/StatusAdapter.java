package vn.whoever.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import vn.whoever.R;
import vn.whoever.views.activities.MainActivity;
import vn.whoever.views.customviews.JTextView;
import vn.whoever.models.ArrayStatus;
import vn.whoever.models.Status;
import vn.whoever.utils.TimeUtils;
import vn.whoever.views.fragments.RepliesFragment;

/**
 * Created by spider man on 1/13/2016.
 */
public class StatusAdapter extends BaseAdapter {

    private ArrayList<Status> statusList;
    private Activity activity;

    private int startCount;
    private int count;
    private int stepNumber;

    public StatusAdapter(Activity activity, int startCount, int stepCount) {
        this.activity = activity;
        loadStatusList();

        this.startCount = Math.min(startCount, statusList.size());
        this.count = this.startCount;
        this.stepNumber = stepCount;
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
        return statusList.get(position).getIdStatus();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Status status = (Status) getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_status_layout, null);
        }

//        Log.d("Content: ", status.getContentStatus());
//        Log.d("count Like", String.valueOf(status.getCountLike()));
//        Log.d("count Dislike", String.valueOf(status.getCountDislike()));
//        Log.d("count Comment", String.valueOf(status.getCountComment()));

        TextView nickName = (TextView) convertView.findViewById(R.id.nickNameAndExtendOnStatus);
        nickName.setText("Nguyễn Đô");

        TextView timeUp = (TextView) convertView.findViewById(R.id.timeUploadStatus);
        timeUp.setText(TimeUtils.getInstance().getTimeStatus(new Date()));

        final JTextView content = (JTextView) convertView.findViewById(R.id.contentStatus);
        content.setText(status.getContentStatus(), true);

        LinearLayout btnComment = (LinearLayout) convertView.findViewById(R.id.btnCommentStatus);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
                MainActivity.frgTransaction.replace(R.id.mainFrame, new RepliesFragment()).addToBackStack("repliesFrame").commit();
            }
        });

        /*
        TextView totalLike = (TextView) convertView.findViewById(R.id.totalLikeStatus);
        totalLike.setText(status.getCountLike());


        TextView totalDislike = (TextView) convertView.findViewById(R.id.totalDislikeStatus);
        totalDislike.setText(status.getCountDislike());

        TextView totalComment = (TextView) convertView.findViewById(R.id.totalCommentStatus);
        totalComment.setText(status.getCountComment());

        */
        return convertView;
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

    public void loadStatusList() {
        /**
         * TODO: load database from sqlite show to
         */
        ArrayStatus arrayStatus = new ArrayStatus();
        statusList = arrayStatus.getArrStatus();
    }
}
