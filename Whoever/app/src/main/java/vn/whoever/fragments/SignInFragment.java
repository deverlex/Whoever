package vn.whoever.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.whoever.R;
import vn.whoever.utils.SizeDisplay;

/**
 * Created by spider man on 12/24/2015.
 */
public class SignInFragment extends Fragment {

    private String email = "";
    private String password = "";

    private EditText emailText;
    private EditText passwordText;

    private TextView textSignUp;
    private TextView textTerm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_layout, null);

        emailText = (EditText) view.findViewById(R.id.inputEmailStart);
        emailText.setTextColor(Color.parseColor("#ffffff"));

        passwordText = (EditText) view.findViewById(R.id.inputPasswordStart);
        passwordText.setTextColor(Color.parseColor("#ffffff"));

        Button btnSignin = (Button) view.findViewById(R.id.signInButton);
      //  btnSignin.setHeight((int)SizeDisplay.convertPxtoDp(40, getActivity()));
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();

                if(checkLoginAccount()) {

                } else {
                    Toast toastLogin = new Toast(getActivity());
                }
            }
        });

        return view;
    }

    public boolean checkLoginAccount() {

        /**
         * TODO: check password and email on server
          */
        return true;
    }
}
