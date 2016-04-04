package vn.whoever.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/20/2016.
 */
public class HelpCenterActivity extends AppCompatActivity implements AppGc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_center_layout);
    }

    @Override
    public void initGc() {

    }
}
