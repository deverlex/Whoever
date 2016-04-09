package vn.whoever.views.fragments;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.utils.Initgc;
import vn.whoever.views.activities.MainActivity;

/**
 * Created by spider man on 4/9/2016.
 */
public class LoadFragment extends Fragment implements Initgc {

    private ProgressBar progressBar;
    private int progress = 0;
    private Handler handler = new Handler();
    private Thread thread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.load_layout, container, false);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        TextView logoLoad = (TextView) view.findViewById(R.id.logoTextLoad);
        Typeface bauhau93_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bauhau93.ttf");
        logoLoad.setTypeface(bauhau93_font);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarLoadData);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#eb4949"));
        }
    }

    @Override
    public void initListener(View view) {
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
                                MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
                                MainActivity.frgTransaction.replace(R.id.mainFrame, new MainFragment()).commit();
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

    @Override
    public void initGc() {

    }
}
