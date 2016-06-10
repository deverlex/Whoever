package vn.whoever.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.adapters.MessageAdapter;
import vn.whoever.utils.Initgc;
import vn.whoever.views.activities.MainActivity;

/**
 * Created by Nguyen Van Do on 4/9/2016.
 * This class isn't completed.
 */
public class MessageFragment extends Fragment implements Initgc {

    private EditText inputText;
    private ListView listMessage;
    private ImageButton btnBackHome;
    private ImageButton btnQickViewProfile;
    private ImageButton btnSendMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        listMessage = (ListView) view.findViewById(R.id.listMessageOfChatSession);
        MessageAdapter messageAdapter = new MessageAdapter(getActivity(), 6 ,4);
        listMessage.setAdapter(messageAdapter);

        btnBackHome = (ImageButton) view.findViewById(R.id.btnBackFromChat);
        btnQickViewProfile = (ImageButton) view.findViewById(R.id.btnQuickViewProfileFromChat);
        btnSendMessage = (ImageButton) view.findViewById(R.id.btnSendMessage);
    }

    @Override
    public void initListener(View view) {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnQickViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToProfile(new ProfileFragment(), "messageFrameToProfile");
            }
        });

//        btnSendMessage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {}
//        });
    }

    public void navigateToProfile(Fragment fragment, String strStack) {
        getParentFragment().getChildFragmentManager().beginTransaction()
                .replace(R.id.majorFrame, fragment).addToBackStack(strStack).commit();
        getParentFragment().getChildFragmentManager().executePendingTransactions();
    }

    @Override
    public void initGc() {}
}
