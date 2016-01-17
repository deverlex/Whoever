package vn.whoever.utils;

import java.util.Date;

import vn.whoever.dao.ConnectionDB;

/**
 * Created by spider man on 1/17/2016.
 */
public class ConvertTime {

    private static ConvertTime convertTime = new ConvertTime();

    public static ConvertTime getInstance() {
        return convertTime;
    }

    public synchronized String getTimeStatus(Date date) {

        return "13:02 hôm qua";
    }
}
