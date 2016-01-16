package vn.whoever.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import vn.whoever.R;

/**
 * Created by spider man on 12/29/2015.
 */
public class ContactsFragment extends Fragment {

    private RelativeLayout layoutToolBar;
    private FloatingActionButton buttonAddAccount;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_layout, null);


        return view;
    }
}
