package vn.whoever.views.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.models.dao.ConnDB;

/**
 * Created by Nguyen Van Do on 5/7/2016.
 * This class implement dialog layout for view profile
 */
public class DialogViewProfile extends DialogFragment {

    private Dialog dialog;
    private TextView textLang;
    private TextView textBirthday;
    private TextView textGender;
    private TextView textMobile;
    private TextView textEmail;
    private Button buttonAccept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_profile_layout, container, false);

        init(view);
        initData(view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void init(View view) {
        textLang = (TextView) view.findViewById(R.id.textViewLanguageProfile);
        textGender = (TextView) view.findViewById(R.id.textViewGenderProfile);
        textBirthday = (TextView) view.findViewById(R.id.textViewBirthDayProfile);
        textMobile = (TextView) view.findViewById(R.id.textViewPhoneProfile);
        textEmail = (TextView) view.findViewById(R.id.textViewEmailProfile);
        buttonAccept = (Button) view.findViewById(R.id.btnAcceptCloseProfile);
    }

    // Get info from database and set into layout
    public void initData(View view) {
        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        Cursor cursor = db.rawQuery("select langName, birthday, gender, mobile, email from LocalProfile", null);
        cursor.moveToFirst();
        textLang.setText(""+cursor.getString(0));
        textGender.setText(""+cursor.getString(2));
        textBirthday.setText(""+cursor.getString(1));
        textEmail.setText("" + cursor.getString(4));
        textMobile.setText(""+cursor.getString(3));
        cursor.close();
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
