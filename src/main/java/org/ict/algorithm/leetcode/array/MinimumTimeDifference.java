package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of 24-hour clock time points in "HH:MM" format,
 * return the minimum minutes difference between any two time-points in the list.
 *
 *
 * Example 1:
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * Example 2:
 *
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0

 * Constraints:
 * 2 <= timePoints.length <= 2 * 10^4
 * timePoints[i] is in the format "HH:MM".
 * @author sniper
 * @date 07 May 2023
 * LC539, Medium, frequency=30
 */
public class MinimumTimeDifference {

    public int findMinDifferenceV2(List<String> timePoints) {
        //todo
        return 0;
    }

    /**
     * class Solution:
     *     def findMinDifference(self, timePoints: List[str]) -> int:
     *         minutes = sorted(int(t[:2]) * 60 + int(t[3:]) for t in timePoints)
     *         minutes.append(minutes[0] + 24 * 60)
     *         res = 24 * 60
     *         for i in range(1, len(minutes)):
     *             res = min(res, minutes[i] - minutes[i - 1])
     *         return res
     *
     * @param timePoints
     * @return
     */
    public int findMinDifferenceV1(List<String> timePoints) {
        List<Integer> minutes = new ArrayList<>();
        for (String tp : timePoints) {
            String[] time = tp.split(":");
            minutes.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }
        Collections.sort(minutes);
        /**
         * Very important.
         * Process the head the tail difference
         */
        minutes.add(minutes.get(0) + 24 * 60);
        int res = 24 * 60;
        for (int i = 1; i < minutes.size(); i++) {
            res = Math.min(res, minutes.get(i) - minutes.get(i - 1));
        }
        return res;
    }

    /**
     *
     * e.g. timePoints = ["23:59","00:00"], expected 1
     * e.g. timePoints = ["05:31","22:08","00:35"], expected 147
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String s = timePoints.get(i);
            int hour = Integer.parseInt(s.substring(0, 2)) * 60;
            int minute = Integer.parseInt(s.substring(3));
            times[i] = hour + minute;
        }

        Arrays.sort(times);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            res = Math.min(res, times[i] - times[i - 1]);
        }

        /**
         * Process the head the tail difference:
         * (head + 24 * 60) - tail
         */
        res = Math.min(res, 24 * 60 + times[0] - times[n - 1]);
        return res;
    }
}
