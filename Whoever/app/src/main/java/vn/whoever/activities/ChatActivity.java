package vn.whoever.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.adapters.ChatAdapter;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/20/2016.
 */
public class ChatActivity extends AppCompatActivity implements AppGc {

    private EditText inputText;
    private ListView listMessage;
    private ImageButton btnBackHome;
    private ImageButton btnQickViewProfile;
    private ImageButton btnSendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        init();
        initListener();
    }

    public void init() {
        listMessage = (ListView) findViewById(R.id.listMessageOfChatSession);
        ChatAdapter chatAdapter = new ChatAdapter(this);
        listMessage.setAdapter(chatAdapter);

        btnBackHome = (ImageButton) findViewById(R.id.btnBackFromChat);
        btnQickViewProfile = (ImageButton) findViewById(R.id.btnQuickViewProfileFromChat);
        btnSendMessage = (ImageButton) findViewById(R.id.btnSendMessage);
    }

    public void initListener() {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        btnQickViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
