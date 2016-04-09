package vn.whoever.views.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import vn.whoever.utils.Initgc;
import vn.whoever.utils.RegexUtils;
import vn.whoever.views.activities.MainActivity;

/**
 * Created by spider man on 12/24/2015.
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_layout, null);

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
        editTextPassword = (EditText) view.findViewById(R.id.textEditPasswordRegister);
        editTextPassword.setTextColor(Color.parseColor("#ffffff"));

        textViewSignIn = (TextView) view.findViewById(R.id.textHaveAAccount);
        btnCreateAccount = (Button) view.findViewById(R.id.signUpButton);
        checkBoxAgreeTerm = (CheckBox) view.findViewById(R.id.checkAgreeTermService);
        textViewTerm = (TextView) view.findViewById(R.id.textTermUserInfor);

        textLogoApp = (TextView) view.findViewById(R.id.logoTextStartSignUp);
        Typeface bauhau93_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bauhau93.ttf");
        textLogoApp.setTypeface(bauhau93_font);
    }

    @Override
    public void initListener(View view) {
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: naviagte to login layout
                 */
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

                if(toast != null) {
                    toast.cancel();
                }
                if(RegexUtils.getInstance().checkSsoId(ssoId) && RegexUtils.getInstance().checkPassword(password)
                        && RegexUtils.getInstance().checkNickName(nickName)) {
                    if(checkBoxAgreeTerm.isChecked()) {

                     //   StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(WelcomeFragment.KEY_USE_ACCOUNT, true);
                        WelcomeFragment welcomeFragment = new WelcomeFragment();
                        welcomeFragment.setArguments(bundle);
                     //   StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, welcomeFragment).commit();
                        MainActivity.frgTransaction = MainActivity.frgtManager.beginTransaction();
                        MainActivity.frgTransaction.replace(R.id.mainFrame, welcomeFragment).commit();

                    } else {
                        toast = Toast.makeText(getActivity().getApplicationContext(), "Choice agree to the Term", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Log.d("Nick", nickName);
                    Log.d("SsoId", ssoId);
                    Log.d("Pass", password);
                    toast = Toast.makeText(getActivity().getApplicationContext(),"Check Acccount ID, Password or Nickname", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        checkBoxAgreeTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null) {
                    toast.cancel();
                }
                if(isCheckTerm) {
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

        textViewTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editTextNickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    boolean check = RegexUtils.getInstance().checkNickName(editTextNickName.getText().toString());
                    if(!check) {
                        if(toast != null) {
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
                if(!hasFocus) {
                    boolean check = RegexUtils.getInstance().checkSsoId(editTextSsoId.getText().toString());
                    if(!check) {
                        if(toast != null) {
                            toast.cancel();
                        }
                        toast = Toast.makeText(getActivity(), "Email isn't standard of Account ID", Toast.LENGTH_LONG);
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
                        toast = Toast.makeText(getActivity(), "Password isn't standard of password", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
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
