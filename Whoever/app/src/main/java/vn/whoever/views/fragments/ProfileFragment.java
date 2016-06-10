package vn.whoever.views.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.utils.Initgc;
import vn.whoever.views.dialogs.DialogViewProfile;

/**
 * Created by Nguyen Van Do on 4/9/2016.
 * This class implement profile layout.
 */
public class ProfileFragment extends Fragment implements Initgc {

    private ImageButton btnBackHome;
    private TextView textNickname;
    private ImageButton btnViewProfile;
    private DialogViewProfile dialogViewProfile;

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
        textNickname = (TextView) view.findViewById(R.id.textNickNameProfile);
        btnViewProfile = (ImageButton) view.findViewById(R.id.btnViewInfoProfile);

        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        Cursor cursor = db.rawQuery("select nickName from LocalProfile", null);
        cursor.moveToFirst();
        textNickname.setText(""+cursor.getString(0));
        dialogViewProfile = new DialogViewProfile();
    }

    @Override
    public void initListener(View view) {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogViewProfile.show(getActivity().getFragmentManager(), "Choice View News");
            }
        });
    }

    @Override
    public void initGc() {}
}
