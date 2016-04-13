package vn.whoever.views.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import vn.whoever.models.dao.LanguageDao;

/**
 * Created by spider man on 2/24/2016.
 */
public class LanguagePickerFragment extends DialogFragment {

    private HashMap<String, String> mapLanguage;
    private ArrayList<String> listLanguage = new ArrayList<>();
    private ArrayList<String> listkey = new ArrayList<>();
    private TextView textLanguage;
    private int position = 0;
    private CharSequence[] cs;
    private AlertDialog.Builder dialog;

    private String langCode;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Resources res = getActivity().getResources();
        //Bundle bundle = getArguments();

        dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("Please Select");
        dialog.setPositiveButton("Cancel", new PositiveButton());


        String sysCodeLanguage = Locale.getDefault().getISO3Language().substring(0, 2);
        getLangName(sysCodeLanguage);

        for (Map.Entry<String, String> select : mapLanguage.entrySet()) {
            listLanguage.add(select.getValue());
            listkey.add(select.getKey());
        }

        if(position == 0 || position == -1) {
            position = listkey.indexOf(sysCodeLanguage);
        }

        cs = listLanguage.toArray(new CharSequence[listLanguage.size()]);
        dialog.setSingleChoiceItems(cs, position, selectedItem);

        return dialog.create();
    }

    class PositiveButton implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int wh) {
            dialogInterface.dismiss();
        }
    }

    DialogInterface.OnClickListener selectedItem = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInf, int selected) {
            position = selected;
            textLanguage.setText(listLanguage.get(position));
            langCode = listkey.get(position);
            dialogInf.dismiss();
        }
    };

    public void setTextLanguage(TextView textView) {
        this.textLanguage = textView;
    }

    public String getLangCode() {
        return langCode;
    }

    public String getLangName(String langCode) {
        mapLanguage = LanguageDao.getInstance(getActivity()).getArrayLanguageSupport();
        return mapLanguage.get(langCode);
    }
}
