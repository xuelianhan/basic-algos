package org.ict.algorithm.leetcode.string;

/**
 * @author sniper
 * @date 2022/8/17
 * LC151
 */
public class ReverseWordsInString {

    /**
     * Follow-up:
     * If the string data type is mutable in your language,
     * can you solve it in-place with O(1) extra space?
     * @param s
     * @return
     */

    public String reverseWordsV2(String s) {
        StringBuilder sb = new StringBuilder(s.trim());
        //todo
        return null;
    }

    public String reverseWords(String s) {
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
