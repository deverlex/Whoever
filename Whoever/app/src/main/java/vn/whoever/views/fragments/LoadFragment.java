package vn.whoever.views.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.TransConn.NewsTrans;
import vn.whoever.models.dao.ConnDB;
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

        hiddenSoftInput();
        init(view);
        initListener(view);
        return view;
    }

    public void hiddenSoftInput() {
        View view = getActivity().getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
        //TODO: don dep DB
        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
        db.execSQL("delete from News");
        db.execSQL("delete from Home");
        db.close();
        /**
         * TODO: load data for homepage and news
         * load data for profile
         * load data for message
         */

        /**
         * download: news, home, online list, message : update UI laster
         * contacts: load on UI now
         */

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 6; ++i) {
                    switch (i) {
                        case 1:
                            //TODO: load home page
                            break;
                        case 2:
                            NewsTrans newsTrans = new NewsTrans(getActivity());
                            newsTrans.getNewsFeed("nearby", 0);
                            break;
                        case 3:
                            //load contacts list

                            break;
                        default:
                            break;
                    }
                    progress += 20;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            if(progress == progressBar.getMax()) {
                                MainActivity.frgTrans = MainActivity.frgtManager.beginTransaction();
                                MainActivity.frgTrans.replace(R.id.mainFrame, new MainFragment()).commit();
                            }
                        }
                    });
                    // TODO gi do
                    try {
                        Thread.sleep(300);
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

    @Override
    public void onPause() {
        super.onPause();
    }
}
