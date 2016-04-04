package vn.whoever.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/21/2016.
 */
public class ProifileActivity extends AppCompatActivity implements AppGc {

    private ImageButton btnBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        init();
        initListener();
    }

    public void init() {
        btnBackHome = (ImageButton) findViewById(R.id.btnBackHomeFromProfile);

    }

    public void initListener() {
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void initGc() {

    }
}
