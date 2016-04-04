package vn.whoever.views.fragments;

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

import vn.whoever.views.activities.MainActivity;
import vn.whoever.R;
import vn.whoever.views.activities.StartActivity;
import vn.whoever.transactionlayer.ConnectionTransaction;
import vn.whoever.utils.Initgc;
import vn.whoever.utils.LoginState;
import vn.whoever.utils.RegexUtils;

/**
 * Created by spider man on 12/24/2015.
 */
public class SignInFragment extends Fragment implements Initgc {

    private String ssoId = "";
    private String password = "";

    private EditText editTextSsoId;
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
        editTextSsoId = (EditText) view.findViewById(R.id.inputSsoIdStart);
        editTextSsoId.setTextColor(Color.parseColor("#ffffff"));
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

        editTextSsoId.setOnKeyListener(new View.OnKeyListener() {
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

        editTextSsoId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    boolean check = RegexUtils.getInstance().checkSsoId(editTextSsoId.getText().toString());
                    if(!check) {
                        if(toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Account isn't standard of account require", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    boolean check = RegexUtils.getInstance().checkPassword(editTextPassword.getText().toString());
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


                StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putBoolean(WelcomeFragment.KEY_USE_ACCOUNT, false);
                WelcomeFragment welcomeFragment = new WelcomeFragment();
                welcomeFragment.setArguments(bundle);
                StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, welcomeFragment).commit();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ssoId = editTextSsoId.getText().toString();
                password = editTextPassword.getText().toString();
                /**
                 * TODO: after check password and email => demo
                 */
              //  email = "nguyendo94vn@gmail.com";
              //  password = "12345678";

                if(toast != null) {
                    toast.cancel();
                }

                if(RegexUtils.getInstance().checkSsoId(ssoId) && RegexUtils.getInstance().checkPassword(password)) {
                    int stateLogin = ConnectionTransaction.getInstance(getActivity(), null).getRequestLogin(ssoId, password);

                    if(LoginState.PASS == stateLogin){
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else if(LoginState.WELCOME == stateLogin) {
                        StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                        StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, new WelcomeFragment()).commit();
                    } else {
                        toast = Toast.makeText(getActivity(), "Check your connection or your Acccount ID", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    // TODO: show a toast alert: standard of email & password input fails
                    toast = Toast.makeText(getActivity(), "Try check Account ID or Password, please", Toast.LENGTH_LONG);
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
