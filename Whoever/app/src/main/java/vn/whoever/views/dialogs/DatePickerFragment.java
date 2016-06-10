package vn.whoever.views.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by Nguyen Van Do on 2/24/2016.
 * This class handle event concerned date picker
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int year = 2000;
    private int dayOfMonth = 1;
    private int month = 1;

    private DatePickerDialog datePickerDialog;
    private TextView viewDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.dayOfMonth = dayOfMonth;

        // Update text time on Welcome layout
        String strDate = "";
        if (dayOfMonth < 10) {
            strDate += "0" + dayOfMonth;
        } else {
            strDate += dayOfMonth;
        }

        if (month < 10) {
            strDate += "/0" + month;
        } else {
            strDate += "/" + month;
        }
        strDate += "/" + year;
        viewDate.setText(strDate);
    }

    public void setViewDate(TextView viewDate) {
        this.viewDate = viewDate;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String getDateString() {
        String strDate = "";
        strDate += year;
        if (month < 10) {
            strDate += "-0" + month;
        } else {
            strDate += "-" + month;
        }
        if (dayOfMonth < 10) {
            strDate += "-0" + dayOfMonth;
        } else {
            strDate += "-" + dayOfMonth;
        }
        return strDate;
    }
}
