package org.ict.algorithm.leetcode.string;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a date string in the form Day Month Year, where:
 *
 * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
 * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
 * Year is in the range [1900, 2100].
 * Convert the date string to the format YYYY-MM-DD, where:
 *
 * YYYY denotes the 4 digit year.
 * MM denotes the 2 digit month.
 * DD denotes the 2 digit day.
 *
 *
 * Example 1:
 *
 * Input: date = "20th Oct 2052"
 * Output: "2052-10-20"
 * Example 2:
 *
 * Input: date = "6th Jun 1933"
 * Output: "1933-06-06"
 * Example 3:
 *
 * Input: date = "26th May 1960"
 * Output: "1960-05-26"
 *
 *
 * Constraints:
 *
 * The given dates are guaranteed to be valid, so no error handling is necessary.
 * @author sniper
 * @date 13 Aug, 2022
 * LC1507
 */
public class ReformatDate {

    public String reformatDate(String date) {
        Map<String, String> month = new HashMap<>();
        month.put("Jan", "01");
        month.put("Feb", "02");
        month.put("Mar", "03");
        month.put("Apr", "04");
        month.put("May", "05");
        month.put("Jun", "06");
        month.put("Jul", "07");
        month.put("Aug", "08");
        month.put("Sep", "09");
        month.put("Oct", "10");
        month.put("Nov", "11");
        month.put("Dec", "12");
        StringBuilder sb = new StringBuilder();
        String[] arr = date.split("\\s");
        String d = arr[0];
        String m = arr[1];
        String y = arr[2];
        sb.append(y);
        sb.append("-");
        sb.append(month.get(m));
        sb.append("-");
        if (d.length() == 4) {
            sb.append(d.substring(0, 2));
        } else if (d.length() == 3) {
            sb.append("0"+ d.substring(0, 1));
        }
        return sb.toString();
    }

}
