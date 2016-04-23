package vn.whoever.views.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import vn.whoever.R;
import vn.whoever.models.Contact;
import vn.whoever.models.dao.ConnDB;
import vn.whoever.views.fragments.LoadFragment;
import vn.whoever.views.fragments.SignInFragment;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager frgtManager;
    public static FragmentTransaction frgTrans;
    public static SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("SETTING_SYSTEM", MODE_PRIVATE);
        boolean isLogged = sharedPref.getBoolean("isLogged", false);

        frgtManager = getSupportFragmentManager();
        frgTrans = frgtManager.beginTransaction();

        /**
         * TODO: connection to DB
         *
         */
        Bundle bundle = new Bundle();
        if(isLogged) {
            frgTrans.replace(R.id.mainFrame, new LoadFragment()).commit();
        } else {
            frgTrans.replace(R.id.mainFrame, new SignInFragment()).commit();
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

    @Override
    public void onPause() {
        System.gc();
        super.onPause();
    }

    @Override
    public void onStart() {
        ConnDB.getConn(this);
        ConnDB.getConn().openDataBase();
        super.onStart();
    }

    @Override
    public void onStop() {
        ConnDB.getConn().close();
        super.onStop();
    }
}
