package vn.whoever.activities;

import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
        setEventForLayout();
    }

    public void setEventForLayout() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textInputSearch, 0);
        textInputSearch.requestFocus();
        textInputSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textInputSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        textInputSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = textInputSearch.getText().toString();
                Log.d("INPUT TEXT: ", text);
                if(text.length() > 0) {
                    btnDestroySearch.setVisibility(View.VISIBLE);
                } else {
                    btnDestroySearch.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
    }
}
