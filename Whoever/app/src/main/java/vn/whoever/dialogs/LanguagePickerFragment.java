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
import android.widget.NumberPicker;

/**
 * Created by spider man on 2/24/2016.
 */
public class LanguagePickerFragment extends DialogFragment {

    final String languages[] = {"[Default System]","English", "Tiếng Việt"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return null;
    }
}
