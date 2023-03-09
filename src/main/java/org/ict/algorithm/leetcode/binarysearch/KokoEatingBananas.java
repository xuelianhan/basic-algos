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
     * e.g.piles = [3,6,7,11], h = 8
     * k:3, piles = [3,3,3,3,3,1,3,3,3,2], the size of piles is greater than 8.
     * k:4, piles = [3,4,2,4,3,4,4,3], the size of piles is equal to 8, k:4 is the minimum number.
     * k:5, piles = [3,5,1,5,2,5,5,1], the size of piles is equal to 8, but k:5 is not the minimum number.
     * @author zhijun_liao
     * @see <a href="https://leetcode.com/problems/koko-eating-bananas/solutions/769702/python-clear-explanation-powerful-ultimate-binary-search-template-solved-many-problems/?orderBy=most_votes"></a>
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeedV3(int[] piles, int h) {
        int n = piles.length;
        Arrays.sort(piles);
        if (n == h) {
            return piles[n - 1];
        }
        //todo
        return 0;
    }


    /**
     * e.g. piles = [805306368,805306368,805306368], h = 1000000000, expected 3
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
        Arrays.sort(piles);
        if (n == h) {
            return piles[n - 1];
        }
        /**
         *  1 <= piles[i] <= 10^9
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
                 * Can eat all bananas within h hours,
                 * so we slow the eating speed k, and we assign k to hi.
                 */
                hi = k;
            } else {
                /**
                 * Koko cannot eat all bananas within h hours,
                 * so we need to speed up k, and let lo to be k + 1.
                 */
                lo = k + 1;
            }
        }
        return lo;
    }

    private boolean canEatAllV2(int[] piles, int k, int h) {
        /**
         * hours take to eat all bananas at speed k.
         */
        int countHours = 0;
        for (int pile : piles) {
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
