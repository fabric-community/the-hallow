package com.fabriccommunity.spookytime.util;

import com.google.common.collect.ImmutableList;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Provides utilities to obtain the amount of time between current date and Spooktober
 */
public class TimeUtil {
	
	private static final ImmutableList<Integer> NEXT_YEAR_MONTHS = ImmutableList.of(11, 12);
	
	private TimeUtil() {
	}
	
	/**
	 * Gets the amount of days left from the current time till Spooktober (31/10/YYYY)
	 *
	 * @return The amount of days left till Spooktober.
	 */
	public static long daysTillSpooktober() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int yearSelector;
		
		if (NEXT_YEAR_MONTHS.contains(calendar.get(Calendar.MONTH))) {
			yearSelector = calendar.get(Calendar.YEAR) + 1;
		} else {
			yearSelector = calendar.get(Calendar.YEAR);
		}
		
		LocalDate now = LocalDate.now();
		
		LocalDate nextSpook = LocalDate.of(yearSelector, Month.OCTOBER, 31);
		
		return now.until(nextSpook).getDays();
	}
}
