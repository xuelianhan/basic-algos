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
 *
 * Actually I was asked this question during the interview of Amazon (intern) last year.
 * I have no idea of the Date API and I tried to solve the problem with map at that time.
 * I ran out of time and missed the opportunity in the end.
 * If I was asked a hard dynamic programming question and I couldn't solve it, I would accept.
 * But this question? I felt some kind of unfair and really disappointed.
 *
 * @see <a href="https://www.mathsisfun.com/leap-years.html"></a>
 *
 * All the leap years since 1900 are as follows:
 * 1904, 1908, 1912, 1916, 1920, 1924, 1928, 1932, 1936, 1940,
 * 1944, 1948, 1952, 1956, 1960, 1964, 1968, 1972, 1976, 1980,
 * 1984, 1988, 1992, 1996, 2000, 2004, 2008, 2012, 2016, 2020
 * 2024, 2028, 2032, 2036, 2040, 2044, 2048, 2052, 2056, 2060,
 * 2064, 2068, 2072, 2076, 2080, 2084, 2088, 2092, 2096.
 *
 * In 2060, I may not be in this world anymore。
 * I marked this codes to memorize my loved ones,
 * my wife HAIZHEN NI, and my child JINYU HAN.
 * If you see here, thanks for your patience.
 * @author sniper
 * @date 2022/8/11
 * LC1360
 */
public class NumberOfDaysBetweenTwoDates {

    public static void main(String[] args) {
        //String date1 = "2020-01-15";
        //String date2 = "2019-12-31";
        //int result = daysBetweenDates(date1, date2);
        int cnt = countLeapYear(2020, 12);
        System.out.println(cnt);
    }

    /**
     * A Better and Simple solution is to count total number of days before dt1 from i.e.,
     * total days from 00/00/0000 to dt1,
     * then count total number of days before dt2.
     * Finally return the difference between two counts.
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetweenDates(String date1, String date2) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int d1 = calculateDays(date1, monthDays);
        int d2 = calculateDays(date2, monthDays);
        return Math.abs(d2 - d1);
    }

    /**
     * Count number of days before date from 0000-00-00.
     * @param date
     * @param monthDays
     * @return
     */
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
         * Add a day for every leap year,
         * So we need to count the leap years.
         */
        days += countLeapYear(year, month);
        return days;
    }

    /**
     * Every leap year adds one extra day (29 Feb) to total days.
     * n1 = 2000*365 + 31 + 1 + Number of leap years
     * Count of leap years for a date 'd/m/y' can be calculated
     * using the following formula:
     * Number leap years
     *  = floor(y/4) - floor(y/100) + floor(y/400) if m > 2
     *   = floor((y-1)/4) - floor((y-1)/100) + floor((y-1)/400) if m <= 2
     * All above divisions must be done using integer arithmetic
     * So that the remainder is ignored.
     * For 01/01/2000, leap year count is 1999/4 - 1999/100
     * + 1999/400 which is 499 - 19 + 4 = 484
     * Therefore n1 is 2000*365 + 31 + 1 + 484
     *
     * @param year
     * @param month
     * @return
     */
    public static int countLeapYear(int year, int month) {
        int y = year;
        /**
         * Check if the current year needs to be considered
         * for the count of leap years or not
         * If the current year not reach to Feb,
         * then we need to subtract one
         * cnt = floor((y-1)/4) - floor((y-1)/100) + floor((y-1)/400) if m <= 2
         */
        if (month <= 2) {
            y--;
        }
        /**
         * An year is a leap year if it is a multiple of 4,
         * multiple of 400 and not a multiple of 100.
         * And the average number of days per year is
         * 365 + 1⁄4 − 1⁄100 + 1⁄400 = 365 + 97⁄400  = 365.2425.
         *
         * Number leap years: cnt
         * cnt = floor(y/4) - floor(y/100) + floor(y/400) if m > 2
         * cnt = floor((y-1)/4) - floor((y-1)/100) + floor((y-1)/400) if m <= 2
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
