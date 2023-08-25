package org.ict.algorithm.leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * You are given an integer array deck where deck[i] represents the number written on the ith card.
 *
 * Partition the cards into one or more groups such that:
 *
 * Each group has exactly x cards where x > 1, and
 * All the cards in one group have the same integer written on them.
 * Return true if such partition is possible, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * Example 2:
 *
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false
 * Explanation: No possible partition.
 *
 *
 * Constraints:
 *
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 * @author sniper
 * @date 25 Aug 2023
 * LC914, Easy
 */
public class XofKindInDeckOfCards {



    /**
     * e.g. deck = [1], expected false.
     * e.g. deck = [1,1,2,2,2,2], expected true.
     * e.g. deck = [1,1,1,1,2,2,2,2,2,2], expected true.
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 1) {
            return false;
        }

        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int i : deck) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        if (freq.firstEntry().getValue() == 1) {
            return false;
        }

        int maxCommonDivisor = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            maxCommonDivisor = gcd(entry.getValue(), maxCommonDivisor);
        }
        return maxCommonDivisor > 1;
    }

    private int gcd(int a, int b) {
        int temp = 0;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
