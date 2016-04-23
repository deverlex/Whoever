package vn.whoever.views.fragments;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import vn.whoever.R;
import vn.whoever.TransConnection.HttpStatus;
import vn.whoever.TransConnection.InfoTrans;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.views.activities.MainActivity;
import vn.whoever.views.dialogs.DatePickerFragment;
import vn.whoever.views.dialogs.LanguagePickerFragment;
import vn.whoever.utils.Initgc;
import vn.whoever.utils.TimeUtils;

/**
 * Created by spider man on 12/26/2015.
 */
public class WelcomeFragment extends Fragment implements Initgc {

    private TextView textViewBirthday;
    private TextView textViewLanguage;
    private Button btnPushApp;
    DatePickerFragment dateDialog;
    LanguagePickerFragment langDialog;
    private TextView textLogoApp;
    private ProgressDialog progressDialog;

    private Handler handler = new Handler();
    private int timeout;
    private Integer httpCode = null;

    private boolean isAccount = false; // for use register account
    private String langCode;
    private String ssoId;
    private String password;
    private String nickName;
    private String birthday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, container, false);

        Bundle bundle = getArguments();
        if(bundle.getBoolean("isSignUp")) {
            isAccount = true;
            ssoId = bundle.getString("ssoId");
            password = bundle.getString("password");
            nickName = bundle.getString("nickName");
        } else {
            isAccount = false;
        }

        hiddenSoftInput();

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        langCode = Locale.getDefault().getISO3Language().substring(0, 2);
        textViewBirthday = (TextView) view.findViewById(R.id.textBirthDayWelcome);
        textViewLanguage = (TextView) view.findViewById(R.id.textLanguageWelcome);
        btnPushApp = (Button) view.findViewById(R.id.buttonPushApp);

        dateDialog = new DatePickerFragment();
        dateDialog.setViewDate(textViewBirthday);

        langDialog  = new LanguagePickerFragment();
        langDialog.setTextLanguage(textViewLanguage);
        textViewLanguage.setText(langDialog.getLangName(langCode));

        textLogoApp = (TextView) view.findViewById(R.id.logoTextStartWelcome);
        Typeface bauhau93_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bauhau93.ttf");
        textLogoApp.setTypeface(bauhau93_font);
    }

    @Override
    public void initListener(final View view) {
        btnPushApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                /**
                 * In this, need push to server a schedule contain:
                 * birth day, language, user name and password
                 * -> update infor of user
                 */
                if (langDialog.getLangCode() != null) {
                    langCode = langDialog.getLangCode();
                }

                if (TimeUtils.getInstance().isOldEnough(dateDialog.getYear(), dateDialog.getMonth(), dateDialog.getDayOfMonth())) {

                    birthday = dateDialog.getDateString();

                    if (isAccount) {
                        timeout = 50;
                        final InfoTrans infoTrans = new InfoTrans(getActivity());
                        infoTrans.registerUser(ssoId, password, nickName, birthday, langCode);

                        progressDialog = ProgressDialog.show(getActivity(), "", "Waiting for login...", true);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (timeout > 0) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            httpCode = infoTrans.getHttpStatusCode();
                                            if (httpCode != null) {
                                                if (HttpStatus.getStatus(getActivity()).codeSignInAnonymous(httpCode)) {
                                                    insertDB();
                                                    loadDataActive();
                                                }
                                                timeout = 0;
                                                progressDialog.dismiss();
                                            }
                                            if (timeout == 1) {
                                                progressDialog.dismiss();
                                                HttpStatus.getStatus(getActivity()).codeSignInAnonymous(HttpStatus.SC_SERVICE_UNAVAIABLE);
                                            }
                                        }
                                    });
                                    --timeout;
                                    try {
                                        Thread.sleep(150);
                                    } catch (InterruptedException e) {
                                    }
                                }
                            }
                        }).start();

                    } else {
                        timeout = 40;
                        final InfoTrans infoTrans = new InfoTrans(getActivity());
                        infoTrans.getRequestLoginAnonymous(langCode);
                        progressDialog = ProgressDialog.show(getActivity(), "", "Waiting for login...", true);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (timeout > 0) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            httpCode = infoTrans.getHttpStatusCode();
                                            if (httpCode != null) {
                                                if (HttpStatus.getStatus(getActivity()).codeSignInAnonymous(httpCode)) {
                                                    insertDbForAnnonymous();
                                                    loadDataActive();
                                                }
                                                timeout = 0;
                                                progressDialog.dismiss();
                                            }
                                            if (timeout == 1) {
                                                progressDialog.dismiss();
                                                HttpStatus.getStatus(getActivity()).codeSignInAnonymous(HttpStatus.SC_SERVICE_UNAVAIABLE);
                                            }
                                        }
                                    });
                                    --timeout;
                                    try {
                                        Thread.sleep(150);
                                    } catch (InterruptedException e) {
                                    }
                                }
                            }
                        }).start();
                    }
                } else {
                    Toast.makeText(getActivity(), "You haven't enough year old", Toast.LENGTH_LONG).show();
                }
            }
        });

        textViewBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: popup picker date
                 */
                dateDialog.show(getActivity().getFragmentManager(), "datePicker");
            }
        });

        textViewLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: popup menus language
                 */
                langDialog.show(getActivity().getFragmentManager(), "Dialog");

            }
        });
    }

    public void loadDataActive() {
        MainActivity.frgTrans = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgtManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Bundle bundle = new Bundle();
        bundle.putString("langCode", langCode);
        LoadFragment loadFragment = new LoadFragment();
        loadFragment.setArguments(bundle);
        MainActivity.frgTrans.replace(R.id.mainFrame, new LoadFragment()).commit();
    }

    private void insertDB() {
        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("ssoId", ssoId);
        values.put("password", password);
        db.execSQL("delete from LocalAccount");
        db.insert("LocalAccount", null, values);
        values.clear();
        values.put("nickName", nickName);
        values.put("birthday", birthday);
        db.execSQL("delete from LocalProfile");
        db.insert("LocalProfile", null, values);
        db.close();
    }

    private void insertDbForAnnonymous() {
        SQLiteDatabase db = ConnDB.getConn().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("birthday", birthday);
        db.update("LocalProfile", values, "id = 1", null);
        db.close();
    }

    public void hiddenSoftInput() {
        View view = getActivity().getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onPause() {
        System.gc();
        super.onPause();
    }

    @Override
    public void initGc() {

    }
}
