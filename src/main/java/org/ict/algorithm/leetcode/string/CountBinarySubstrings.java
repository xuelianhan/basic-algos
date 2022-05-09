package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's,
 * and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
 * "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01"
 * that have equal number of consecutive 1's and 0's.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 * @author sniper
 * @date 2022/4/26
 * LC696
 */
public class CountBinarySubstrings {

    public static void main(String[] args) {
        String sub  = "0110001111";
        int result = countBinarySubstringsV2(sub);
        System.out.println(result);
    }

    /**
     * The following solution is provided by compton_scatter
     *
     * Maintain the current character run length and previous character run length.
     * If prevRunLength >= curRunLength, we have found a valid string.
     *
     * @param s
     * @return
     */
    public static int countBinarySubstringsV3(String s) {
        int prevRunLength = 0, curRunLength = 1, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curRunLength++;
            } else {
                prevRunLength = curRunLength;
                curRunLength = 1;
            }
            if (prevRunLength >= curRunLength) {
                res++;
            }
        }
        return res;
    }

    /**
     * The following solution is provided by lee215
     *
     * First, I count the number of 1 or 0 grouped consecutively.
     * For example "0110001111" will be [1, 2, 3, 4].
     *
     * Second, for any possible substrings with 1 and 0 grouped consecutively,
     * the number of valid substring will be the minimum number of 0 and 1.
     * For example "0001111", will be min(3, 4) = 3, ("01", "0011", "000111")
     *
     *
     * Complexity
     * Time O(N)
     * Space O(1)
     * @param s
     * @return
     */
    public static int countBinarySubstringsV2(String s) {
        int cur = 1, pre = 0, res = 0;
        for(int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                res += Math.min(pre, cur);
                pre = cur;
                cur = 1;
            }//"0110001111"
            System.out.println("pre:" + pre + ", cur:" + cur + ", res:" + res);
        }
        /**
         * Due to the res sum is before pre and cur, so in the last time when pre and cur
         * update, the res should add this one.
         */
        return res + Math.min(pre, cur);
    }


    /**
     * Time Limit Exceeded
     * @param s
     * @return
     */
    public static int countBinarySubstringsV1(String s) {
        if (s.length() == 1) {
            return 0;
        }
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return 0;
            } else {
                return 1;
            }
        }
        //length of string greater than 2
        int size = s.length() / 2;
        int mod = s.length() % 2;
        int maxStep = (mod == 0 ? s.length() : s.length() - 1);
        int total = 0;
        for (int step = 2; step <= maxStep; step += 2) {
            for (int i = 0;  i + step <= s.length(); i++) {
                // substring
                String sub = s.substring(i, i + step);
                // judge substring whether match or not
                boolean flag = subMatch(sub);
                if (flag) {
                    //System.out.println("sub:"+sub);
                    total++;
                }
            }
        }
        return total;
    }

    private static boolean subMatch(String sub) {
        int pivot = sub.length() / 2;
        int i = 0;
        int frontSum = 0;
        int backSum = 0;
        char[] arr = sub.toCharArray();
        for (i = 0; i < pivot; i++) {
            frontSum += Character.getNumericValue(arr[i]);
        }
        for (; i < sub.length(); i++) {
            backSum += Character.getNumericValue(arr[i]);
        }
        return (frontSum == 0 && backSum == pivot) || (frontSum == pivot && backSum == 0);
    }

    /**
     * Raw implementation of all substring for str array with length of n
     * @param str
     * @param n
     * @param list
     */
    public static void subString(char str[], int n, List<String> list) {
        // Pick starting point
        for (int len = 1; len <= n; len++) {
            // Pick ending point
            for (int i = 0; i <= n - len; i++) {
                //  Print characters from current
                // starting point to current ending
                // point.
                int j = i + len - 1;
                StringBuffer sb = new StringBuffer();
                for (int k = i; k <= j; k++) {
                   sb.append(str[k]);
                }
                list.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
    }
}
