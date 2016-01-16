package vn.whoever.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.whoever.R;

/**
 * Created by spider man on 12/28/2015.
 */
public class NewsFeedFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsfeed_layout, null);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }
}
