package org.ict.algorithm.leetcode.string;

import java.util.Arrays;

/**
 * Given a string s which consists of lowercase or uppercase letters,
 * return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case-sensitive,
 * for example, "Aa" is not considered a palindrome here.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: 1
 *
 * Example 3:
 *
 * Input: s = "bb"
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 * @author sniper
 * @date 2022/2/10
 * LC409
 */
public class LongestPalindrome {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //String s = "abccccdd";
        //String s = "abcccccdd";
        //String s = "cccdd";
        //String s = "ccc";
        //String s = "bb";
        //String s = "AAbcccDDD";
        String s = "jglknendplocymmvwtoxvebkekzfdhykknufqdkntnqvgfbahsljkobhbxkvyictzkqjqydczuxjkgecdyhixdttxfqmgksrkyvopwprsgoszftuhawflzjyuyrujrxluhzjvbflxgcovilthvuihzttzithnsqbdxtafxrfrblulsakrahulwthhbjcslceewxfxtavljpimaqqlcbrdgtgjryjytgxljxtravwdlnrrauxplempnbfeusgtqzjtzshwieutxdytlrrqvyemlyzolhbkzhyfyttevqnfvmpqjngcnazmaagwihxrhmcibyfkccyrqwnzlzqeuenhwlzhbxqxerfifzncimwqsfatudjihtumrtjtggzleovihifxufvwqeimbxvzlxwcsknksogsbwwdlwulnetdysvsfkonggeedtshxqkgbhoscjgpiel";
        //String s = "aaaccccbbaa";
        System.out.println("s length:" + s.length());
        int result = longestPalindrome(s);
        System.out.println("result:" + result);
    }

    public static int longestPalindrome(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int[] arr = new int[52];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                arr[chars[i] - 'A']++;
            } else {
                arr[chars[i] - 'a' + 26]++;
            }
        }
        System.out.println(Arrays.toString(arr));
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                total += arr[i];
            } else {
                if (arr[i] != 1) {
                    total += arr[i] - 1;
                }
            }
        }
        if (s.length() % 2 != 0) {
            total++;
        }
        return total;
    }
}
