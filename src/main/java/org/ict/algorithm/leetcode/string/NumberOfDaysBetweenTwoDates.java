package org.ict.algorithm.leetcode.string;

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
 * @author sniper
 * @date 2022/8/11
 * LC1360
 */
public class NumberOfDaysBetweenTwoDates {

    public static void main(String[] args) {

    }

    public int daysBetweenDates(String date1, String date2) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        return 0;
    }

    public int calculateDays(String date, int[] monthDays) {
        String[] s = date.split("-");
        int year = Integer.valueOf(s[0]);
        int month = Integer.valueOf(s[1]);
        int day = Integer.valueOf(s[2]);
        
        return 0;
    }

    public boolean isLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));
    }
}
