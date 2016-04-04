package vn.whoever.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.whoever.R;
import vn.whoever.utils.AppGc;

/**
 * Created by spider man on 1/16/2016.
 */
public class SearchContactActivity extends AppCompatActivity implements AppGc {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact_layout);
    }

    public void init() {

    }

    public void initListener() {

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
