package vn.whoever.views.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import vn.whoever.R;
import vn.whoever.TransConnection.ContactTrans;
import vn.whoever.TransConnection.HttpStatus;
import vn.whoever.TransConnection.StatusTrans;
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
    private TextView textLoad;
    private int cLoop = 0;
    private StatusTrans statusTrans = null;
    private int delay = 200;
    private boolean isLoadDb = false;
    private Integer httpStatus = null;

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
        textLoad = (TextView) view.findViewById(R.id.textWaitLoadData);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarLoadData);
        progressBar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#930a0a"));
        }
    }

    @Override
    public void initListener(View view) {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    progress += 20;
                    if(!isLoadDb) {
                        loadData();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                            if(statusTrans != null && progress == progressBar.getMax()) {
                                httpStatus = statusTrans.getHttpStatusCode();
                                if(httpStatus != null && httpStatus == HttpStatus.SC_OK) {
                                    cLoop = 5;
                                    MainActivity.frgTrans = MainActivity.frgtManager.beginTransaction();
                                    MainActivity.frgTrans.replace(R.id.mainFrame, new MainFragment()).commit();
                                } else {
                                    cLoop += 1;
                                    progressBar.setProgress(0);
                                    if(cLoop == 5) {
                                        MainActivity.frgTrans = MainActivity.frgtManager.beginTransaction();
                                        MainActivity.frgTrans.replace(R.id.mainFrame, new MainFragment()).commit();
                                        Toast.makeText(getActivity(), "Service isn't response, try later", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                progress = 0;
                            } else if(statusTrans == null && progress == progressBar.getMax()) {
                                MainActivity.frgTrans = MainActivity.frgtManager.beginTransaction();
                                MainActivity.frgTrans.replace(R.id.mainFrame, new MainFragment()).commit();
                                Toast.makeText(getActivity(), "No connection to service", Toast.LENGTH_SHORT).show();
                                cLoop = 5;
                            }
                        }
                    });
                    if(cLoop == 5) break;
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void loadData() {
        boolean isLogged = MainActivity.sharedPref.getBoolean("isLogged", false);
        if(!isLogged && checkInternetAvaiable()) {
            //TODO: first login to app
            SharedPreferences.Editor editor = MainActivity.sharedPref.edit();
            editor.putBoolean("isLogged", true);
            editor.commit();

            SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
            db.execSQL("delete from SetPostStatus");
            db.execSQL("delete from News");
            db.execSQL("delete from Home");

            ContentValues values = new ContentValues();
            values.put("id", 1);
            values.put("use", "anonymous");
            values.put("privacy", "public");
            db.insert("SetPostStatus", null, values);

            //db.close();

            statusTrans = new StatusTrans(getActivity());
            ContactTrans contactTrans = new ContactTrans(getActivity());
            statusTrans.getNewsFeed("nearby", 0);
            statusTrans.getHomePage();
            contactTrans.getContactOnline();
        } else {
            if(checkInternetAvaiable()) {
                // TODO: if have connection to server -> load new data
                SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
                db.execSQL("delete from News");
                db.execSQL("delete from Home");
                //db.close();

                statusTrans = new StatusTrans(getActivity());
                ContactTrans contactTrans = new ContactTrans(getActivity());
                statusTrans.getNewsFeed("nearby", 0);
                statusTrans.getHomePage();
                contactTrans.getContactOnline();
            }
        }
        if(statusTrans == null) delay = 100;
        isLoadDb = true;
    }

    @Override
    public void initGc() {}

    @Override
    public void onPause() {
        super.onPause();
    }

    private boolean checkInternetAvaiable() {
        try{
            URL myUrl = new URL("http://192.168.1.112:8080/mainserver/");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(1500);
            connection.connect();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
