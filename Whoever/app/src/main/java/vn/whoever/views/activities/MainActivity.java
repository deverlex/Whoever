package vn.whoever.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import vn.whoever.R;
import vn.whoever.utils.*;
import vn.whoever.views.fragments.LoadFragment;
import vn.whoever.views.fragments.SignInFragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager frgtManager;
    public static FragmentTransaction frgTransaction;
    public static final String START_SYSTEM = "SYSTEM_SETTING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Storage.setInstance(getSharedPreferences(START_SYSTEM, MODE_PRIVATE));
        boolean isLogin = Storage.getInstance().getBoolean("isLogin", false);

        frgtManager = getSupportFragmentManager();
        frgTransaction = frgtManager.beginTransaction();

        if(isLogin) {
            frgTransaction.replace(R.id.mainFrame, new LoadFragment()).commit();
        } else {
            frgTransaction.replace(R.id.mainFrame, new SignInFragment()).commit();
        }
        hiddenSoftInput();
    }

    public void hiddenSoftInput() {
        View view = this.getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getFragments() != null) {
            for (Fragment frag : fm.getFragments()) {
                if (frag != null && frag.isVisible()) {
                    FragmentManager childFm = frag.getChildFragmentManager();
                    if (childFm.getBackStackEntryCount() > 0) {
                        childFm.popBackStack();
                        return;
                    }
                }
            }
        }
        if (getFragmentManager() != null && getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        }
        super.onBackPressed();
    }

}
