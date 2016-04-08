package vn.whoever.views.activities;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.utils.AppGc;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 4/4/2016.
 */
public class LoadActivity extends AppCompatActivity implements AppGc {

    private ProgressBar progressBar;
    private int progress = 0;
    private Handler handler = new Handler();
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_layout);
        init();
        initListener();
    }

    public void init() {
        TextView logoLoad = (TextView) findViewById(R.id.logoTextLoad);
        Typeface bauhau93_font = Typeface.createFromAsset(getAssets(), "fonts/bauhau93.ttf");
        logoLoad.setTypeface(bauhau93_font);

        progressBar = (ProgressBar) findViewById(R.id.progressBarLoadData);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    }

    public void initListener() {
        /**
         * TODO: load data for homepage and news
         * load data for profile
         * load data for message
         */

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 5; ++i) {

                    //load data in here

                    progress += 20;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            if(progress == progressBar.getMax()) {
                                navigateMain();
                            }
                        }
                    });

                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void navigateMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initGc() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
