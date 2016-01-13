package vn.whoever.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

import vn.whoever.R;

/**
 * Created by spider man on 1/13/2016.
 */
public class StatusAdapter extends BaseAdapter {

    private ArrayList<Status> statusList;
    private Context context;

    public StatusAdapter(Context context) {
        this.context = context;
        statusList = new ArrayList<Status>();
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



        return convertView;
    }

    public void loadStatusList() {
        /**
         * TODO: load database from sqlite show to
         */
    }
}
