package vn.whoever.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.whoever.MainActivity;
import vn.whoever.R;
import vn.whoever.dao.LanguageDao;
import vn.whoever.dialogs.DatePickerFragment;
import vn.whoever.dialogs.LanguagePickerFragment;
import vn.whoever.utils.ConvertSizeDisplay;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init(view);
        initListener(view);

        return view;
    }

    @Override
    public void init(View view) {
        textViewBirthday = (TextView) view.findViewById(R.id.textBirthDayWelcome);
        textViewLanguage = (TextView) view.findViewById(R.id.textLanguageWelcome);
        btnPushApp = (Button) view.findViewById(R.id.buttonPushApp);

        dateDialog = new DatePickerFragment();
        dateDialog.setViewDate(textViewBirthday);

        langDialog  = new LanguagePickerFragment();
        langDialog.setTextLanguage(textViewLanguage);
    }

    @Override
    public void initListener(View view) {
        btnPushApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * In this, need push to server a schedule contain:
                 * birth day, language, user name and password
                 * -> update infor of user
                 */
                if(TimeUtils.getInstance().isOldEnough(dateDialog.getYear(), dateDialog.getMonth(), dateDialog.getDayOfMonth())) {
                    /**
                     * TODO: send infor to server
                     *
                     */

                    Intent intentMain = new Intent(getActivity(), MainActivity.class);
                    startActivity(intentMain);
                    getActivity().finish();
                }   else {
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
