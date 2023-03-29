package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message,
 * all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).
 * For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 * @author sniper
 * @date 29 Mar, 2023
 * LC91, Medium
 * Cisco, Lyft
 */
public class DecodeWays {

    public static void main(String[] args) {
        String s = "11106";
        DecodeWays instance = new DecodeWays();
        int res = instance.numDecodings(s);
        System.out.println(res);
    }

    public int numDecodingsV5(String s) {
        int res = 0;
        //todo
        return res;
    }

    public int numDecodingsV4(String s) {
        int res = 0;
        //todo
        return res;
    }

    public int numDecodingsV3(String s) {
        int res = 0;
        //todo
        return res;
    }

    public int numDecodingsV2(String s) {
        int res = 0;
        //todo
        return res;
    }

    /**
     * @author Shuming leetcode-id:yu6
     * @see <a href="https://leetcode.com/problems/decode-ways/solutions/30451/evolve-from-recursion-to-dp"></a>
     * @param s
     * @return
     */
    public int numDecodingsV1(String s) {
        int res = 0;
        //todo
        return res;
    }


    /**
     * Understanding the following solution.
     *
     * dp[i] means the way to decode a string of the first-i characters with size-i
     * I used a dp array of size n + 1 to save sub-problem solutions.
     * dp[0] means an empty string will have one way to decode,
     * dp[1] means the way to decode a string of size 1.
     * I then check one digit and two digit combination and save the results along the way.
     * In the end, dp[n] will be the end result.
     * @author yfcheng
     * @see <a href="https://leetcode.com/problems/decode-ways/solutions/30358/java-clean-dp-solution-with-explanation"></a>
     *
     * e.g. s:"11106"
     * dp[0]:1, dp[1]:1
     *
     * i:2, substring:"11",   first:1, second:11, first in [1,9], dp[2]=0+dp[1]=1, second in [10,26], dp[2]=1+dp[0]=2, it means "1,1", "11".
     * i:3, substring:"111",  first:1, second:11, first in [1,9], dp[3]=0+dp[2]=2, second in [10,26], dp[3]=2+dp[1]=3, it means "1,1,1", "11,1", "1,11".
     * i:4, substring:"1110", first:0, second:10, first not in [1,9], second in [10,26], dp[4]=0+dp[2]=2, it means "11,10", "1,1,10".
     * i:5, substring:"11106",first:6, second:06, first in [1,9], second not in [10,26], dp[5]=0+dp[4]=2, it means "11,10,6", "1,1,10,6"
     * for-loop-end, return dp[5]:2
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.startsWith("0")) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int first =  Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >=10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
