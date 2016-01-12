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

/**
 * Created by spider man on 12/26/2015.
 */
public class WelcomeFragment extends Fragment {

    private TextView textViewBirthday;
    private TextView textViewLanguage;
    private Button btnPushApp;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_layout, null);

        init(view);

        setButtonPushWelcome(view);
        setFeatureBirthdayWelcome(view);
        setFeatureLanguageWelcome(view);
        return view;
    }

    public void init(View view) {
        textViewBirthday = (TextView) view.findViewById(R.id.textBirthDayWelcome);
        textViewLanguage = (TextView) view.findViewById(R.id.textLanguageWelcome);
        btnPushApp = (Button) view.findViewById(R.id.buttonPushApp);
    }

    public void setFeatureBirthdayWelcome(View view) {
        textViewBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: popup picker date
                 */

            }
        });
    }

    public void setFeatureLanguageWelcome(View view) {
        textViewLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: popup menus language
                 */

            }
        });
    }

    public void setButtonPushWelcome(View view) {
        btnPushApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(getActivity(), MainActivity.class);
                startActivity(intentMain);
                getActivity().finish();
            }
        });
    }
}
