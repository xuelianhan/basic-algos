package org.ict.algorithm.leetcode.string;

/**
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD,
 * return the day number of the year.
 *
 *
 *
 * Example 1:
 *
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 * Example 2:
 *
 * Input: date = "2019-02-10"
 * Output: 41
 *
 *
 * Constraints:
 *
 * date.length == 10
 * date[4] == date[7] == '-', and all other date[i]'s are digits
 * date represents a calendar date between Jan 1st, 1900 and Dec 31th, 2019.
 * @author sniper
 * @date 2022/8/10
 * LC1154
 */
public class DayOfTheYear {

    public static void main(String[] args) {
        String date = "2004-03-01";
        int result = dayOfYear(date);
        System.out.println(result);
    }

    public static int dayOfYear(String date) {
        int result = 0;
        String[] arr = date.split("-");
        String mStr = arr[1];
        String dStr = arr[2];
        int y = Integer.valueOf(arr[0]);
        int m = (mStr.startsWith("0") ? Integer.valueOf(mStr.substring(1)) : Integer.valueOf(mStr.substring(0)));
        int d = Integer.valueOf(dStr);
        for (int i = 1; i < m; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10) {
                result += 31;
            } else if (i == 2) {
                /**
                 * leap year need add extra day in February
                 */
                if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
                    result += 29;
                } else {
                    result += 28;
                }
            } else {
                result += 30;
            }
        }
        result += d;
        return result;
    }
}
