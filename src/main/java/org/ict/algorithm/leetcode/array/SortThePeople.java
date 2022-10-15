package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * You are given an array of strings names, and an array heights that consists of distinct positive integers.
 * Both arrays are of length n.
 *
 * For each index i, names[i] and heights[i] denote the name and height of the ith person.
 *
 * Return names sorted in descending order by the people's heights.
 *
 *
 *
 * Example 1:
 *
 * Input: names = ["Mary","John","Emma"], heights = [180,165,170]
 * Output: ["Mary","Emma","John"]
 * Explanation: Mary is the tallest, followed by Emma and John.
 * Example 2:
 *
 * Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
 * Output: ["Bob","Alice","Bob"]
 * Explanation: The first Bob is the tallest, followed by Alice and the second Bob.
 *
 *
 * Constraints:
 *
 * n == names.length == heights.length
 * 1 <= n <= 103
 * 1 <= names[i].length <= 20
 * 1 <= heights[i] <= 10^5
 * names[i] consists of lower and upper case English letters.
 * All the values of heights are distinct.
 * @author sniper
 * @date 15 Oct, 2022
 * LC2418
 */
public class SortThePeople {

    public static void main(String[] args) {
        String[] names = {"Mary","John","Emma"};
        int[] heights = {180,165,170};
        sortPeople(names, heights);
    }

    public String[] sortPeopleV1(String[] names, int[] heights) {
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], i);
        }
        String[] res = new String[names.length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getValue();
            res[i] = names[k];
            i++;
        }
        return res;
    }

    /**
     * UnderStand the following Solution.
     * @param names
     * @param heights
     * @return
     */
    public static String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (o1 > o2) {
                return -1;
            } else {
                return 0;
            }
        });
        for (int i = 0; i < heights.length; i++) {
            map.put(heights[i], i);
        }
        String[] res = new String[names.length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k = entry.getValue();
            res[i] = names[k];
            i++;
        }
        return res;
    }
}
