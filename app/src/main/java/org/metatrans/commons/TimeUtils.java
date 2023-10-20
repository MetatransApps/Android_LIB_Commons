package org.metatrans.commons;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
	
	public static String time2string(long time) {
		String second = "" + (time / 1000) % 60;
		String minute = "" + (time / (1000 * 60)) % 60;
		String hour   = "" + (time / (1000 * 60 * 60)) % 24;
		
		if (hour.length() == 1) {
			hour = "0" + hour;
		}

		if (minute.length() == 1) {
			minute = "0" + minute;
		}

		if (second.length() == 1) {
			second = "0" + second;
		}

		String time_string = hour + ":" + minute + ":" + second;
		
		return time_string;
	}


	public static String getReadableDateTime(long timestamp) {

		try{

			Calendar calendar = Calendar.getInstance();
			TimeZone tz = TimeZone.getDefault();
			calendar.setTimeInMillis(timestamp);
			//calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currenTimeZone = (Date) calendar.getTime();

			return sdf.format(currenTimeZone);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "";
	}
}
