package org.ict.algorithm.leetcode.binarysearch;

/**
 * On a horizontal number line,
 * we have gas stations at positions stations[0], stations[1], ..., stations[N-1],
 * where N = stations.length.
 *
 * Now, we add K more gas stations so that D,
 * the maximum distance between adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * Example:
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 *
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
        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 9;
        MinimizeMaxDistanceToGasStation instance = new MinimizeMaxDistanceToGasStation();
        double res = instance.minMaxGasDist(stations, k);
        System.out.println(res);
    }


    /**
     * e.g. stations = [10, 19, 25, 27, 56, 63, 70, 87, 96, 97], k = 3
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
         * Answers within 10^-6 of the true value will be accepted as correct
         */
        while (hi - lo > 1e-6) {
            double mid = lo + (hi - lo) / 2;
            if (feasible(stations, mid, k)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return lo;
    }

    private boolean feasible(int[] stations, double mid, int k) {
        int cnt = 0;
        int n = stations.length;
        for (int i = 0; i < n - 1; i++) {
            cnt += (stations[i + 1] - stations[i]) / mid;
        }
        return cnt <= k;
    }
}
