package org.ict.algorithm.leetcode.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string.
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 * Example 2:
 *
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 * Example 3:
 *
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s contains only lowercase English letters.
 * @author sniper
 * @date 12 Sep 2023
 * LC1647, Medium
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {

    /**
     * Time Cost 7ms
     * ------------------------------------
     * e.g. s = "aaabbbcc"
     * count[a]:3
     * count[b]:3
     * count[c]:2
     * ------------------------------------
     * after for-while-loop:
     * count[a]:3
     * count[b]:2
     * count[c]:1
     * ------------------------------------
     * @param s
     * @return
     */
    public int minDeletions(String s) {
        int res = 0;
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        Set<Integer> usedFreq = new HashSet<>();
        for (int freq : count) {
            while (freq > 0 && usedFreq.contains(freq)) {
                freq--;
                res++;
            }
            usedFreq.add(freq);
        }
        return res;
    }

}
