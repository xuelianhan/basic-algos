package org.ict.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n a string s of lowercase letters, these letters form consecutive groups of the same character.
 *
 * For example, a string like s = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z", and "yy".
 *
 * A group is identified by an interval [start, end],
 * where start and end denote the start and end indices (inclusive) of the group.
 * In the above example, "xxxx" has the interval [3,6].
 *
 * A group is considered large if it has 3 or more characters.
 *
 * Return the intervals of every large group sorted in increasing order by start index.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbxxxxzzy"
 * Output: [[3,6]]
 * Explanation: "xxxx" is the only large group with start index 3 and end index 6.
 * Example 2:
 *
 * Input: s = "abc"
 * Output: []
 * Explanation: We have groups "a", "b", and "c", none of which are large groups.
 * Example 3:
 *
 * Input: s = "abcdddeeeeaabbbcd"
 * Output: [[3,5],[6,9],[12,14]]
 * Explanation: The large groups are "ddd", "eeee", and "bbb".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s contains lowercase English letters only.
 *
 * @author sniper
 * @date 2022/8/2
 * lc830
 */
public class PositionsOfLargeGroups {

    public static void main(String[] args) {
        String s = "aaa";
        List<List<Integer>> result = largeGroupPositions(s);
        System.out.println(result);
    }

    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        int i = 0, j = 1, step = 0;
        while (j < s.length()) {
            if (s.charAt(i) != s.charAt(j)) {
                /**
                 * process cases not consecutive such as 'abcdd'
                 */
                step = (j - i);
                if (step >= 3) {
                    List<Integer> range = new ArrayList<>();
                    range.add(i);
                    range.add(j - 1);
                    result.add(range);
                }
                i = j;
            } else {
                if (j == s.length() - 1) {
                    /**
                     * process cases consecutive like 'aaa'
                     */
                    step = (j - i) + 1;
                    if (step >= 3) {
                        List<Integer> range = new ArrayList<>();
                        range.add(i);
                        range.add(j);
                        result.add(range);
                    }
                }
            }
            j++;
        }
        return result;
    }

    public List<List<Integer>> largeGroupPositionsV2(String S) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0, j = 0; i < S.length(); i = j) {
            while (j < S.length() && S.charAt(j) == S.charAt(i)) {
                ++j;
            }
            if (j - i >= 3) {
                res.add(Arrays.asList(i, j - 1));
            }
        }
        return res;
    }
}
