package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * Koko loves to eat bananas.
 * There are n piles of bananas, the i-th pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas,
 * she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * @author sniper
 * @date 08 Mar, 2023
 * LC875, Medium
 * TikTok
 */
public class KokoEatingBananas {

    public static void main(String[] args) {
        KokoEatingBananas instance = new KokoEatingBananas();
        //int[] piles = {3,6,7,11};
        //int h = 8;
        int[] piles = {805306368,805306368,805306368};
        int h = 1000000000;
        instance.minEatingSpeedV2(piles, h);
    }

    /**
     * A rather common misunderstanding of binary search is that people often think this technique could only be used in simple scenario
     * like "Given a sorted array, find a specific value in it".
     * As a matter of fact, it can be applied to much more complicated situations.
     *
     * e.g.piles = [3,6,7,11], h = 8
     * k:3, piles = [3,3,3,3,3,1,3,3,3,2], the size of piles is greater than 8.
     * k:4, piles = [3,4,2,4,3,4,4,3], the size of piles is equal to 8, k:4 is the minimum number.
     * k:5, piles = [3,5,1,5,2,5,5,1], the size of piles is equal to 8, but k:5 is not the minimum number.
     *
     * e.g.piles = [312884470], h = 968709470
     *
     * @author zhijun_liao
     * @author GraceMeng
     * @see <a href="https://leetcode.com/problems/koko-eating-bananas/solutions/769702/python-clear-explanation-powerful-ultimate-binary-search-template-solved-many-problems/?orderBy=most_votes"></a>
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeedV3(int[] piles, int h) {
        /**
         * Notice here l start from 1, not zero.
         * because 1 <= piles[i] <= 10^9
         */
        int lo = 1;
        int hi = getMaxPileV1(piles);
        /**
         * Deal with case e.g. piles = [2,2], h=2
         */
        if (hi == piles.length) {
            return hi;
        }
        while (lo < hi) {
            int speed = lo + (hi - lo) / 2;
            if (canEatAllV2(piles, speed, h)) {
                /**
                 * If Koko can eat all piles of banana with speed in h hours,
                 * she can slow down her eating speed.
                 */
                hi = speed;
            } else {
                lo = speed + 1;
            }
        }
        return lo;
    }

    private int getMaxPileV1(int[] piles) {
        return Arrays.stream(piles).max().getAsInt();
    }

    private int getMaxPile(int[] piles) {
        int maxPile = Integer.MIN_VALUE;
        for (int pile : piles) {
            maxPile = Math.max(pile, maxPile);
        }
        return maxPile;
    }


    /**
     * e.g. piles = [805306368,805306368,805306368], h = 1000000000, expected 3
     * e.g. piles = [2, 2], h = 2, expected 2
     *
     * Each hour, Koko chooses some pile of bananas, and eats K bananas from that pile.
     * Due to (1 <= piles[i] <= 10^9), K is limited to range:[lo, hi], lo:1, hi:max(piles[i]),
     * Or setting 10^9 to hi brute-force.
     * We need to find K that satisfied the following conditions:
     *   1.For any K, K can enable Koko to eat all the bananas within h hours.
     * Let's mark total sum as totalSum,
     * for each pile[i] in piles array:
     *     totalSum = totalSum + Math.ceil(pile[i]/K);
     * totalSum <= h;
     *   2.In range from lo to hi, we need to find the minimum K' from all the K-set.
     *
     * @author GraceMeng
     * @see <a href="https://leetcode.com/problems/koko-eating-bananas/solutions/152506/binary-search-java-python-with-explanations/?orderBy=most_votes"></a>
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeedV2(int[] piles, int h) {
        int n = piles.length;
        /**
         * We need to use the maximum value later, so sorting at here.
         * Actually, we don't need the piles sorted, we only need its maximum.
         * So the following lines can be replaced with getMaxPile method.
         * Please see the details in minEatingSpeedV3.
         */
        Arrays.sort(piles);
        /**
         * Deal with case:
         * e.g piles = [2, 2], h = 2
         */
        if (n == h) {
            return piles[n - 1];
        }
        /**
         * 1 <= piles[i] <= 10^9
         */
        int lo = 1;
        int hi = piles[n - 1];
        /**
         * Notice here:
         * 1.lo < hi, not (lo <= hi).
         * 2.countHours <= h, then (hi = k), not (hi = k - 1)
         * 3.((hi - lo) >> 1), don't forget the outer-brace!
         */
        while (lo < hi) {
            int k = lo + ((hi - lo) >> 1);
            if (canEatAllV2(piles, k, h)) {
                /**
                 * Koko can eat all bananas within h hours,
                 * so we slow the eating speed k, that is why we assign k to hi.
                 */
                hi = k;
            } else {
                /**
                 * Koko cannot eat all bananas within h hours,
                 * so we need to speed up k, and we let lo to be k + 1.
                 */
                lo = k + 1;
            }
        }
        return lo;
    }

    /**
     * Whether Koko can eat all bananas within h hours at speed k of each hour.
     * @param piles
     * @param k
     * @param h
     * @return
     */
    private boolean canEatAllV2(int[] piles, int k, int h) {
        /**
         * hours take to eat all bananas at speed k.
         */
        int countHours = 0;
        for (int pile : piles) {
            // The effect is same as countHours += Math.ceil(pile * 1.0 / k) but more fast.
            countHours += (pile + k - 1) / k;
        }
        return countHours <= h;
    }

    private boolean canEatAllV1(int[] piles, int k, int h) {
        /**
         * hours take to eat all bananas at speed k.
         */
        int countHours = 0;
        for (int pile : piles) {
            countHours += Math.ceil(pile * 1.0 / k);
        }
        return countHours <= h;
    }

    /**
     * Whether Koko can eat all bananas within h hours at speed k of each hour.
     * e.g. piles = [805306368,805306368,805306368], h = 1000000000
     * @param piles
     * @param k
     * @param h
     * @return
     */
    private boolean canEatAll(int[] piles, int k, int h) {
        /**
         * hours take to eat all bananas at speed k.
         */
        int countHours = 0;
        for (int pile : piles) {
            countHours += pile / k;
            if (pile % k != 0) {
                countHours++;
            }
        }
        return countHours <= h;
    }


    /**
     * Binary search between [1, 10^9] or [1, max(piles)] to find the result.
     * Time complexity: O(NlogM)
     *
     * (p + m - 1) / m equal to ceil(p / m) (just personal behavior)
     *
     * Here you find another similar problem.
     * {@link MinimizeMaxDistanceToGasStation}
     *
     * e.g.
     * piles = [312884470], h = 968709470
     *
     * @author lee215
     * @see <a href="https://leetcode.com/problems/koko-eating-bananas/solutions/152324/java-c-python-binary-search/?orderBy=most_votes"></a>
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeedV1(int[] piles, int h) {
        /**
         * 1 <= piles[i] <= 10^9
         * So left start from 1 other than zero.
         * If left starts with zero, so the statement
         * (pile + mid - 1) / lead to java.lang.ArithmeticException: / by zero.
         */
        int left = 1;
        int right = 1000000000;
        while (left < right) {
            int mid = (left + right) / 2;
            int len = 0;
            for (int pile : piles) {
                /**
                 * Notice here using pile * 1.0 instead of pile itself,
                 * because ceil need double parameter.
                 * If you miss the 1.0, divide operator would lose the accuracy and less than or equal to int.
                 * Math.ceil: greater than or equal to
                 * Math.floor: less than or equal to
                 */
                len += (pile + mid - 1) / mid;
            }
            if (len > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * Binary search between [1, 10^9] or [1, max(piles)] to find the result.
     * Time complexity: O(NlogM)
     * e.g. piles = [3,6,7,11], h = 8, expected 4
     *
     * @author lee215
     * @see <a href="https://leetcode.com/problems/koko-eating-bananas/solutions/152324/java-c-python-binary-search/?orderBy=most_votes"></a>
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        /**
         * 1 <= piles[i] <= 10^9
         * So left start from 1 other than zero.
         */
        int left = 1;
        int right = 1000000000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int len = 0;
            for (int pile : piles) {
                /**
                 * Notice here using pile * 1.0 instead of pile itself,
                 * because ceil need double parameter.
                 * If you miss the 1.0, divide operator would lose the accuracy and less than or equal to int.
                 * Math.ceil: greater than or equal to
                 * Math.floor: less than or equal to
                 */
                len += Math.ceil(pile * 1.0 / mid);
            }
            if (len > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
