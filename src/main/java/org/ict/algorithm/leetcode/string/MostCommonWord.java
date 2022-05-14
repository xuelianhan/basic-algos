package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a string paragraph and a string array of the banned words banned,
 * return the most frequent word that is not banned.
 * It is guaranteed there is at least one word that is not banned,
 * and that the answer is unique.
 *
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 *
 *
 *
 * Example 1:
 *
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 * Example 2:
 *
 * Input: paragraph = "a.", banned = []
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= paragraph.length <= 1000
 * paragraph consists of English letters, space ' ', or one of the symbols: "!?',;.".
 * 0 <= banned.length <= 100
 * 1 <= banned[i].length <= 10
 * banned[i] consists of only lowercase English letters.
 * @author sniper
 * @date 14 May, 2022
 * LC819
 */
public class MostCommonWord {

    public static void main(String[] args) {
        //String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        //String[] banned = {"hit"};
        //String paragraph = "a.";
        //String[] banned = {};
        String paragraph = "Bob";
        String[] banned = {};
        String result = mostCommonWord(paragraph, banned);
        System.out.println(result);
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        StringBuffer sb = new StringBuffer();
        Map<String, Integer> cntMap = new HashMap<>();
        String result = "";
        Integer max = 0;
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            }
            String s = null;
            if (c == ' ' || c == ','  || c == '!' || c == '?' || c == ';' || c == '.' || c == '\'') {
                s = sb.toString().toLowerCase();
                sb = sb.delete(0, sb.length());
            } else {
                s = sb.toString().toLowerCase();
            }

            if (s == null || s.length() == 0) {
                continue;
            }

            if (null != banned && banned.length > 0) {
                Set<String> banSet = Stream.of(banned).collect(Collectors.toSet());
                if (banSet.contains(s)) {
                    continue;
                }
            }

            Integer cnt = cntMap.getOrDefault(s, 0);
            cntMap.put(s, cnt + 1);
            if (cnt + 1 > max) {
                max = cnt + 1;
                result = s;
            }
        }
        return result;
    }
}
