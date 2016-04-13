package vn.whoever.views.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import vn.whoever.transactionconn.BeginTransaction;
import vn.whoever.R;
import vn.whoever.transactionconn.HttpStatus;
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
    private ProgressDialog dialog;

    private Handler handler = new Handler();
    private int timeout;

    public static final String KEY_USE_ACCOUNT = "isSignUp";

    private boolean isAccount = false; // for use register account
    private String langCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, container, false);
        hiddenSoftInput(view);

        Bundle bundle = getArguments();
        if(bundle.getBoolean(KEY_USE_ACCOUNT)) {
            isAccount = true;
        } else {
            isAccount = false;
        }

        init(view);
        initListener(view);
        return view;
    }

    public void hiddenSoftInput(View view) {;
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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
                Log.d("langCode", langCode);

                if (TimeUtils.getInstance().isOldEnough(dateDialog.getYear(), dateDialog.getMonth(), dateDialog.getDayOfMonth())) {

                    String strDate = dateDialog.getDateString();



                    if (isAccount) {
                        /**
                         * TODO: saved clear start activity
                         */
                        //BeginTransaction.getInstance(getActivity(), view).registerUser("satthumaulanh", "12345678", "hoa hai anh", "10-03-1994", "en");

                    } else {
                        timeout = 10;
                        BeginTransaction.getTransaction(getActivity()).getRequestLoginAnonymous(langCode);
                        dialog = ProgressDialog.show(getActivity(), "", "Waiting for login...", true);
                        dialog.show();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (timeout > 0) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Integer httpCode = BeginTransaction.getTransaction(getActivity()).getHttpStatusCode();
                                            if (httpCode != null) {
                                                if (HttpStatus.getStatus(getActivity()).code(httpCode)) {
                                                    loadDataActive();
                                                }
                                                timeout = 0;
                                            }
                                        }
                                    });
                                    --timeout;
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {}
                                }
                                dialog.dismiss();
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
                // DialogFragment newFragment = new LanguagePickerFragment();
                // newFragment.show(getActivity().getFragmentManager(), "languagePicker");

                //LanguageDao languageDao = new LanguageDao(getActivity());
                //languageDao.getArrayLanguageSupport();

                /*
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(LanguagePickerFragment.DATA, getItems());
                bundle.putInt(LanguagePickerFragment.SELECTED, 0);
                dialog.setArguments(bundle);
                */
                langDialog.show(getActivity().getFragmentManager(), "Dialog");

            }
        });
    }

    public void loadDataActive() {
        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgtManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        MainActivity.frgTransaction.replace(R.id.mainFrame, new LoadFragment()).commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void initGc() {

    }

    /*
    private ArrayList<String> getItems()
    {
        ArrayList<String> ret_val = new ArrayList<String>();

        ret_val.add("Mikasa");
        ret_val.add("Crysta");
        ret_val.add("Ani");
        ret_val.add("Sasha");
        ret_val.add("Yumiru");
        return ret_val;
    }

    */
}
