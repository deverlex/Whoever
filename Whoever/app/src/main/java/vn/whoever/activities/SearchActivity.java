package vn.whoever.activities;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import vn.whoever.MainActivity;
import vn.whoever.R;

/**
 * Created by spider man on 12/30/2015.
 */
public class SearchActivity extends AppCompatActivity {

    private EditText textInputSearch;
    private RelativeLayout btnBackHome;
    private ImageView btnDestroySearch;

    @Override
    protected void onCreate(Bundle savedStanceState) {
        super.onCreate(savedStanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.search_layout);

        init();
        initListener();
    }

    public void init() {
        textInputSearch = (EditText) findViewById(R.id.textInputFromSearch);
        textInputSearch.setTextColor(Color.parseColor("#ffffff"));

        btnDestroySearch = (ImageView) findViewById(R.id.btnDestroyInputFromSearch);
        btnBackHome = (RelativeLayout) findViewById(R.id.btnBackHomeFromSearch);
    }

    public void initListener() {

        textInputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (textInputSearch.getText().length() > 0) {
                    btnDestroySearch.setVisibility(View.VISIBLE);
                } else {
                    btnDestroySearch.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputSearch.getText().length() > 0) {
                    btnDestroySearch.setVisibility(View.VISIBLE);
                } else {
                    btnDestroySearch.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnDestroySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputSearch.setText("");
                if(textInputSearch.requestFocus()) {
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(textInputSearch, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });

        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMain();
            }
        });
    }

    public void navigateToMain() {
        Intent intentMain = new Intent(this, MainActivity.class);
        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentMain);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
    }
}
