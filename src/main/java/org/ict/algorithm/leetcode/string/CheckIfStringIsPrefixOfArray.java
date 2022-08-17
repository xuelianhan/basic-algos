package org.ict.algorithm.leetcode.string;

/**
 * Given a string s and an array of strings words,
 * determine whether s is a prefix string of words.
 *
 * A string s is a prefix string of words if s can be made by concatenating
 * the first k strings in words for some positive k no larger than words.length.
 *
 * Return true if s is a prefix string of words, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
 * Output: true
 * Explanation:
 * s can be made by concatenating "i", "love", and "leetcode" together.
 * Example 2:
 *
 * Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
 * Output: false
 * Explanation:
 * It is impossible to make s using a prefix of arr.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * 1 <= s.length <= 1000
 * words[i] and s consist of only lowercase English letters.
 * @author sniper
 * @date 2022/8/17
 * LC1961
 */
public class CheckIfStringIsPrefixOfArray {

    public static void main(String[] args) {
        //String s = "iloveleetcode";
        //String[] words = {"i","love","leetcode", "apples"};

        //String s = "iloveleetcode";
        //String[] words = {"apples","i","love","leetcode"};

        /**
         * Expected false
         */
        //String s = "a";
        //String[] words = {"aa","aaaa","banana"};

        String s = "ccccccccc";
        String[] words = {"c","cc"};
        boolean result = isPrefixString(s, words);
        System.out.println(result);
    }

    public static boolean isPrefixStringV2(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(w);
            if(sb.toString().equals(s)) {
                return true;
            }
            if (sb.length() > s.length()) {
                return false;
            }
        }
        return false;
    }

    /**
     * 1.First catch is that we need to match the entire string s;
     * 2.The second catch is that we cannot stop in the middle of a word, only in between.
     * @param s
     * @param words
     * @return
     */
    public static boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if (sb.length() < s.length()) {
                continue;
            } else if (sb.length() == s.length()) {
                break;
            } else {
                return false;
            }
        }
        /**
         * words concat result length may be less than string s
         */
        if (sb.length() < s.length()) {
            return false;
        }
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) != sb.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
