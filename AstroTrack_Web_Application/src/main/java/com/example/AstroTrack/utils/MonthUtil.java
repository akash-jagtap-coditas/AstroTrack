package com.example.AstroTrack.utils;

import java.time.Month;

/**
 * Utility class for converting month names to integer values.
 */
public class MonthUtil {

    /**
     * Converts the name of a month to its corresponding integer value.
     *
     * @param monthName the name of the month
     * @return the integer value of the month
     */
    public static int convertMonthNameToInt(String monthName) {
//        monthName = monthName.substring(0, 1).toUpperCase() + monthName.substring(1).toLowerCase();
        Month month = Month.valueOf(monthName.toUpperCase());
        return month.getValue();
    }
}
