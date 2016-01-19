package vn.whoever.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import vn.whoever.R;
import vn.whoever.utils.Initgc;

/**
 * Created by spider man on 1/19/2016.
 */
public class OnlineListFragment extends Fragment implements Initgc {

    private EditText textInputSearch;
    private ImageView btnDestroySearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup caontainer, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_list_layout, null);

        init(view);
        initListener(view);
        return view;
    }

    @Override
    public void init(View view) {
        textInputSearch = (EditText) view.findViewById(R.id.textInputFromOnlineList);
        textInputSearch.setTextColor(Color.parseColor("#ffffff"));

        btnDestroySearch = (ImageView) view.findViewById(R.id.btnDestroyInputFromOnlineList);
    }

    @Override
    public void initListener(View view) {
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
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(textInputSearch, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
    }

    @Override
    public void initGc() {

    }
}
