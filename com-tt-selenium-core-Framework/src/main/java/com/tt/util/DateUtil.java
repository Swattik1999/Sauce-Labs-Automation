package com.tt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String dateFormat = "yyyy-MMM-dd:HH:mm:ss";

	public static String getCurrentDate(String format) {
		String output = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		output = sdf.format(d);
		return output;
	}
	
	public static String getCurrentDate()
	{
		return getCurrentDate(dateFormat);
	}

	public static String getDate(String incrementType, int factor) {
		String output = "";
		Calendar cal = Calendar.getInstance();

		if (incrementType.equalsIgnoreCase("MONTH")) {
			cal.add(Calendar.MONTH, factor);
		} else if (incrementType.equalsIgnoreCase("DAY")) 
		{
			cal.add(Calendar.DATE, factor);
		}
		
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

		output = sdf.format(d);
		return output;
	}

	public static long timeDiff(String before, String after) {
		long diff = 0;
		try {
			Date d1 = new SimpleDateFormat(dateFormat).parse(before);
			Date d2 = new SimpleDateFormat(dateFormat).parse(after);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd:HH:mm:ss");
			System.out.println(sdf.format(d1));
			System.out.println(sdf.format(d2));
			
			long beforeTime = d1.getTime();
			long afterTime = d2.getTime();
			
			System.out.println("before:" + beforeTime);
			System.out.println("after:" + afterTime);
			
			diff = (afterTime - beforeTime)/1000;
			
		} catch (Exception e) {

		}

		return diff;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(DateUtil.getCurrentDate());
		System.out.println(DateUtil.getDate("DAY", 2));
		long seconds = DateUtil.timeDiff("2021-Jan-30:22:03:41", "2021-Jan-31:22:03:41");
		long hours = seconds/3600;
		long days = hours/24;
		System.out.println("Time Difference in Days: " + days);
				
	}
}

