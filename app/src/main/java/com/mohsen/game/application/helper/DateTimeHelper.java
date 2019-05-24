package com.mohsen.game.application.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeHelper {
	
	public static final double DAY_IN_MILLIS = 1000 * 60 * 60 * 24.0;
	// Date Period Selection
	public static final int KEY_DATE_THIS_MONTH = 1;
	public static final int KEY_DATE_THIS_SEASON = 2;
	public static final int KEY_DATE_THIS_YEAR = 3;
	public static final int KEY_DATE_NEXT_MONTH = 4;
	public static final int KEY_DATE_NEXT_SEASON = 5;
	public static final int KEY_DATE_NEXT_YEAR = 6;
	public static final int KEY_DATE_CUSTOM_DATE = 7;
	
	public static int getDiffDaysBetweenTwoDate(long fromDateInMillis, long toDateInMillis) {
		return (int) Math.ceil((toDateInMillis - fromDateInMillis) / (DAY_IN_MILLIS));
	}
	
	public static boolean equalDates(Calendar cal1, Calendar cal2) {
		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
			if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
				if (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
					return true;
		
		return false;
	}
	
	
	public static Date getGMTDate(Date currentDate) {
		
		TimeZone tz = TimeZone.getDefault();
		Date ret = new Date(currentDate.getTime() - tz.getRawOffset());
		
		
		// if we are now in DST, back off by the delta.  Note that we are checking the GMT date, this is the KEY.
		if (tz.inDaylightTime(ret)) {
			Date dstDate = new Date(ret.getTime() - tz.getDSTSavings());
			
			// check to make sure we have not crossed back into standard time
			// this happens when we are on the cusp of DST (7pm the day before the change for PDT)
			if (tz.inDaylightTime(dstDate)) {
				ret = dstDate;
			}
		}
		return ret;
	}
	
	public static Date getGMTDate(long milliSeconds) {
		return getGMTDate(new Date(milliSeconds));
	}
	
	public static long getGMTDateInMilliSeconds(Date currentDate) {
		Date ret = getGMTDate(currentDate);
		return ret.getTime();
	}
	
	public static long getGMTDateInMilliSeconds(long milliSeconds) {
		Date ret = getGMTDate(new Date(milliSeconds));
		return ret.getTime();
	}
	
	public static Date getCurrentGMTDate() {
		return getGMTDate(new Date(System.currentTimeMillis()));
	}
	
	public static long getCurrentGMTDateInMilliSeconds() {
		return getGMTDate(new Date(System.currentTimeMillis())).getTime();
	}
	
}
