package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.utils.Initgc;
import vn.whoever.views.dialogs.DialogPrivacyPostStatus;

/**
 * Created by spider man on 4/8/2016.
 */
public class PostStatusFragment extends Fragment implements Initgc {

    private RelativeLayout toolbarPostStatus;
    private RelativeLayout toobarPrivacy;

    private ImageButton btnBackHome;
    private ImageButton btnPost;

    private EditText editContentStatus;
    private DialogPrivacyPostStatus privacyPost;

    private String status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_status_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        toolbarPostStatus = (RelativeLayout) view.findViewById(R.id.toolBarFromPostStatus);
        toobarPrivacy = (RelativeLayout) view.findViewById(R.id.toolBarChoicePrivacy);

        editContentStatus = (EditText) view.findViewById(R.id.contentTextInputStatus);
        btnBackHome = (ImageButton) toolbarPostStatus.findViewById(R.id.btnBackFromPostStatus);
        btnPost = (ImageButton) toolbarPostStatus.findViewById(R.id.btnSendStatus);
    }

    @Override
    public void initListener(View view) {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = editContentStatus.getText().toString();
                /**
                 * TODO: update for new feed & database SQLite
                 */

            }
        });

        toobarPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacyPost = new DialogPrivacyPostStatus();
                Bundle bundle = new Bundle();

                privacyPost.show(getActivity().getFragmentManager(), "Privacy Post Status");
            }
        });
    }

    @Override
    public void initGc() {

    }
}
