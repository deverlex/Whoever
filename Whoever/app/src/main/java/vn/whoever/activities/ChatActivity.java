package vn.whoever.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.EditText;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/20/2016.
 */
public class ChatActivity extends AppCompatActivity implements AppGc {

    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        init();
        initListener();
    }

    public void init() {
        inputText = (EditText) findViewById(R.id.inputMessageSend);
        inputText.getText().append("\ud83d\ude09");
    }

    public void initListener() {

    }

    @Override
    public void initGc() {

    }
}
