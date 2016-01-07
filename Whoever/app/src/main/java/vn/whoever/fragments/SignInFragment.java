package vn.whoever.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.whoever.MainActivity;
import vn.whoever.R;
import vn.whoever.StartActivity;
import vn.whoever.Transactions.UserTransaction;
import vn.whoever.models.User;

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
    private Button btnSignin;
    private Button btnSkipSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_layout, null);

        setFeatureEmailEditText(view);
        setFeaturePasswordEditText(view);
        setCreateNewAccount(view);

        setButtonSignInWithAccount(view);
        setButtonSkipSignIn(view);


        textTerm = (TextView) view.findViewById(R.id.textTermUserInfor);

        return view;
    }

    public void setCreateNewAccount(View view) {
        textSignUp = (TextView) view.findViewById(R.id.textCreateNewUser);
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, new SignUpFragment()).commit();
            }
        });
    }

    public void setFeatureEmailEditText(final View view) {
        emailText = (EditText) view.findViewById(R.id.inputEmailStart);
        emailText.setTextColor(Color.parseColor("#ffffff"));

        emailText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // check key input

                return false;
            }
        });
    }

    public void setFeaturePasswordEditText(View view) {
        passwordText = (EditText) view.findViewById(R.id.inputPasswordStart);
        passwordText.setTextColor(Color.parseColor("#ffffff"));

        passwordText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                return false;
            }
        });
    }

    public void setButtonSkipSignIn(View view) {
        btnSkipSignIn = (Button) view.findViewById(R.id.skipSignInButton);
        btnSkipSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMain();
            }
        });
    }

    public void setButtonSignInWithAccount(View view) {
        btnSignin = (Button) view.findViewById(R.id.signInButton);
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                if (checkLoginAccount()) {
                    navigateToMain();
                } else {
                    Toast toastLogin = new Toast(getActivity());
                }
            }
        });
    }

    public void navigateToMain() {
        Intent intentMain = new Intent(getActivity(), MainActivity.class);
        startActivity(intentMain);
        getActivity().finish();
    }

    public boolean checkLoginAccount() {
        /**
         * TODO: check password and email on server
          */
        User user = UserTransaction.getInstance().getRequestLogin(getActivity(), "nguyendo94vn@gmail.com", "12345678");
        if(user != null) {
            Log.d("nickName: ", user.getNickName());
            Log.d("email: ", user.getEmail());
            Log.d("password: ", user.getPassword());
        }

        return true;
    }


}
