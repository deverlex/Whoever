package vn.whoever.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/20/2016.
 */
public class PrivacyActivity extends AppCompatActivity implements AppGc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.privacy_user_layout);
    }

    @Override
    public void initGc() {

    }
}
