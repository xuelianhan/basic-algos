package org.ict.algorithm.leetcode.prefixsum;


import java.util.Random;

/**
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * You need to implement the function pickIndex(),
 * which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it.
 * The probability of picking an index i is w[i] / sum(w).
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%),
 * and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * Example 1:
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 *
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
 *
 * Example 2:
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 *
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed.
 * All the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 *
 *
 * Constraints:
 *
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10^4 times.
 * @author sniper
 * @date 12 Apr, 2023
 * LC528, Medium, frequency=182.
 */
public class RandomPickWithWeightV1 {

    private int[] prefixSum;

    private Random random = new Random();

    public RandomPickWithWeightV1(int[] w) {
        int n = w.length;
        /**
         * prefixSum[0] = 0
         * prefixSum[1] = w[0]
         * prefixSum[2] = w[1] + w[0]
         * ...
         */
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + w[i];
        }
    }

    public int pickIndex() {
        int n = prefixSum.length;
        /**
         * Notice lo start from 1 not 0, because prefixSum[0] is 0
         */
        int lo = 1;
        int hi = n - 1;
        /**
         * Notice target is 1 + random.nextInt(prefixSum[n - 1]);
         * Don't forget 1 here.
         */
        int target = 1 + random.nextInt(prefixSum[n - 1]);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            /**
             * Here the codes is same as standard binary search
             */
            if (prefixSum[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        /**
         * Notice return lo - 1 instead of lo.
         */
        return lo - 1;
    }
}
