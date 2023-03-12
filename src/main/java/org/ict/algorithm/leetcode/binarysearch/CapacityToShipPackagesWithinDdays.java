package org.ict.algorithm.leetcode.binarysearch;

import java.util.Arrays;

/**
 * A conveyor belt has packages that must be shipped from one port to another within days days.
 *
 * The ith package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 *
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 *
 *
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts
 * like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 * Example 2:
 *
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 * Example 3:
 *
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 *
 *
 * Constraints:
 *
 * 1 <= days <= weights.length <= 5 * 10^4
 * 1 <= weights[i] <= 500
 * @author sniper
 * @date 12 Mar, 2023
 * LC1011, Medium
 * This problem is similar with {@link KokoEatingBananas}
 */
public class CapacityToShipPackagesWithinDdays {

    public static void main(String[] args) {
        //int[] weights = {1,2,3,4,5,6,7,8,9,10};
        //int days = 5;

        int[] weights = {1,2,3,1,1};
        int days = 4;
        CapacityToShipPackagesWithinDdays instance = new CapacityToShipPackagesWithinDdays();
        int res = instance.shipWithinDays(weights, days);
        //instance.canShipAll(weights, 14, 5);
        System.out.println(res);
    }

    public int shipWithinDaysV1(int[] weights, int days) {
        //todo
        return 0;
    }

    public int shipWithinDays(int[] weights, int days) {
        int lo = Arrays.stream(weights).max().getAsInt();
        int hi = Arrays.stream(weights).sum();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canShipAll(weights, mid, days)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean canShipAll(int[] weights, int k, int days) {
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > k) {
                cnt++;
                sum = weights[i];
            } else if (sum == k) {
                cnt++;
                sum = 0;
            }
        }
        if (sum > 0) {
            cnt++;
        }
        return cnt <= days;
    }
}
