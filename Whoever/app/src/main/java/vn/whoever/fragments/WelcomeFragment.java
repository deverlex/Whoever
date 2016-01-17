package vn.whoever.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import vn.whoever.MainActivity;
import vn.whoever.R;
import vn.whoever.utils.InitFragment;

/**
 * Created by spider man on 12/26/2015.
 */
public class WelcomeFragment extends Fragment implements InitFragment {

    private TextView textViewBirthday;
    private TextView textViewLanguage;
    private Button btnPushApp;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, null);

        init(view);
        initListener(view);

        return view;
    }

    @Override
    public void init(View view) {
        textViewBirthday = (TextView) view.findViewById(R.id.textBirthDayWelcome);
        textViewLanguage = (TextView) view.findViewById(R.id.textLanguageWelcome);
        btnPushApp = (Button) view.findViewById(R.id.buttonPushApp);
    }

    @Override
    public void initListener(View view) {
        btnPushApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(getActivity(), MainActivity.class);
                startActivity(intentMain);
                getActivity().finish();
            }
        });

        textViewBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: popup picker date
                 */

            }
        });

        textViewLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: popup menus language
                 */

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
}
