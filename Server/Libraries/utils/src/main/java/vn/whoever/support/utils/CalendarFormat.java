package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author spider man
 *	Calendar format in Whoever
 */

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
