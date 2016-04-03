package vn.whoever.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import vn.whoever.R;
import vn.whoever.adapters.MessageAdapter;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/20/2016.
 */
public class MessageActivity extends AppCompatActivity implements AppGc, AbsListView.OnScrollListener {

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
        MessageAdapter messageAdapter = new MessageAdapter(this, 6 ,4);
        listMessage.setAdapter(messageAdapter);

        btnBackHome = (ImageButton) findViewById(R.id.btnBackFromChat);
        btnQickViewProfile = (ImageButton) findViewById(R.id.btnQuickViewProfileFromChat);
        btnSendMessage = (ImageButton) findViewById(R.id.btnSendMessage);
    }

    public void initListener() {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                hiddenSoftInput();
                finish();
            }
        });

        btnQickViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavigateProfile();
            }
        });

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void hiddenSoftInput() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void onNavigateProfile() {
        Intent intent = new Intent(this, ProifileActivity.class);
        startActivity(intent);
    }

    @Override
    public void initGc() {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
