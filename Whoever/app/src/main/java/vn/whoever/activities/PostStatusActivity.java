package vn.whoever.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/14/2016.
 */
public class PostStatusActivity extends AppCompatActivity implements AppGc {

    private RelativeLayout toolbar;
    private RelativeLayout btnBack;
    private RelativeLayout btnPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_status_layout);

        init();
        initListener();
    }

    public void init() {
        toolbar = (RelativeLayout) findViewById(R.id.toolBarFromPostStatus);
        btnBack = (RelativeLayout) toolbar.findViewById(R.id.btnBackFromPostStatus);
        btnPost = (RelativeLayout) toolbar.findViewById(R.id.btnSendStatus);

    }

    public void initListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initGc() {

    }
}
