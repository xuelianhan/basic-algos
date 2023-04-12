package org.ict.algorithm.leetcode.randomized;

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
 * LC528, Medium
 */
public class RandomPickWithWeight {

    private int[] prefixSum;

    private Random random = new Random();


    public RandomPickWithWeight(int[] w) {
        int n = w.length;
        prefixSum = new int[n];
        prefixSum[0] = w[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
    }

    /**
     * The rand() function is random with equal probability,
     * how can we have a weighted random?
     * We can use a trick, since the weights are 1 and 3, add up to 4,
     * so we now assume that there are 4 points,
     * and then randomly take a point with equal probability,
     * after randomly to the first point to represent the original first point,
     * randomly to the last three points to represent the original second point,
     * so that we can guarantee a weighted random
     *
     * e.g. w = [1, 3],
     * our target is as following:
     * 1.let the probability of picking index-0(value-1) is 1/(1+3);
     * 2.let the probability of picking index-1(value-3) is 3/(1+3);
     * prefixSum = [1, 4], prefixSum[1]=4
     * random.nextInt(4) generates uniformly distributed
     * value between zero (inclusive) and 4 (exclusive), they are 0,1,2,3
     * So target may be anyone of 0,1,2,3
     * if target=0, the first number greater than 0 in prefixSum array is 1, it's index is 0
     * if target=1, the first number greater than 1 in prefixSum array is 4, it's index is 2
     * if target=2, the first number greater than 2 in prefixSum array is 4, it's index is 2
     * if target=3, the first number greater than 3 in prefixSum array is 4, it's index is 2
     * Because nextInt method is equal probability.
     * So the index-0 probability is 1/4, the index-2 probability is 3/4.
     *
     * e.g. w = [1, 3, 2]
     * our target is as following:
     * 1.let the probability of picking index-0(value-1) is 1/(1+3+2);
     * 2.let the probability of picking index-1(value-3) is 3/(1+3+2);
     * 2.let the probability of picking index-2(value-2) is 2/(1+3+2);
     *
     * prefixSum = [1, 4, 6], prefixSum[2]=6
     * random.nextInt(6) generates uniformly distributed
     * value between zero (inclusive) and 6 (exclusive), they are 0,1,2,3,4,5
     * So target may be anyone of 0,1,2,3,4,5
     * if target=0, the first number greater than 0 in prefixSum array is 1, it's index is 0
     * if target=1, the first number greater than 1 in prefixSum array is 4, it's index is 1
     * if target=2, the first number greater than 2 in prefixSum array is 4, it's index is 1
     * if target=3, the first number greater than 3 in prefixSum array is 4, it's index is 1
     * if target=4, the first number greater than 4 in prefixSum array is 6, it's index is 2
     * if target=5, the first number greater than 5 in prefixSum array is 6, it's index is 2
     * Because nextInt method is equal probability.
     * So the index-0 probability is 1/6,
     *    the index-1 probability is 3/6.
     *    the index-2 probability is 2/6.
     *
     * @return
     */
    public int pickIndex() {
        int n = prefixSum.length;
        int lo = 0;
        int hi = n;
        int target = random.nextInt(prefixSum[n - 1]);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prefixSum[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

}
