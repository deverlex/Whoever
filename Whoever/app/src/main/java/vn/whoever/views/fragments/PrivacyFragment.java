package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.whoever.R;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 4/9/2016.
 */
public class PrivacyFragment extends Fragment implements Initgc {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.privacy_user_layout, container, false);

        return view;
    }

    @Override
    public void init(View view) {

    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void initGc() {

    }
}