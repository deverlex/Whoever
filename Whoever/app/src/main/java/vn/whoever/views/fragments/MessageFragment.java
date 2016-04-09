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
 * Created by spider man on 4/9/2016.
 */
public class MessageFragment extends Fragment implements Initgc {

    private EditText inputText;
    private ListView listMessage;
    private ImageButton btnBackHome;
    private ImageButton btnQickViewProfile;
    private ImageButton btnSendMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_layout, container, false);

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
                MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
                MainActivity.frgTransaction.replace(R.id.mainFrame, new ProfileFragment()).addToBackStack("msgFrame").commit();
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initGc() {

    }
}
