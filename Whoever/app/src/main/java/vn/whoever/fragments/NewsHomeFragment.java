package vn.whoever.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.adapters.StatusAdapter;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/28/2015.
 */
public class NewsHomeFragment extends Fragment implements Initgc {

    private ListView listStatus;
    private StatusAdapter statusAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newshome_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        listStatus = (ListView) view.findViewById(R.id.listViewNewsHome);
        statusAdapter = new StatusAdapter(getActivity());
        listStatus.setAdapter(statusAdapter);
    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}
