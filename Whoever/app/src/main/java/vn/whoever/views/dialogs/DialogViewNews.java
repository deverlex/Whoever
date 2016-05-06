package vn.whoever.views.dialogs;

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

/**
 * Created by spider man on 4/23/2016.
 */
public class DialogViewNews extends DialogFragment {

    private RadioGroup choiceViewGr;
    private RadioButton btnNearby;
    private RadioButton btnFriends;

    private Button btnAccept;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_news_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void init(View view) {
        btnAccept = (Button) view.findViewById(R.id.btnAcceptChoiceViewNews);
    }

    public void initListener(View view) {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
