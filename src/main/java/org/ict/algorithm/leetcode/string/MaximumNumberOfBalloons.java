package org.ict.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once.
 * Return the maximum number of instances that can be formed.
 *
 * Example 1:
 * Input: text = "nlaebolko"
 * Output: 1
 * Example 2:
 *
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 *
 * Input: text = "leetcode"
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 * @author sniper
 * @date 2022/8/10
 * LC1189
 */
public class MaximumNumberOfBalloons {

    public static void main(String[] args) {
        //String text = "nlaebolko";
        //String text = "loonbalxballpoon";
        String text = "leetcode";
        int result = maxNumberOfBalloons(text);
        System.out.println(result);
    }

    public int maxNumberOfBalloonsV2(String text) {
        //count all letters
        int[] chars = new int[26];
        for (char c : text.toCharArray()) {
            chars[c - 'a']++;
        }
        //for b
        int min = chars[1];
        //for a
        min = Math.min(min, chars[0]);
        // for l /2
        min = Math.min(min, chars[11] / 2);
        //similarly for o/2
        min = Math.min(min, chars[14] / 2);
        //for n
        min = Math.min(min, chars[13]);
        return min;
    }

    public static int maxNumberOfBalloons(String text) {
        String balloon = "balloon";
        Map<Character, Integer> map = new HashMap<>(26);
        for(char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int cnt = Integer.MAX_VALUE;
        for (char c : balloon.toCharArray()) {
            if (c == 'b' || c == 'a' || c == 'n') {
                if (map.getOrDefault(c, 0) < 1) {
                    cnt = 0;
                    break;
                }
                cnt = Math.min(cnt, map.get(c));
            } else if (c == 'l' || c == 'o') {
                if (map.getOrDefault(c, 0) < 2) {
                    cnt = 0;
                    break;
                }
                cnt = Math.min(cnt, map.get(c) / 2);
            }
        }
        return cnt;
    }
}
