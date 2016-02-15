package vn.whoever.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/30/2016.
 */
public class CommentActivity extends AppCompatActivity implements AppGc {

    private ListView listComment;
    private TextView nickNamePostStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.comment_layout);
        init();
        initListener();
    }

    public void init() {

    }

    public void initListener() {

    }

    @Override
    public void initGc() {

    }
}
