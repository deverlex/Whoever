package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePost {

	
	private static TimePost tpost = new TimePost();
	
	public static String getTimePost(Date date) {
		return tpost.time(date);
	}
	
	private TimePost() {
		super();
	}
	
	public String time(Date date) {
		long subTime = (new Date()).getTime() - date.getTime();
		if(subTime < 60000L) {
			return "just now";
		} else if(subTime < 3600000L) {
			return "" + (int) subTime/3600000 + " min";
		} else if (subTime < 86400000){
			return "" + (int) subTime / 86400000 + " hrs";
		}
		DateFormat fmDay = new SimpleDateFormat("MMMM dd");
		DateFormat fmHour = new SimpleDateFormat("hh:mm a");
		return fmDay.format(date) + " at " + fmHour.format(date);
	}
}
