package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nguyen Van Do
 *	This class define format date in header packet HTTP or on URI
 */
public class FormatDate {

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private final DateFormat dateFormatUri = new SimpleDateFormat("yyyyMMddhhmmss");
	private String strDate;
	private Date date;

	public FormatDate() {
		super();
	}

	public FormatDate(Date date) {
		strDate = dateFormat.format(date);
	}

	public FormatDate(long date) {
		strDate = dateFormat.format(new Date(date));
	}

	public FormatDate(String strDate) {
		date = toDate(strDate);
	}

	public String toDateString() {
		return strDate;
	}

	public Date toDate() {
		return date;
	}

	public String toDateString(Date date) {
		return dateFormat.format(date);
	}

	public Date toDate(String strDate) {
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Date toDateUri(String strDate) {
		try {
			return dateFormatUri.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
