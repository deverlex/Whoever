package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nguyen Van Do
 *	This class handle format time status update lasted
 */

public class TimeUp {

	private Date date;

	public TimeUp(Date date) {
		this.date = date;
	}

	public String getTime() {
		long subTime = (new Date()).getTime() - date.getTime();
		if (subTime < 5000L) {
			return "just now";
		} else if (subTime < 60000L) {
			return (int) subTime / 1000L + " sec";
		} else if (subTime < 3600000L) {
			return (int) subTime / 60000L + " min";
		} else if (subTime < 86400000L) {
			return (int) subTime / 3600000L + " hrs";
		}
		DateFormat fmDay = new SimpleDateFormat("MMMM dd");
		return fmDay.format(date);
	}
}
