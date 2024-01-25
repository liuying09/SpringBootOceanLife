package com.oceanLife.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getDateTimeFormat(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date dateNow = new Date();
		String thisDate = dateFormat.format(dateNow);
		
		return thisDate;
	}
	
}
