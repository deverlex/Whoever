package vn.whoever.views.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import vn.whoever.R;
import vn.whoever.views.activities.MainActivity;
import vn.whoever.views.fragments.NewsFeedFragment;

/**
 * Created by spider man on 4/23/2016.
 */
public class DialogViewNews extends DialogFragment {

    private RadioGroup choiceViewGr;
    private RadioButton btnNearby;
    private RadioButton btnFriends;

    private Button btnAccept;
    private Dialog dialog;

    private NewsFeedFragment fragment;
    private String setView = "nearby";

    public DialogViewNews(NewsFeedFragment fragment) {
        setView = MainActivity.sharedPref.getString("viewNews", "nearby");
        this.fragment = fragment;
    }

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
        btnFriends = (RadioButton) view.findViewById(R.id.radioBtnChoiceViewFriends);
        btnNearby = (RadioButton) view.findViewById(R.id.radioBtnChoiceViewNearby);

        if(setView.equals("nearby")) {
            btnNearby.setChecked(true);
            btnFriends.setChecked(false);
        } else {
            btnNearby.setChecked(false);
            btnFriends.setChecked(true);
        }

        choiceViewGr = (RadioGroup) view.findViewById(R.id.groupButtonChoiceViewNews);
    }

    public void initListener(View view) {
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.fetchStatus();
                dialog.dismiss();
                SharedPreferences.Editor editor = MainActivity.sharedPref.edit();
                editor.putString("viewNews", setView);
                editor.commit();
            }
        });

        choiceViewGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(btnNearby.getId() == checkedId) {
                    btnNearby.setChecked(true);
                    btnFriends.setChecked(false);
                    setView = "nearby";
                } else {
                    btnNearby.setChecked(false);
                    btnFriends.setChecked(true);
                    setView = "friends";
                }
            }
        });
    }

    public String getSetView() {
        return setView;
    }
}
