package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a string s of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 *
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them.
 * You can keep doing this until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 *
 * Notice that an empty string is also good.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leEeetcode"
 * Output: "leetcode"
 * Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
 * Example 2:
 *
 * Input: s = "abBAcC"
 * Output: ""
 * Explanation: We have many possible scenarios, and all lead to the same answer. For example:
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 * Example 3:
 *
 * Input: s = "s"
 * Output: "s"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only lower and upper case English letters.
 *
 * Check the ASCII value of each character for the following conditions:
 * If the ASCII value lies in the range of [65, 90], then it is an uppercase letter.
 * If the ASCII value lies in the range of [97, 122], then it is a lowercase letter.
 * If the ASCII value lies in the range of [48, 57], then it is a number.
 * @author sniper
 * @date 13 Aug, 2022
 * LC1544
 */
public class MakeTheStringGreat {

    public static void main(String[] args) {
        String s = "leEeetcode";
        String result = makeGood(s);
        System.out.println(result);
    }

    public static String makeGood(String s) {
        if (s.length() == 1) {
            return s;
        }
        String result = "";
        List<Character> list = new ArrayList<>();
        String subStr = s;
        for (int i = 1;i < s.length();) {
            char c1 = s.charAt(i-1);
            char c2 = s.charAt(i);
            if (Character.isUpperCase(c1) && Character.isLowerCase(c2) && Character.toLowerCase(c1) == c2) {
                i += 2;
            } else if (Character.isUpperCase(c2) && Character.isLowerCase(c1) && Character.toLowerCase(c2) == c1) {
                i += 2;
            } else {
                list.add(c1);
                list.add(c2);
                i++;
            }
        }
        if (subStr.length() == list.size()) {
            subStr = list.stream().map(c -> c.toString()).collect(Collectors.joining());
            result = subStr;
        }
        return result;
    }
}
