package vn.whoever.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by spider man on 1/17/2016.
 */
public class TimeUtils {

    private static TimeUtils timeUtils = new TimeUtils();

    public static TimeUtils getInstance() {
        return timeUtils;
    }

    public synchronized String getTimeStatus(Date date) {

        return "13:02 hôm qua";
    }

    /**
     * For check old enough of user. must be suffering 13 year old;
     * @param year
     * @param month
     * @param dayOfMonth
     */
    public synchronized boolean isOldEnough(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int sub = calendar.get(Calendar.YEAR) - year;
        if(sub > 13) {
            return true;
        } else if(sub == 13) {
            if(month < calendar.get(Calendar.MONTH)) {
                return true;
            } else if(month == calendar.get(Calendar.MONTH) && dayOfMonth < calendar.get(Calendar.DAY_OF_MONTH)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
