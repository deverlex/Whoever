package vn.whoever.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import vn.whoever.R;

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

    private FragmentManager frgManagerRegister;
    private FragmentTransaction frgTransactionRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_layout, null);

        frgManagerRegister = getActivity().getFragmentManager();

        setFeatureeEditTextNickname(view);
        setFeatureEditTextEmail(view);
        setFeatureEditTextPassword(view);

        setFeatureButtonCreateAccount(view);
        setFeatureCheckboxAgree(view);
        setFeatureTextViewTerm(view);

        return view;
    }

    public void setFeatureeEditTextNickname(View view) {
        editTextNickName = (EditText) view.findViewById(R.id.textEditNickNameRegister);
        editTextNickName.setTextColor(Color.parseColor("#ffffff"));
    }

    public void setFeatureEditTextEmail(View view) {
        editTextEmail = (EditText) view.findViewById(R.id.textEditEmailRegister);
        editTextEmail.setTextColor(Color.parseColor("#ffffff"));
    }

    public void setFeatureEditTextPassword(View view) {
        editTextPassword = (EditText) view.findViewById(R.id.textEditPasswordRegister);
        editTextPassword.setTextColor(Color.parseColor("#ffffff"));
    }

    public void setFeatureTextViewSignIn(View view) {
        textViewSignIn = (TextView) view.findViewById(R.id.textHaveAAccount);
        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: naviagte to login layout
                 */
                frgTransactionRegister = frgManagerRegister.beginTransaction();
                frgTransactionRegister.replace(R.id.layoutStartApp, new SignInFragment()).commit();
            }
        });
    }

    public void setFeatureButtonCreateAccount(View view) {
        btnCreateAccount = (Button) view.findViewById(R.id.signUpButton);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: check email, password, check agree
                 */

                frgTransactionRegister = frgManagerRegister.beginTransaction();
                frgTransactionRegister.replace(R.id.layoutStartApp, new WelcomeFragment()).commit();
            }
        });
    }

    public void setFeatureCheckboxAgree(View view) {
        checkBoxAgreeTerm = (CheckBox) view.findViewById(R.id.checkAgreeTermService);
        checkBoxAgreeTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setFeatureTextViewTerm(View view) {
        textViewTerm = (TextView) view.findViewById(R.id.textTermUserInfor);
        textViewTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
