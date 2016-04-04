package vn.whoever.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.whoever.R;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 12/25/2015.
 * TODO: update term from server to sqlite database
 */
public class TermFragment extends Fragment implements Initgc {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.term_policies_layout, null);

        return view;
    }

    @Override
    public void init(View view) {

    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void initGc() {

    }
}
