package vn.whoever.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.telephony.TelephonyManager;
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
import vn.whoever.transp.UserTransaction;
import vn.whoever.utils.Initgc;
import vn.whoever.utils.RegexUtils;

/**
 * Created by spider man on 12/24/2015.
 */
public class SignInFragment extends Fragment implements Initgc {

    private String email = "";
    private String password = "";

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textSignUp;
    private TextView textTerm;
    private Button btnSignin;
    private Button btnSkipSignIn;

    private Toast toast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_layout, null);

        init(view);
        initListener(view);

        return view;
    }

    @Override
    public void init(View view) {
        textSignUp = (TextView) view.findViewById(R.id.textCreateNewUser);
        editTextEmail = (EditText) view.findViewById(R.id.inputEmailStart);
        editTextEmail.setTextColor(Color.parseColor("#ffffff"));
        editTextPassword = (EditText) view.findViewById(R.id.inputPasswordStart);
        editTextPassword.setTextColor(Color.parseColor("#ffffff"));
        btnSkipSignIn = (Button) view.findViewById(R.id.skipSignInButton);
        btnSignin = (Button) view.findViewById(R.id.signInButton);
        textTerm = (TextView) view.findViewById(R.id.textTermUserInfor);
    }

    @Override
    public void initListener(View view) {
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, new SignUpFragment()).commit();
            }
        });

        editTextEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // check key input

                return false;
            }
        });

        editTextPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                return false;
            }
        });

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    boolean check = RegexUtils.checkEmail(editTextEmail.getText().toString());
                    if(!check) {
                        if(toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Email isn't standard of email require", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    boolean check = RegexUtils.checkPassword(editTextPassword.getText().toString());
                    if(!check) {
                        if(toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Password isn't standard of email require", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        btnSkipSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: get IMEI of phone send to server
                 */

                //String serialNb = getSerialNumberUser();
                //UserTransaction.getInstance(getActivity(), null).getRequestLoginAnonymous(serialNb);
                StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, new WelcomeFragment()).commit();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editTextEmail.getText().toString();
                password = editTextPassword.getText().toString();
                /**
                 * TODO: after check password and email => demo
                 */
              //  email = "nguyendo94vn@gmail.com";
              //  password = "12345678";

                if(RegexUtils.checkEmail(email) && RegexUtils.checkPassword(password)) {
                    UserTransaction.getInstance(getActivity(), null).getRequestLogin(email, password);
                } else {
                    if(toast != null) {
                        toast.cancel();
                    }
                    // TODO: show a toast alert: standard of email & password input fails
                    toast = Toast.makeText(getActivity(), "Try check email or password, please", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public String getSerialNumberUser() {
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public void navigateToMain() {
        Intent intentMain = new Intent(getActivity(), MainActivity.class);
        startActivity(intentMain);
        getActivity().finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(toast != null) {
            toast.cancel();
        }
    }

    @Override
    public void initGc() {

    }
}
