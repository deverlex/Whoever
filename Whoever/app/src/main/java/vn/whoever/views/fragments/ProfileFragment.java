package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import vn.whoever.R;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 4/9/2016.
 */
public class ProfileFragment extends Fragment implements Initgc {

    private ImageButton btnBackHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        btnBackHome = (ImageButton) view.findViewById(R.id.btnBackHomeFromProfile);
    }

    @Override
    public void initListener(View view) {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void initGc() {

    }
}
