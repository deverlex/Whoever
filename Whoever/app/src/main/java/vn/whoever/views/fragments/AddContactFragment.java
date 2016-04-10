package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.utils.Initgc;
import vn.whoever.views.activities.MainActivity;

/**
 * Created by spider man on 4/8/2016.
 */
public class AddContactFragment extends Fragment implements Initgc {

    private ImageButton btnBackContactList;
    private ImageButton btnSearchFriends;
    private ImageButton btnOpenChoicePostalCode;
    private TextView textViewPostalCode;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contact_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        btnBackContactList = (ImageButton) view.findViewById(R.id.btnBackContactList);
        btnSearchFriends = (ImageButton) view.findViewById(R.id.btnSearchFiends);
        btnOpenChoicePostalCode = (ImageButton) view.findViewById(R.id.btnOpenChoicePostalCode);
        textViewPostalCode = (TextView) view.findViewById(R.id.textPostalCodeByCountries);
    }

    @Override
    public void initListener(View view) {
        btnBackContactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnSearchFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnOpenChoicePostalCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textViewPostalCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initGc() {

    }
}
