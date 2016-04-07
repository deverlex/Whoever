package vn.whoever.support.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private final DateFormat dateFormatUri = new SimpleDateFormat("yyyyMMddhhmmss");

	public FormatDate() {
		super();
	}

	public String toString(Date date) {
		return dateFormat.format(date);
	}

	public Date toDate(String strDate) throws ParseException {
		return dateFormat.parse(strDate);
	}
	
	public Date toDateUri(String strDate) throws ParseException {
		return dateFormatUri.parse(strDate);
	}
}
