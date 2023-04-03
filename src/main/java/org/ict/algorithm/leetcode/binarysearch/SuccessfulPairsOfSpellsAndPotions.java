package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * You are given two positive integer arrays spells and potions, of length n and m respectively,
 * where spells[i] represents the strength of the ith spell and potions[j] represents the strength of the jth potion.
 *
 * You are also given an integer success.
 * A spell and potion pair is considered successful if the product of their strengths is at least success.
 *
 * Return an integer array pairs of length n where pairs[i] is the number of potions that will form a successful pair with the ith spell.
 *
 *
 *
 * Example 1:
 * Input: spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * Output: [4,0,3]
 * Explanation:
 * - 0th spell: 5 * [1,2,3,4,5] = [5,10,15,20,25]. 4 pairs are successful.
 * - 1st spell: 1 * [1,2,3,4,5] = [1,2,3,4,5]. 0 pairs are successful.
 * - 2nd spell: 3 * [1,2,3,4,5] = [3,6,9,12,15]. 3 pairs are successful.
 * Thus, [4,0,3] is returned.
 *
 *
 * Example 2:
 * Input: spells = [3,1,2], potions = [8,5,8], success = 16
 * Output: [2,0,2]
 * Explanation:
 * - 0th spell: 3 * [8,5,8] = [24,15,24]. 2 pairs are successful.
 * - 1st spell: 1 * [8,5,8] = [8,5,8]. 0 pairs are successful.
 * - 2nd spell: 2 * [8,5,8] = [16,10,16]. 2 pairs are successful.
 * Thus, [2,0,2] is returned.
 *
 *
 * Constraints:
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 * @author sniper
 * @date 03 Apr, 2023
 * LC2300, Medium
 */
public class SuccessfulPairsOfSpellsAndPotions {

    public static void main(String[] args) {
        int[] spells = {3,1,2};
        int[] potions = {8,5,8};
        long success = 16;
        SuccessfulPairsOfSpellsAndPotions instance = new SuccessfulPairsOfSpellsAndPotions();
        int[] res = instance.successfulPairsV1(spells, potions, success);
        System.out.println(Arrays.toString(res));
    }


    public int[] successfulPairsV3(int[] spells, int[] potions, long success) {
        return null;
    }

    public int[] successfulPairsV2(int[] spells, int[] potions, long success) {
        return null;
    }

    /**
     * Notice that if a spell and potion pair is successful, then the spell and all stronger potions will be successful too.
     * Thus, for each spell, we need to find the potion with the least strength that will form a successful pair.
     * We can efficiently do this by sorting the potions based on strength and using binary search.
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairsV1(int[] spells, int[] potions, long success) {
        int m = spells.length;
        int n = potions.length;
        Arrays.sort(potions);
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            int index = binarySearch(potions, spells[i], success);
            if (index >= 0) {
                cnt = n - index;
            }
            res[i] = cnt;
        }
        return res;
    }

    private int binarySearch(int[] potions, long multiplier, long success) {
        int lo = 0;
        int hi = potions.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (multiplier * potions[mid] < success) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return multiplier * potions[lo] >=  success ? lo : -1;
    }


    /**
     * Brute-Force Solution
     * Time limit exceeded.
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int m = spells.length;
        int n = potions.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (spells[i] * potions[j] >= success) {
                    cnt++;
                }
            }
            res[i] = cnt;
        }
        return res;
    }
}
