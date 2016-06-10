package vn.whoever.views.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.models.dao.ConnDB;

/**
 * Created by Nguyen Van Do on 3/7/2016.
 * This class implement dialog layout for status's privacy
 */
public class DialogPrivacyPostStatus extends DialogFragment {

    private RadioGroup groupChoiceUse;
    private RadioGroup groupChoicePrivacy;
    private RadioButton btnAccount;
    private RadioButton btnAnonymous;
    private RadioButton btnPrimary;
    private RadioButton btnFriends;
    private RadioButton btnPublic;
    private Button buttonAccept;
    private Dialog dialog;
    private RelativeLayout toobarPrivacy;

    public DialogPrivacyPostStatus() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_privacy_status, null);
        // Set layout's element
        init(view);
        // Set listener layout's event
        initListener(view);
        return view;
    }

    public void init(View view) {
        buttonAccept = (Button) view.findViewById(R.id.btnAcceptChoicePrivacyPostStatus);

        groupChoiceUse = (RadioGroup) view.findViewById(R.id.groupButtonChoiceUse);
        groupChoicePrivacy = (RadioGroup) view.findViewById(R.id.groupButtonChoicePrivacyPostStatus);

        btnAccount = (RadioButton) view.findViewById(R.id.radioButtonChoiceUseAccount);
        btnAnonymous = (RadioButton) view.findViewById(R.id.radioButtonChoiceUseAnonymous);
        btnPrimary = (RadioButton) view.findViewById(R.id.radioButtonChoicePrimary);
        btnFriends = (RadioButton) view.findViewById(R.id.radioButtonChoiceFriends);
        btnPublic = (RadioButton) view.findViewById(R.id.radioButtonChoicePublic);
        /**
         * TODO: default selected privacy for post status
         */
        SQLiteDatabase db = ConnDB.getConn().getReadableDatabase();
        Cursor cursor = db.rawQuery("select use, privacy from SetPostStatus where id=1", null);
        cursor.moveToFirst();
        if (cursor.getString(0).equals("anonymous")) {
            btnAnonymous.setChecked(true);
        } else {
            btnAccount.setChecked(true);
        }
        if (cursor.getString(1).equals("public")) {
            btnPublic.setChecked(true);
        } else if (cursor.getString(1).equals("friends")) {
            btnFriends.setChecked(true);
        } else {
            btnPrimary.setChecked(true);
        }
        cursor.close();
    }

    public void initListener(View view) {
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Choice for anonymous or use user name mode
        groupChoiceUse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == btnAnonymous.getId()) {
                    btnAnonymous.setChecked(true);
                    btnAccount.setChecked(false);
                } else {
                    btnAnonymous.setChecked(false);
                    btnAccount.setChecked(true);
                }
                updateDbSet();
            }
        });

        // Choice privacy for post status
        groupChoicePrivacy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == btnPublic.getId()) {
                    btnPublic.setChecked(true);
                    btnFriends.setChecked(false);
                    btnPrimary.setChecked(false);
                } else if (checkedId == btnFriends.getId()) {
                    btnPublic.setChecked(false);
                    btnFriends.setChecked(true);
                    btnPrimary.setChecked(false);
                } else {
                    btnPublic.setChecked(false);
                    btnFriends.setChecked(false);
                    btnPrimary.setChecked(true);
                }
                updateDbSet();
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    // Update UI after choice
    public void updateDbSet() {
        String use = "anonymous";
        String privacy = "public";

        ImageView imagePrivacy = (ImageView) toobarPrivacy.findViewById(R.id.symbolPrivacyPostStatus);
        imagePrivacy.setImageResource(R.drawable.icon_notify_red);
        ImageView imageUseAccount = (ImageView) toobarPrivacy.findViewById(R.id.symbolUseAccountPostStatus);
        imageUseAccount.setImageResource(R.drawable.icon_anonymous);
        TextView textUseAccount = (TextView) toobarPrivacy.findViewById(R.id.textShowUseAccountPostStatus);
        textUseAccount.setText("Anonymous");
        TextView textPrivacy = (TextView) toobarPrivacy.findViewById(R.id.textShowPrivacyPostStatus);
        textPrivacy.setText("Public");

        if (!btnAnonymous.isChecked()) {
            use = "account";
            imageUseAccount.setImageResource(R.drawable.icon_account);
            textUseAccount.setText("Account");
        }
        if (btnFriends.isChecked()) {
            privacy = "friends";
            imagePrivacy.setImageResource(R.drawable.icon_contacts_red);
            textPrivacy.setText("Friends");
        } else if (btnPrimary.isChecked()) {
            privacy = "primary";
            imagePrivacy.setImageResource(R.drawable.icon_lock_red);
            textPrivacy.setText("Primary");
        }
        // Update choice mode into database
        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("use", use);
        values.put("privacy", privacy);
        db.update("SetPostStatus", values, "id=1", null);
        db.close();
    }
    public void setLayout(RelativeLayout layout) {
        this.toobarPrivacy = layout;
    }
}
