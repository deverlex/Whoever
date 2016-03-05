package vn.whoever.dialogs;

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
import java.util.List;
import java.util.Locale;
import java.util.Map;

import vn.whoever.dao.LanguageDao;
import vn.whoever.models.ArrayLanguage;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Resources res = getActivity().getResources();
        //Bundle bundle = getArguments();

        dialog = new AlertDialog.Builder(getActivity());

        dialog.setTitle("Please Select");
        dialog.setPositiveButton("Cancel", new PositiveButton());

        mapLanguage = LanguageDao.getInstance(getActivity()).getArrayLanguageSupport();

        String sysCodeLanguage = Locale.getDefault().getISO3Language().substring(0, 2);

        for (Map.Entry<String, String> select : mapLanguage.entrySet()) {
            listLanguage.add(select.getValue());
            listkey.add(select.getKey());
        }

        if(position == 0) {
            position = listkey.indexOf(sysCodeLanguage);
        }

        if(position == -1) {
            position = listkey.indexOf("en");
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
            dialogInf.dismiss();
        }
    };

    public void setTextLanguage(TextView textView) {
        this.textLanguage = textView;
    }

}
