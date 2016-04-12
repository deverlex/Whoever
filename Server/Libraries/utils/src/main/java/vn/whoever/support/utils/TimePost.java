package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		if(subTime < 5000L) {
			return "just now";
		} else if(subTime <  60000L) {
			return "" + (int) subTime/1000L + " sec"; 
		} else if(subTime < 3600000L) {
			return "" + (int) subTime/60000L + " min";
		} else if (subTime < 86400000L){
			return "" + (int) subTime / 3600000L + " hrs";
		}
		DateFormat fmDay = new SimpleDateFormat("MMMM dd");
		DateFormat fmHour = new SimpleDateFormat("hh:mm a");
		return fmDay.format(date) + " at " + fmHour.format(date);
	}
}
