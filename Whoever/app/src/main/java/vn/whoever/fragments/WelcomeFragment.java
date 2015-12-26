package vn.whoever.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.whoever.R;

/**
 * Created by spider man on 12/26/2015.
 */
public class WelcomeFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedStanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, null);

        return view;
    }
}
