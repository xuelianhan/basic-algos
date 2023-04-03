package org.ict.algorithm.leetcode.dynamicprogramming;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
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
 * Given a string s containing only digits, return the number of ways to decode it.
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 * Constraints:
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
        int res = instance.numDecoding(s);
        System.out.println(res);
    }

    /**
     * Dynamic programming Solution with Constant Space
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     * Since our dp only need to keep up to 3 following states:
     * 1.Current state, let name dp corresponding to dp[i];
     * 2.Last state, let name dp1 corresponding to dp[i+1];
     * 3.Last twice state, let name dp2 corresponding to dp[i+2];
     *
     * @param s
     * @return
     */
    public int numDecodingV6(String s) {
        int dp1 = 1;
        int dp2 = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            int dp = (s.charAt(i) == '0' ? 0 : dp1);
            if (i < (n - 1) && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                dp += dp2;
            }
            dp2 = dp1;
            dp1 = dp;
        }
        return dp1;
    }

    /**
     * Understanding the following solution.
     *
     * Top-Down Dynamic programming solution
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
    public int numDecodingV5(String s) {
        /**
         * 1 <= s.length <= 100, so the following check is not necessary.
         */
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

    /**
     * Bottom-up Dynamic programming Solution
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param s
     * @return
     */
    public int numDecodingsV4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
            }
            if ((i < (n - 1)) && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }


    /**
     * Bottom-up Dynamic programming Solution
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param s
     * @return
     */
    public int numDecodingV3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
                if ((i < (n - 1)) && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    /**
     * Bottom-up Dynamic programming Solution
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param s
     * @return
     */
    public int numDecodingV2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            dp[i] = dp[i + 1];
            if ((i < (n - 1)) && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    /**
     * Recursion + Memoization Solution
     * A char may be decoded alone or by pairing with the next char.
     * Time Cost 0ms
     *
     * @author Shuming leetcode-id:yu6
     * @see <a href="https://leetcode.com/problems/decode-ways/solutions/30451/evolve-from-recursion-to-dp"></a>
     * @param s
     * @return
     */
    public int numDecodingV1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        /**
         * If you use int[] memo here, you need to initialize memo with -1:
         * Arrays.fill(memo, -1);
         * And then change (memo[p] != null) to (memo[p] != -1)
         */
        Integer[] memo = new Integer[s.length()];
        return numDecoding(0, s, memo);
    }

    private int numDecoding(int p, String s, Integer[] memo) {
        int n = s.length();
        if (p == n) {
            return 1;
        }
        if (s.charAt(p) == '0') {
            return 0;
        }
        if (memo[p] != null) {
            return memo[p];
        }
        int res = numDecoding(p + 1, s, memo);
        if ((p < (n - 1)) && (s.charAt(p) == '1' || s.charAt(p) == '2' && s.charAt(p + 1) < '7')) {
            res += numDecoding(p + 2, s, memo);
        }
        memo[p] = res;
        return res;
    }

    /**
     * Recursion Solution without memo
     * Time Complexity O(2^N)
     * e.g. s = "111111111111111111111111111111111111111111111",
     * Time Limit Exceeded.
     *
     * A char may be decoded alone or by pairing with the next char.
     * @author Shuming leetcode-id:yu6
     * @see <a href="https://leetcode.com/problems/decode-ways/solutions/30451/evolve-from-recursion-to-dp"></a>
     * @param s
     * @return
     */

    public int numDecoding(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return numDecoding(0, s);
    }

    /**
     * e.g. s = "11106"
     *                    11106
     *                    1/ \11
     *                  1106  106
     *                 1/       \10
     *                106        6
     *             10/
     *             6
     *  There are two effective paths in total:
     *  1-1-10-6
     *  11-10-6
     *
     *  e.g. s = "226"
     *                  226
     *                2/  \22
     *               26    6
     *             2/ \26
     *             6   ""
     *  There are three effective paths in total:
     *  2-2-6
     *  2-26
     *  22-6
     *
     * @author Shuming leetcode-id:yu6
     * @see <a href="https://leetcode.com/problems/decode-ways/solutions/30451/evolve-from-recursion-to-dp"></a>
     * @see <a href="https://leetcode.com/problems/decode-ways/solutions/608268/python-thinking-process-diagram-dp-dfs"></a>
     * @param p
     * @param s
     * @return
     */
    private int numDecoding(int p, String s) {
        int n = s.length();
        /**
         * Condition (p == n) must be placed before (s.charAt(p) == '0') to prevent index of bound error.
         * Why p == n, we need to return 1? not return 0?
         * p == n means this path has come to the end, end means this path can be counted as one valid choice.
         */
        if (p == n) {
            return 1;
        }
        if (s.charAt(p) == '0') {
            /**
             * Cut off subtrees start with "0", because this path is invalid, so return 0 directly.
             */
            return 0;
        }
        /**
         * Choose one more digit after p
         */
        int res = numDecoding(p + 1, s);
        /**
         * Choose two more digits after p.
         * The first digit can only be 1 or 2, because "26" is the legal maximum
         * If you choose first digit to ones greater than 2, such as 3, 4, 5..., then they must be greater than 26.
         * If first digit has chosen 2, then the second digit cannot be greater than 6, that's the reason of condition
         * s.charAt(p) == '2' && s.charAt(p + 1) < '7')
         */
        if (p < (n - 1) && (s.charAt(p) == '1' || s.charAt(p) == '2' && s.charAt(p + 1) < '7')) {
            res += numDecoding(p + 2, s);
        }
        return res;
    }
}
