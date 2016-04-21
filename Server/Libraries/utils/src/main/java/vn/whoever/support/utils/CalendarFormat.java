package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarFormat {

	private DateFormat format = new SimpleDateFormat("yyyy-dd-MMMM");
	private Date date;
	
	public CalendarFormat(Date date) {
		this.date = date;
	}
	
	public String getStrDate() {
		return format.format(date);
	}
}
