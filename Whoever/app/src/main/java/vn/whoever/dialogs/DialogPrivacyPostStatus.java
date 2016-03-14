package vn.whoever.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import vn.whoever.R;
import vn.whoever.utils.PrivacyStatus;

/**
 * Created by spider man on 3/7/2016.
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

    public DialogPrivacyPostStatus() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_privacy_status, null);

        init(view);
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
        btnAccount.setSelected(true);
        btnPublic.setChecked(true);
    }

    public void initListener(View view) {
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: somethings
                 */

                dialog.dismiss();
            }
        });

        groupChoiceUse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == btnAnonymous.getId()) {

                } else {

                }
            }
        });

        groupChoicePrivacy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == btnPublic.getId()) {

                } else if(checkedId == btnFriends.getId()) {

                } else {

                }
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public int getSelectedUse() {
        if(btnAnonymous.isChecked()) {
            return btnAnonymous.getId();
        } else {
            return btnAccount.getId();
        }
    }

    public int getSelectedPrivacy() {
        if(btnPublic.isChecked()) {
            return btnPublic.getId();
        } else if(btnFriends.isChecked()) {
            return btnFriends.getId();
        } else {
            return btnPrimary.getId();
        }
    }
}
