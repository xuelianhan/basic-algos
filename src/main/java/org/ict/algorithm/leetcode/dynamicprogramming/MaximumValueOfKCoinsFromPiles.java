package org.ict.algorithm.leetcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.
 * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
 * Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom,
 * and a positive integer k,
 * return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: piles = [[1,100,3],[7,8,9]], k = 2
 * Output: 101
 * Explanation:
 * The above diagram shows the different ways we can choose k coins.
 * The maximum total we can obtain is 101.
 * Example 2:
 *
 * Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * Output: 706
 * Explanation:
 * The maximum total can be obtained if we choose all coins from the last pile.
 *
 *
 * Constraints:
 *
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 10^5
 * 1 <= k <= sum(piles[i].length) <= 2000
 * @author sniper
 * @date 16 Apr, 2023
 * LC2218, Hard
 * Apple Interview Question
 */
public class MaximumValueOfKCoinsFromPiles {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList(1,100,3));
        List<Integer> list2 = new ArrayList<>();
        list2.addAll(Arrays.asList(7,8,9));
        List<List<Integer>> piles = new ArrayList<>();
        piles.add(list1);
        piles.add(list2);
        piles.forEach(item -> {
            System.out.println("input:" + item);
        });
        int k = 2;
        MaximumValueOfKCoinsFromPiles instance = new MaximumValueOfKCoinsFromPiles();
        //instance.wrong(piles, k);
        System.out.println("=============");
        instance.maxValueOfCoins(piles, k);
    }

    /**
     * Time Cost 233ms
     * @author lee215
     * @see <a href="https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/solutions/1887010/java-c-python-top-down-dp-solution"></a>
     * @param piles
     * @param k
     * @return
     */
    public int maxValueOfCoinsV1(List<List<Integer>> piles, int k) {
        /**
         * memo[i][k] = max value of picking k coins from piles[i]
         */
        Integer[][] memo = new Integer[piles.size()][k + 1];
        return helper(piles, memo, k, 0);
    }

    private int helper(List<List<Integer>> piles, Integer[][] memo, int k,  int i) {
        if (i == piles.size() || k == 0) {
            return 0;
        }
        if (memo[i][k] != null) {
            return memo[i][k];
        }
        int res = helper(piles, memo, k, i + 1);
        int sum = 0;
        /**
         * Try to pick 1, 2, ..., k coins from current pile
         */
        for (int j = 0; j < Math.min(piles.get(i).size(), k); j++) {
            sum += piles.get(i).get(j);
            res = Math.max(res, sum + helper(piles, memo,k - j - 1,i + 1));
        }
        memo[i][k] = res;
        return res;
    }

    /**
     * Time Cost 54ms in Java
     * Time Cost 5806ms in Python
     *  def maxValueOfCoins(self, piles: List[List[int]], k: int) -> int:
     *         prefix_sum = [list(accumulate(p, initial=0)) for p in piles]
     *         dp = [0] * (k + 1)
     *         for prefix in prefix_sum:
     *             for i in range(k, -1, -1):
     *                 for j, v in enumerate(prefix):
     *                     if i >= j:
     *                         dp[i] = max(dp[i], dp[i - j] + v)
     *         return dp[-1]
     * @param piles
     * @param k
     * @return
     */
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        List<int[]> prefixSum = new ArrayList<>();
        for (List<Integer> p : piles) {
            int m = p.size();
            /**
             * A little trick here, length of prefix set to
             * m + 1 instead of m.
             * prefix[0] is zero, prefix[1] is the first element of pile.
             * Or write as following:
             * for (int i = 1; i < m + 1; i++) {
             *     prefix[i] = prefix[i - 1] + pile.get(i - 1);
             * }
             * Recommend i starts from 0, otherwise you need to be careful to deal with
             * the boundary.
             */
            int[] prefix = new int[m + 1];
            for (int i = 0; i < m; i++) {
                prefix[i + 1] = prefix[i] + p.get(i);
            }
            prefixSum.add(prefix);
        }
        /**
         * e.g. k = 2, piles.length==2, p1, p2
         * 1. choose one from p1, choose one from p2;
         * 2. choose two from p1;
         * 3. choose two from p2;
         * So the possibility times is (k + 1)
         * e.g. piles = [[1,100,3],[7,8,9]], k = 2
         * prefixSum:[[0, 1, 101, 104], [0, 7, 15, 24]]
         * prefix:[0, 1, 101, 104]
         * i:2, j:0, i >= j, dp[2]=max(dp[2], dp[2] + prefix[0])=max(0, 0 + 0)=0
         * i:2, j:1, i >= j, dp[2]=max(dp[2], dp[1] + prefix[1])=max(0, 0 + 1)=1
         * i:2, j:2, i >= j, dp[2]=max(dp[2], dp[0] + prefix[2])=max(1, 0 + 101)=101
         * i:1, j:0, i >= j, dp[1]=max(dp[1], dp[1] + prefix[0])=max(0, 0 + 0)=0
         * i:1, j:1, i >= j, dp[1]=max(dp[1], dp[0] + prefix[1])=max(0, 0 + 1)=1
         * i:0, j:0, i >= j, dp[0]=max(dp[0], dp[0] + prefix[0])=max(0, 0 + 0)=0
         * dp = [0, 1, 101]
         * prefix:[0, 7, 15, 24]
         * i:2, j:0, i >= j, dp[2]=max(dp[2], dp[2] + prefix[0])=max(101, 101 + 0)=101
         * i:2, j:1, i >= j, dp[2]=max(dp[2], dp[1] + prefix[1])=max(101, 1 + 7)=101
         * i:2, j:2, i >= j, dp[2]=max(dp[2], dp[0] + prefix[2])=max(101, 0 + 15)=101
         * i:1, j:0, i >= j, dp[1]=max(dp[1], dp[1] + prefix[0])=max(1, 1 + 0)=1
         * i:1, j:1, i >= j, dp[1]=max(dp[1], dp[0] + prefix[1])=max(1, 0 + 7)=7
         * i:0, j:0, i >= j, dp[0]=max(dp[0], dp[0] + prefix[0])=max(0, 0 + 0)=0
         * dp:[0, 7, 101]
         */
        int[] dp = new int[k + 1];
        for (int[] prefix : prefixSum) {
            for (int i = k; i >= 0; i--) {
                for (int j = 0; j < prefix.length; j++) {
                    if (i >= j) {
                        dp[i] = Math.max(dp[i], dp[i - j] + prefix[j]);
                    }
                }
            }
        }
        /**
         * return the k-th element of dp.
         */
        return dp[k];
    }


    public int wrong(List<List<Integer>> piles, int k) {
        List<int[]> prefixSum = new ArrayList<>();
        for (List<Integer> pile : piles) {
            int m = pile.size();
            // prefix length should be m+1
            int[] prefix = new int[m];
            prefix[0] = pile.get(0);
            // i upper limit should be m + 1
            for (int i = 1; i < m; i++) {
                prefix[i] = prefix[i - 1] + pile.get(i - 1);
            }
            prefixSum.add(prefix);
        }
        prefixSum.forEach(item -> {
            System.out.println(Arrays.toString(item));
        });
        int[] dp = new int[k + 1];
        for (int[] prefix : prefixSum) {
            for (int i = k; i >= 0; i--) {
                for (int j = 0; j < prefix.length; j++) {
                    if (i >= j) {
                        dp[i] = Math.max(dp[i], dp[i - j] + prefix[j]);
                        //System.out.println("i:" + i + ", j:" + j + ", dp[" + i + "]:" + dp[i]);
                    }
                }
            }
            //System.out.println("dp:" + Arrays.toString(dp));
        }
        System.out.println("dp:" + Arrays.toString(dp));
        return dp[k];
    }
}
