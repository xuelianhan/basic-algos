package org.ict.algorithm.leetcode.string;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Write a program to count the number of days between two dates.
 *
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 *
 *
 *
 * Example 1:
 *
 * Input: date1 = "2019-06-29", date2 = "2019-06-30"
 * Output: 1
 * Example 2:
 *
 * Input: date1 = "2020-01-15", date2 = "2019-12-31"
 * Output: 15
 *
 *
 * Constraints:
 *
 * The given dates are valid dates between the years 1971 and 2100.
 * @see <a href="https://www.mathsisfun.com/leap-years.html"></a>
 * @author sniper
 * @date 2022/8/11
 * LC1360
 */
public class NumberOfDaysBetweenTwoDates {

    public static void main(String[] args) {
        String date1 = "2020-01-15";
        String date2 = "2019-12-31";
        int result = daysBetweenDates(date1, date2);
        System.out.println(result);
    }

    public static int daysBetweenDates(String date1, String date2) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int d1 = calculateDays(date1, monthDays);
        int d2 = calculateDays(date2, monthDays);
        return Math.abs(d2 - d1);
    }

    public static int calculateDays(String date, int[] monthDays) {
        String[] s = date.split("-");
        int year = Integer.valueOf(s[0]);
        int month = Integer.valueOf(s[1]);
        int day = Integer.valueOf(s[2]);

        int days = year * 365 + day;
        /**
         * Add days for months in given date
         */
        for (int i = 0; i < month - 1; i++) {
            days += monthDays[i];
        }
        /**
         * Since every leap year is of 366 days,
         * Add a day for every leap year
         */
        days += countLeapYear(year, month);
        return days;
    }

    public static int countLeapYear(int year, int month) {
        int y = year;
        /**
         * Check if the current year needs to be considered
         * for the count of leap years or not
         */
        if (month <= 2) {
            y--;
        }
        /**
         * An year is a leap year if it is a multiple of 4,
         * multiple of 400 and not a multiple of 100.
         */
        return y / 4 - y / 100 + y / 400;
    }

    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }

    public int daysBetweenDatesV2(String date1, String date2) {
        return Math.abs((int) ChronoUnit.DAYS.between(LocalDate.parse(date1), LocalDate.parse(date2)));
    }
}
