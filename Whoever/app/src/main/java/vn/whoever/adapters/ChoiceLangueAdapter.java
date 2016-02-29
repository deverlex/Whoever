package vn.whoever.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.whoever.R;
import vn.whoever.models.ArrayLanguage;

/**
 * Created by spider man on 2/29/2016.
 */
public class ChoiceLangueAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<String> data;

    public ChoiceLangueAdapter(Activity activity) {
        this.activity = activity;
        loadDataListLanguageChoice();
       // layoutInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    public ChoiceLangueAdapter(Activity activity, ArrayList<String> data) {
        this.activity = activity;
        this.data = data;
        //layoutInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language_layout, null);
        }

        ((TextView) convertView.findViewById(R.id.itemTextLanguageChoiceWelcome)).setText(data.get(position));

        return convertView;
    }

    public void loadDataListLanguageChoice() {
        ArrayLanguage arrayLanguage = new ArrayLanguage();
        data = arrayLanguage.getListLanguage();
    }
}
