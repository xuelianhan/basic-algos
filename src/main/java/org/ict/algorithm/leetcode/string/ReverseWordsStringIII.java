package org.ict.algorithm.leetcode.string;

/**
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Example 2:
 *
 * Input: s = "God Ding"
 * Output: "doG gniD"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 10^4
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 * @author sniper
 * @date 17 Mar, 2022
 * LC557
 */
public class ReverseWordsStringIII {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        String result = reverseWords(s);
        System.out.println(result);
    }

    public static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = 0;
        for (; i < arr.length && j < arr.length;) {
            if (arr[j] != ' '){
                j++;
            } else {
                swap(arr, i, j - 1);
                i = j + 1;
                j++;
            }
        }
        return String.valueOf(arr);
    }

    private static void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}
