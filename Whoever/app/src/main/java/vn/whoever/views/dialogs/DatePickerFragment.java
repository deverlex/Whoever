package vn.whoever.views.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by spider man on 2/24/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int year = 2000;
    private int dayOfMonth = 1;
    private int month = 1;

    private DatePickerDialog datePickerDialog;
    private TextView viewDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /*
        final Calendar c = Calendar.getInstance();
        int year =  2000;//c.get(Calendar.YEAR);
        int month =  1;//c.get(Calendar.MONTH);
        int day =  1;//c.get(Calendar.DAY_OF_MONTH);
        */
        return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.dayOfMonth = dayOfMonth;

        String strDate = "";
        if(dayOfMonth < 10) {
            strDate += "0" + dayOfMonth;
        } else {
            strDate += dayOfMonth;
        }

        if(month < 10) {
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
}
