package vn.whoever.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import vn.whoever.R;

/**
 * Created by spider man on 3/7/2016.
 */
public class DialogPrivacyPostStatus extends DialogFragment {

    private RadioGroup groupChoiceUse;
    private RadioGroup groupChoicePrivacy;

    private Button buttonAccept;
    Dialog dialog;

    public DialogPrivacyPostStatus() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_privacy_status, null);

        buttonAccept = (Button) view.findViewById(R.id.btnAcceptChoicePrivacyPostStatus);

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
