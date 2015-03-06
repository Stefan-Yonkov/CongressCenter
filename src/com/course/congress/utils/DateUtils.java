package com.course.congress.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class for working with dates.
 */
public class DateUtils {

	private static Calendar cal = Calendar.getInstance();

	/**
	 * Formats the date so that the time is set to 00:00:00
	 * 
	 * @param date
	 *            - the date that needs to be formated
	 * @return formated date (dd/MM/yyyy)
	 */
	public static Date returnDateWithoutTime(Date date) {
		Date res = date;
		cal.setTime( date );
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

	    res = cal.getTime();
	    return res;
	}

	public static String getCurrentMonth(int monthIndex) {
		String monthString = new DateFormatSymbols().getMonths()[monthIndex];
		return monthString;
	}

	public static String getPrevMonth(int monthIndex) {
		if (monthIndex == 0) {
			monthIndex = 12;
		}
		return new DateFormatSymbols().getMonths()[monthIndex - 1];
	}

	public static String getNextMonth(int monthIndex) {
		if (monthIndex == 11) {
			monthIndex = -1;
		}
		return new DateFormatSymbols().getMonths()[monthIndex + 1];
	}

	public static int getDaysOfMonth(int monthIndex, int year) {
		Calendar mycal = new GregorianCalendar(year, monthIndex, 1);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return daysInMonth;
	}

	public static int getDayIndex(Date date) {
		cal.setTime(date);
		int dayIndex = cal.get(Calendar.DAY_OF_MONTH);
		return dayIndex;
	}

	public static int getMonthIndex(Date date) {
		cal.setTime(date);
		int monthIndex = cal.get(Calendar.MONTH);
		return monthIndex;
	}

	public static int getYearIndex(Date date) {
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static Date getDateByDayMonthYear(int day, int month, int year) {
		cal.set(year, month, day, 0, 0, 0);
		return cal.getTime();
	}
}
