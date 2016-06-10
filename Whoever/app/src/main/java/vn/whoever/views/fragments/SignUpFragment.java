package vn.whoever.views.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.whoever.R;
import vn.whoever.TransConnection.ContactQuery;
import vn.whoever.utils.Initgc;
import vn.whoever.utils.RegexUtils;
import vn.whoever.views.activities.MainActivity;

/**
 * Created by Nguyen Van Do on 12/24/2015.
 * This class implement sign up layout.
 */
public class SignUpFragment extends Fragment implements Initgc {

    private String nickName = "";
    private String ssoId = "";
    private String password = "";

    private EditText editTextNickName;
    private EditText editTextSsoId;
    private EditText editTextPassword;
    private CheckBox checkBoxAgreeTerm;
    private Button btnCreateAccount;
    private TextView textViewSignIn;
    private TextView textViewTerm;
    private Toast toast;
    private TextView textLogoApp;

    private boolean isCheckTerm = false;
    private Handler handler = new Handler();
    private int timeout;
    private String querySsoId;
    private boolean isHasCreate = true;

    private ContactQuery contactQuery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_layout, null);

        Bundle bundle = getArguments();
        ssoId = bundle.getString("ssoId");
        password = bundle.getString("password");
        init(view);
        initListener(view);

        return view;
    }

    @Override
    public void init(View view) {
        editTextNickName = (EditText) view.findViewById(R.id.textEditNickNameRegister);
        editTextNickName.setTextColor(Color.parseColor("#ffffff"));
        editTextSsoId = (EditText) view.findViewById(R.id.textEditSsoIdRegister);
        editTextSsoId.setTextColor(Color.parseColor("#ffffff"));
        editTextSsoId.setText(ssoId);
        editTextPassword = (EditText) view.findViewById(R.id.textEditPasswordRegister);
        editTextPassword.setTextColor(Color.parseColor("#ffffff"));
        editTextPassword.setText(password);

        textViewSignIn = (TextView) view.findViewById(R.id.textHaveAAccount);
        btnCreateAccount = (Button) view.findViewById(R.id.signUpButton);
        checkBoxAgreeTerm = (CheckBox) view.findViewById(R.id.checkAgreeTermService);
        textViewTerm = (TextView) view.findViewById(R.id.textTermUserInfor);

        textLogoApp = (TextView) view.findViewById(R.id.logoTextStartSignUp);
        Typeface bauhau93_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bauhau93.ttf");
        textLogoApp.setTypeface(bauhau93_font);

        contactQuery = new ContactQuery(getActivity());

        if (ssoId.length() > 7) {
            checkSuggestSsoId();
        }
    }

    @Override
    public void initListener(View view) {
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: check email, password, check agree
                 */
                ssoId = editTextSsoId.getText().toString();
                password = editTextPassword.getText().toString();
                nickName = editTextNickName.getText().toString();

                if (toast != null) {
                    toast.cancel();
                }

                if (isHasCreate && RegexUtils.getInstance().checkSsoId(ssoId) && RegexUtils.getInstance().checkPassword(password)
                        && RegexUtils.getInstance().checkNickName(nickName)) {
                    if (checkBoxAgreeTerm.isChecked()) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isSignUp", true);
                        bundle.putString("nickName", nickName);
                        bundle.putString("ssoId", ssoId);
                        bundle.putString("password", password);
                        WelcomeFragment welcomeFragment = new WelcomeFragment();
                        welcomeFragment.setArguments(bundle);
                        navigateToWelcome(welcomeFragment, "signupFrameToWelcome");
                    } else {
                        toast = Toast.makeText(getActivity().getApplicationContext(), "Choice agree to the Term", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Check format of Acccount ID, Password or Nickname again!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        checkBoxAgreeTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toast != null) {
                    toast.cancel();
                }
                if (isCheckTerm) {
                    isCheckTerm = false;
                    toast = Toast.makeText(getActivity().getApplicationContext(), "Not agree to the Term, don't create new account", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    isCheckTerm = true;
                    toast = Toast.makeText(getActivity().getApplicationContext(), "Greate, can create new account", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

//        textViewTerm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {}
//        });

        editTextNickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean check = RegexUtils.getInstance().checkNickName(editTextNickName.getText().toString());
                    if (!check) {
                        if (toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Nickname isn't standard of Nickname", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        editTextSsoId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String strSsoId = editTextSsoId.getText().toString();
                    boolean check = RegexUtils.getInstance().checkSsoId(strSsoId);
                    if (!check) {
                        if (toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Account ID isn't standard of Account ID", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    if (strSsoId.length() > 7) {
                        ssoId = strSsoId;
                        checkSuggestSsoId();
                    }
                }
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    boolean check = RegexUtils.getInstance().checkPassword(editTextPassword.getText().toString());
                    if (!check) {
                        if (toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Password isn't standard of password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }

    private void checkSuggestSsoId() {
        querySsoId = contactQuery.findSsoIdAvaiable(ssoId);
        timeout = 5;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (timeout > 0) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            querySsoId = contactQuery.getQuerySsoId();
                            if (querySsoId != null) {
                                timeout = 0;
                                if (querySsoId.equals("avaiable")) {
                                    Toast.makeText(getActivity(), "This account was avaiable!", Toast.LENGTH_SHORT).show();
                                    isHasCreate = false;
                                } else {
                                    isHasCreate = true;
                                }
                            }
                        }
                    });
                    --timeout;
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private void navigateToWelcome(Fragment fragment, String strStack) {
        MainActivity.frgTrans = MainActivity.frgtManager.beginTransaction();
        MainActivity.frgTrans.replace(R.id.mainFrame, fragment).addToBackStack(strStack).commit();
    }

    @Override
    public void onPause() {
        System.gc();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (toast != null) {
            toast.cancel();
        }
    }

    @Override
    public void initGc() {}
}
