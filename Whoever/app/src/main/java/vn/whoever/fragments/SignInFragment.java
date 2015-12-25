package vn.whoever.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vn.whoever.R;
import vn.whoever.utils.SizeDisplay;

/**
 * Created by spider man on 12/24/2015.
 */
public class SignInFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_in_layout, null);

        EditText emailText = (EditText) view.findViewById(R.id.inputEmailStart);
        emailText.setTextColor(Color.parseColor("#ffffff"));

        Button btnSignin = (Button) view.findViewById(R.id.signInButton);
      //  btnSignin.setHeight((int)SizeDisplay.convertPxtoDp(40, getActivity()));

        return view;
    }
}
