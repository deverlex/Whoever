package vn.whoever.activities;

import android.app.Service;
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
    private RelativeLayout btnBackSearch;
    private ImageView btnDestroySearch;

    @Override
    public void onCreate(Bundle savedStanceState) {
        super.onCreate(savedStanceState);

        setContentView(R.layout.search_layout);

        textInputSearch = (EditText) findViewById(R.id.textInputSearch);
        textInputSearch.setTextColor(Color.parseColor("#ffffff"));

        btnDestroySearch = (ImageView) findViewById(R.id.btnDestroySearch);
        btnBackSearch = (RelativeLayout) findViewById(R.id.btnBackSearch);

        setEventForLayout();
    }

    public void setEventForLayout() {
     //   InputMethodManager imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);
    //    imm.showSoftInput(textInputSearch, 0);
    //    textInputSearch.requestFocus();

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
            }
        });

        btnBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMain();
            }
        });
    }

    public void navigateToMain() {
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
        finish();
    }
}
