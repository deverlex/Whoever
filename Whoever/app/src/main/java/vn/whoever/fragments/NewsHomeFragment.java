package vn.whoever.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.whoever.R;

/**
 * Created by spider man on 12/28/2015.
 */
public class NewsHomeFragment extends Fragment {

    private ListView listViewHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newshome_layout, null);

        return view;
    }

    public void init(View view) {
        //listViewHome = (ListView) view.findViewById(R.id.listViewNewsHome);
    }
}
