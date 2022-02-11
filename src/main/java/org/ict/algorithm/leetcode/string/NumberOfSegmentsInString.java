package org.ict.algorithm.leetcode.string;

/**
 * Given a string s, return the number of segments in the string.
 *
 * A segment is defined to be a contiguous sequence of non-space characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Hello, my name is John"
 * Output: 5
 * Explanation: The five segments are ["Hello,", "my", "name", "is", "John"]
 * Example 2:
 *
 * Input: s = "Hello"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 300
 * s consists of lowercase and uppercase English letters, digits,
 * or one of the following characters "!@#$%^&*()_+-=',.:".
 * The only space character in s is ' '.
 * @author sniper
 * @date 2022/2/11
 * LC434
 */
public class NumberOfSegmentsInString {

    public static void main(String[] args) {
        String s = "    foo    bar";
        int result = countSegments(s);
        System.out.println(result);
    }

    /**
     * continuous space counts + 1
     * count: ", space + f, space + b
     * @param s
     * @return
     */
    public static int countSegments(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int total = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != ' ' && (i == 0 || arr[i-1] == ' ')) {
                total++;
            }
        }
        return total;
    }

}
