package vn.whoever.views.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.whoever.views.activities.MainActivity;
import vn.whoever.R;
import vn.whoever.transactionconn.BeginTransaction;
import vn.whoever.utils.Initgc;
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
    private TextView logoText;

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

        logoText = (TextView) view.findViewById(R.id.logoTextStartSignIn);
        Typeface bauhau93_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bauhau93.ttf");
        logoText.setTypeface(bauhau93_font);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#eb4949"));
        }
    }

    @Override
    public void initListener(View view) {
        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignUp(new SignUpFragment(), "signinFrameToSignUp");
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

                Bundle bundle = new Bundle();
                bundle.putBoolean(WelcomeFragment.KEY_USE_ACCOUNT, false);
                WelcomeFragment welcomeFragment = new WelcomeFragment();
                welcomeFragment.setArguments(bundle);
                navigateToWelcome(welcomeFragment, "signinFrameToWelcome");
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

                if (toast != null) {
                    toast.cancel();
                }

                if (RegexUtils.getInstance().checkSsoId(ssoId) && RegexUtils.getInstance().checkPassword(password)) {
                    int stateLogin = BeginTransaction.getInstance(getActivity(), null).getRequestLogin(ssoId, password);
                    /**
                     * Account avaiable => Activity
                     * else
                     * => create new account
                     */
                    if (true) {
                        //chuyen sang mainFragment
                    } else if (true) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ssoId", ssoId);
                        bundle.putString("password", password);
                        SignUpFragment signUpFragment = new SignUpFragment();
                        signUpFragment.setArguments(bundle);
                        navigateToSignUp(signUpFragment, "signinFrameToSignUp");
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

    private void navigateToWelcome(Fragment fragment, String strStack) {
        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgTransaction.replace(R.id.mainFrame, fragment).addToBackStack(strStack).commit();
    }

    private void navigateToSignUp(Fragment fragment, String strStack) {
        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgTransaction.replace(R.id.mainFrame, fragment).addToBackStack(strStack).commit();
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
