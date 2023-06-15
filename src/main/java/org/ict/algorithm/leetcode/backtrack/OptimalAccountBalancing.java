package org.ict.algorithm.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of transactions
 * where transactions[i] = [from-i, to-i, amount-i] indicates that
 * the person with ID = from-i gave amount-i $ to the person with ID = to-i.
 * Return the minimum number of transactions required to settle the debt.
 *
 * Example 1:
 * Input: transactions = [[0,1,10],[2,0,5]]
 * Output: 2
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 *
 * Example 2:
 * Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * Output: 1
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * Constraints:
 * 1 <= transactions.length <= 8
 * transactions[i].length == 3
 * 0 <= from-i, to-i < 12
 * from-i != to-i
 * 1 <= amount-i <= 100
 * @author sniper
 * @date 15 Jun 2023
 * LC465, Hard, frequency=10
 */
public class OptimalAccountBalancing {
    public static void main(String[] args) {
        int[][] transactions = {{0,1,10},{2,0,5}};
        //int[][] transactions = {{0,1,10}, {1,0,1}, {1,2,5}, {2,0,5}};
        OptimalAccountBalancing instance = new OptimalAccountBalancing();
        int res = instance.minTransfersV1(transactions);
        System.out.println(res);
    }

    /**
     * Dynamic Programming Solution
     * @param transactions
     * @return
     */
    public int minTransfersV3(int[][] transactions) {
        //todo
        return 0;
    }

    /**
     * Bit Manipulation(Mask) Solution
     * @param transactions
     * @return
     */
    public int minTransfersV2(int[][] transactions) {
        //todo
        return 0;
    }

    /**
     * Understanding the following Solution
     * Backtracking Solution
     * @param transactions
     * @return
     */
    public int minTransfersV1(int[][] transactions) {
        /**
         * 0 <= from-i, to-i < 12
         */
        int[] balance = new int[12];
        for (int[] t : transactions) {
            int from = t[0];
            int to = t[1];
            int amount = t[2];
            balance[from] -= amount;
            balance[to] += amount;
        }

        List<Integer> debt = new ArrayList<>();
        for (int b : balance) {
            if (b != 0) {
                debt.add(b);
            }
        }

        int[] debtArr = debt.stream().mapToInt(Integer::intValue).toArray();
        return backtrackV1(debtArr, 0);
    }

    private int backtrackV1(int[] debtArr, int start) {
        int n = debtArr.length;
        while (start < n && debtArr[start] == 0) {
            start++;
        }
        if (start == n) {
            return 0;
        }

        int res = Integer.MAX_VALUE;
        for (int i = start + 1; i < n; i++) {
            if (debtArr[i] * debtArr[start] < 0) {
                debtArr[i] += debtArr[start];
                res = Math.min(res, 1 + backtrackV1(debtArr, start + 1));
                debtArr[i] -= debtArr[start];
            }
        }
        return res;
    }


    /**
     * Understanding the following Solution
     * Backtracking Solution
     * @param transactions
     * @return
     */
    public int minTransfers(int[][] transactions) {
        /**
         * 0 <= from-i, to-i < 12
         */
        int[] balance = new int[12];
        for (int[] t : transactions) {
            int from = t[0];
            int to = t[1];
            int amount = t[2];
            balance[from] -= amount;
            balance[to] += amount;
        }

        List<Integer> debt = new ArrayList<>();
        for (int b : balance) {
            if (b != 0) {
                debt.add(b);
            }
        }

        /**
         * The following are both OK.
         */
        int[] debtArr = debt.stream().mapToInt(i -> i).toArray();
        //int[] debtArr = debt.stream().mapToInt(Integer::intValue).toArray();
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        backtrack(debtArr, 0, 0, res);
        return res[0];
    }

    private void backtrack(int[] debtArr, int start, int cnt, int[] res) {
        int n = debtArr.length;
        while (start < n &&  debtArr[start] == 0) {
            start++;
        }
        if (start == n) {
            res[0] = Math.min(res[0], cnt);
            return;
        }
        for (int i = start + 1; i < n; i++) {
            if ((debtArr[i] < 0 && debtArr[start] > 0) || debtArr[i] > 0 && debtArr[start] < 0) {
                debtArr[i] += debtArr[start];
                backtrack(debtArr, start + 1, cnt + 1, res);
                debtArr[i] -= debtArr[start];
            }
        }
    }
}
