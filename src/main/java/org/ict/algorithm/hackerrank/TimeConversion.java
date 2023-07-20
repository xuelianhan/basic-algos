package org.ict.algorithm.hackerrank;

/**
 * @see <a href="https://www.hackerrank.com/challenges/time-conversion/problem"></a>
 * @author sniper
 * @date 20 Jul 2023
 */
public class TimeConversion {

    public static String timeConversion(String s) {
        int hour = Integer.valueOf(s.substring(0, 2));
        int miniute = Integer.valueOf(s.substring(3, 5));
        int second = Integer.valueOf(s.substring(6, 8));
        String suffix = s.substring(8);

        StringBuilder res = new StringBuilder();
        if ("AM".equals(suffix)) {
            if (hour < 12) {
                res.append(s.substring(0, 8));
            } else if (hour == 12) {
                if (miniute == 0 && second == 0) {
                    res.append("00:00:00");
                } else {
                    res.append("00");
                    res.append(s.substring(2, 8));
                }
            }
        } else if ("PM".equals(suffix)) {
            if (hour < 12) {
                hour += 12;
                res.append(hour);
                res.append(s.substring(2, 8));
            } else if (hour == 12) {
                res.append(s.substring(0, 8));
            }
        }
        return res.toString();
    }
}
