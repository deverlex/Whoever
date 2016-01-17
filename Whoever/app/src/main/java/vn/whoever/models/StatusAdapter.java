package vn.whoever.models;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import vn.whoever.R;
import vn.whoever.utils.ConvertTime;

/**
 * Created by spider man on 1/13/2016.
 */
public class StatusAdapter extends BaseAdapter {

    private ArrayList<Status> statusList;
    private Context context;

    public StatusAdapter(Context context) {
        this.context = context;
        //statusList = new ArrayList<Status>();
        loadStatusList();
    }

    @Override
    public int getCount() {
        return statusList.size();
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

        Log.d("Content: ", status.getContentStatus());
        Log.d("count Like", String.valueOf(status.getCountLike()));
        Log.d("count Dislike", String.valueOf(status.getCountDislike()));
        Log.d("count Comment", String.valueOf(status.getCountComment()));

        TextView nickName = (TextView) convertView.findViewById(R.id.nickNameAndExtendOnStatus);
        nickName.setText(status.getSenderStatus().getNickName());


        TextView timeUp = (TextView) convertView.findViewById(R.id.timeUploadStatus);
        timeUp.setText(ConvertTime.getInstance().getTimeStatus(new Date()));

        TextView content = (TextView) convertView.findViewById(R.id.contentStatus);
        content.setText(status.getContentStatus());

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

    public void loadStatusList() {
        /**
         * TODO: load database from sqlite show to
         */
        ArrayStatus  arrayStatus = new ArrayStatus();
        statusList = arrayStatus.getArrStatus();
    }
}
