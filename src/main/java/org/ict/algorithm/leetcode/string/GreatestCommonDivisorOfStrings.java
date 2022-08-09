package org.ict.algorithm.leetcode.string;

/**
 * For two strings s and t,
 * we say "t divides s" if and only if s = t + ... + t
 * (i.e., t is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 *
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 *
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of English uppercase letters.
 * @author sniper
 * @date 2022/8/9
 * LC1071
 */
public class GreatestCommonDivisorOfStrings {

    public static void main(String[] args) {
        //String str1 = "ABCABC", str2 = "ABC";
        //String str1 = "ABABAB", str2 = "ABAB";
        String  str1 = "LEET", str2 = "CODE";
        String res  = gcdOfStrings(str1, str2);
        System.out.println(res);
    }

    public static String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1)) {
            return "";
        }
        int gcdVal = gcd(str1.length() , str2.length());
        return str2.substring(0, gcdVal);
    }

    public static int gcd(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    /**
     * A bit similar,
     * we need to check the terminating conditions that we can get the GCD directly.
     * Otherwise, we need to make the longer string shorter by taking off the other string from the start.
     * Then, the problem becomes a smaller problem, which can be recursively solved.
     *
     * The space complexity is O(1) constant,
     * and the time complexity is O(M/N)
     * where M is the length of the longer string
     * and N is the shorter length
     * e.g. “A”, and “AAAAAAA…”
     *
     * @see <a href="https://helloacm.com/how-to-compute-the-greatest-common-divisor-of-strings/"></a>
     * @param str1
     * @param str2
     * @return
     */
    public static String gcdOfStringsV1(String str1, String str2) {
        while (true) {
            /**
             * We also notice that if both strings do not contain each other,
             * we can rewrite it using a simpler logic
             *
             *
             */
            if (!(str1 + str2).equals(str2 + str1)) {
                return "";
            }
            if (str1.hashCode() == str2.hashCode()) {
                return str1;
            }
            if (str1.length() > str2.length()) {
                str1 = str1.substring(str2.length());
            }
            if (str2.length() > str1.length()) {
                str2 = str2.substring(str1.length());
            }
        }
    }
}
