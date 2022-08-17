package org.ict.algorithm.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an alphanumeric string s,
 * return the second largest numerical digit that appears in s, or -1 if it does not exist.
 *
 * An alphanumeric string is a string consisting of lowercase English letters and digits.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dfa12321afd"
 * Output: 2
 * Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
 * Example 2:
 *
 * Input: s = "abc1111"
 * Output: -1
 * Explanation: The digits that appear in s are [1]. There is no second largest digit.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of only lowercase English letters and/or digits.
 * @author sniper
 * @date 2022/8/17
 * LC1796
 */
public class SecondLargestDigitInString {

    public static void main(String[] args) {
        //String s = "dfa12321afd";
        String s = "abc1111";
        int result = secondHighest(s);
        System.out.println(result);
    }

    public int secondHighestV2(String s) {
        int first = -1, second = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (!Character.isDigit(ch)) {
                continue;
            }
            int d = ch - '0';
            if (first < d) {
                second = first;
                first = d;
            } else {
                if (second < d && first > d) {
                    second = d;
                }
            }
        }
        return second;
    }

    public static int secondHighest(String s) {
        Set<Integer> digitSet = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                digitSet.add(ch - '0');
            }
        }
        if (digitSet.isEmpty() || digitSet.size() < 2) {
            /**
             * return -1 if not found
             */
            return -1;
        }
        int[] arr = new int[digitSet.size()];
        int i = 0;
        for(int num : digitSet) {
            arr[i] = num;
            i++;
        }
        Arrays.sort(arr);
        /**
         * Second biggest number
         */
        return arr[arr.length - 2];
    }
}
