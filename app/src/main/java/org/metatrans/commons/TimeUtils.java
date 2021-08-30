package org.metatrans.commons;


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
	
}
