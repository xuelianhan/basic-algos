package org.ict.algorithm.leetcode.binarysearch;

/**
 * On a horizontal number line,
 * we have gas stations at positions stations[0], stations[1], ..., stations[N-1],
 * where N = stations.length.
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 * Return the smallest possible value of D.
 * Example:
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 *
 * @author sniper
 * @date 08 Mar, 2023
 * LC774, Hard
 */
public class MinimizeMaxDistanceToGasStation {

    public static void main(String[] args) {
        //int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        //int k = 9;

        /**
         * The distance between two stations marked as D,
         * D(1,2):1
         * D(2,5):3
         * The maximum D is 3
         * k:1, so we put the new station at the half point between 2 and 5,
         * then the gas stations develop like this:
         * 1_2__P__5
         * D(1,2):1
         * D(2,P):1.5
         * D(P,5):1.5
         * The maximum D change from 3 to 1.5
         */
        int[] stations = {1, 2, 5};
        int k = 1;

        MinimizeMaxDistanceToGasStation instance = new MinimizeMaxDistanceToGasStation();
        double res = instance.minMaxGasDist(stations, k);
        System.out.println(res);
    }


    /**
     * What do we want?
     * Return the smallest possible value of the maximum distance between adjacent gas stations after inserting k stations.
     *
     * Please note that the new k station may add at any position between current stations
     * e.g. stations = [10, 19, 25, 27, 56, 63, 70, 87, 96, 97], k = 3
     * The distance array: [9, 6, 2, 29, 7, 7, 17, 9, 1]
     * We can put partition point at any gap of stations, let's take an example:
     * [10, 19, 25, 27-p1-p2-56, 63, 70-p3-87, 96, 97]
     * We can use two-points p1, p2 to split 27-56 into three parts with equal length,
     * then we use one-point p3 to split 70-87 into two halves.
     *
     * Sort stations and initialize low to 0 and high to the difference between the maximum element in stations,
     * and the minimum element in stations.
     * While high - low > 1e-6, do binary search.
     * Each time set mid to be the average of low and high and use mid as a candidate of D to calculate the minimum number of gas stations to be added.
     * If the minimum number of gas stations to be added is less than or equal to K,
     * then set high = mid.
     * Otherwise, set low = mid. Finally, return low.
     *
     * @param stations
     * @param k add k more gas stations
     * @return
     */
    public double minMaxGasDist(int[] stations, int k) {
        /**
         * stations[i] will be an integer in range [0, 10^8]
         */
        double lo = 0.0;
        double hi = 1e8;
        /**
         * Answers within 10^-6 of the true value will be accepted as correct.
         * The word within used here is not accurate.
         * I think it refers the smallest gap(gap lower-bound)
         * mid here refers the possible smallest gap(you can seem it as a candidate gap,
         * because we need to check it is possible to split stations with k parts)
         */
        while (hi - lo > 1e-6) {
            double mid = lo + (hi - lo) / 2.0;
            if (feasible(stations, k, mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return lo;
    }

    /**
     * Similar with {@link KokoEatingBananas}
     * Each distance of two adjacent stations is the number of banana.
     * Now we need to check eating speed mid of each hour is under the
     * threshold of k-hours.
     * @param stations
     * @param k
     * @param mid
     * @return
     */private boolean feasible(int[] stations, int k, double mid) {
        int cnt = 0;
        int n = stations.length;
        for (int i = 0; i < n - 1; i++) {
            cnt += (stations[i + 1] - stations[i]) / mid;
        }
        return cnt <= k;
    }
}
