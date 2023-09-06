package org.ict.algorithm.leetcode.math;

/**
 * Given an integer num, return a string of its base 7 representation.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 100
 * Output: "202"
 * Example 2:
 *
 * Input: num = -7
 * Output: "-10"
 *
 *
 * Constraints:
 *
 * -107 <= num <= 107
 * @author sniper
 * @date 05 Sep 2023
 * Easy, 504
 */
public class Base7 {

    /**
     * Time Cost 9ms
     * @param num
     * @return
     */
    public String convertToBase7V1(int num) {
        if (num < 0)
            return "-" + convertToBase7V1(-num);
        if (num < 7)
            return String.valueOf(num);
        return convertToBase7V1(num / 7) + num % 7;
    }

    /**
     * Understanding the following solution
     * Time Cost 1ms
     * Time Complexity O(Log7N)
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if (num < 0)
            return "-" + convertToBase7(-num);
        if (num < 7)
            return String.valueOf(num);
        return convertToBase7(num / 7) + String.valueOf(num % 7);
    }
}
