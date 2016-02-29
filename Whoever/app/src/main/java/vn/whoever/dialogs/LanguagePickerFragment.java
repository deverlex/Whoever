package vn.whoever.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;

import java.util.List;

import vn.whoever.R;
import vn.whoever.adapters.ChoiceLangueAdapter;

/**
 * Created by spider man on 2/24/2016.
 */
public class LanguagePickerFragment extends DialogFragment {

    final String languages[] = {"[Default System]","English", "Tiếng Việt"};

    public ListView listLanguage;
    public ChoiceLangueAdapter choiceLangueAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.language_choice_layout, container, false);
        init(view);
        initListener(view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void init(View view) {
        listLanguage = (ListView) view.findViewById(R.id.listLanguageChoiceWelcome);

        choiceLangueAdapter = new ChoiceLangueAdapter(getActivity());
        listLanguage.setAdapter(choiceLangueAdapter);
    }

    public void initListener(View view) {
        listLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}
