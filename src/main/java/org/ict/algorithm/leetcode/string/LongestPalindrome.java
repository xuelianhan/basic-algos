package org.ict.algorithm.leetcode.string;

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
        //String s = "aaaccccbbaa";
        //String s = "bb";
        //String s = "AAbcccDDD";
        String s = "abcddeee";
        //String s = "abccccdd";
        //String s = "jglknendplocymmvwtoxvebkekzfdhykknufqdkntnqvgfbahsljkobhbxkvyictzkqjqydczuxjkgecdyhixdttxfqmgksrkyvopwprsgoszftuhawflzjyuyrujrxluhzjvbflxgcovilthvuihzttzithnsqbdxtafxrfrblulsakrahulwthhbjcslceewxfxtavljpimaqqlcbrdgtgjryjytgxljxtravwdlnrrauxplempnbfeusgtqzjtzshwieutxdytlrrqvyemlyzolhbkzhyfyttevqnfvmpqjngcnazmaagwihxrhmcibyfkccyrqwnzlzqeuenhwlzhbxqxerfifzncimwqsfatudjihtumrtjtggzleovihifxufvwqeimbxvzlxwcsknksogsbwwdlwulnetdysvsfkonggeedtshxqkgbhoscjgpiel";
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
        //System.out.println(Arrays.toString(arr));
        int total = 0,  odd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                total += arr[i];
            } else {
                odd++;
                /**
                 * arr[i] is an odd, so subtract one
                 */
                if (arr[i] >= 1) {
                    total += arr[i] - 1;
                }
            }
        }
        /**
         * if odd count greater than zero, need to plus one
         * e.g. cccdd, ccc
         */
        if (odd > 0) {
            total++;
        }

        return total;
    }
}
