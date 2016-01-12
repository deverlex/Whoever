package vn.whoever.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import vn.whoever.R;
import vn.whoever.StartActivity;

/**
 * Created by spider man on 12/24/2015.
 */
public class SignUpFragment extends Fragment {

    private String nickName = "";
    private String email = "";
    private String password = "";

    private EditText editTextNickName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private CheckBox checkBoxAgreeTerm;
    private Button btnCreateAccount;
    private TextView textViewSignIn;
    private TextView textViewTerm;

    private boolean isCheckTerm = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_layout, null);

        init(view);
        initListener(view);

        return view;
    }

    public void init(View view) {
        editTextNickName = (EditText) view.findViewById(R.id.textEditNickNameRegister);
        editTextNickName.setTextColor(Color.parseColor("#ffffff"));
        editTextEmail = (EditText) view.findViewById(R.id.textEditEmailRegister);
        editTextEmail.setTextColor(Color.parseColor("#ffffff"));
        editTextPassword = (EditText) view.findViewById(R.id.textEditPasswordRegister);
        editTextPassword.setTextColor(Color.parseColor("#ffffff"));

        textViewSignIn = (TextView) view.findViewById(R.id.textHaveAAccount);
        btnCreateAccount = (Button) view.findViewById(R.id.signUpButton);
        checkBoxAgreeTerm = (CheckBox) view.findViewById(R.id.checkAgreeTermService);
        textViewTerm = (TextView) view.findViewById(R.id.textTermUserInfor);
    }

    public void initListener(View view) {
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: naviagte to login layout
                 */
                StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, new SignInFragment()).commit();
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: check email, password, check agree
                 */
                if(checkBoxAgreeTerm.isChecked()) {
                    StartActivity.frgStartTransaction = StartActivity.frgStartManager.beginTransaction();
                    StartActivity.frgStartTransaction.replace(R.id.layoutStartApp, new WelcomeFragment()).commit();
                } else {
                    Log.d("SIGNIN: ", "don't checked in checkbox!");
                }
            }
        });

        checkBoxAgreeTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheckTerm) {
                    isCheckTerm = false;
                } else {

                }
            }
        });

        textViewTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
