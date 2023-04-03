package org.ict.algorithm.leetcode.twopointers;

import java.util.Arrays;

/**
 * You are given an array people where people[i] is the weight of the ith person,
 * and an infinite number of boats where each boat can carry a maximum weight of limit.
 * Each boat carries at most two people at the same time,
 * provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 *
 *
 * Example 1:
 *
 * Input: people = [1,2], limit = 3
 * Output: 1
 * Explanation: 1 boat (1, 2)
 * Example 2:
 *
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 * Explanation: 3 boats (1, 2), (2) and (3)
 * Example 3:
 *
 * Input: people = [3,5,3,4], limit = 5
 * Output: 4
 * Explanation: 4 boats (3), (3), (4), (5)
 *
 *
 * Constraints:
 *
 * 1 <= people.length <= 5 * 10^4
 * 1 <= people[i] <= limit <= 3 * 10^4
 * @author sniper
 * @date 03 Apr, 2023
 * LC881, Medium
 */
public class BoatsToSavePeople {

    /**
     * Understanding the following Greedy solution
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoatsV1(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0;
        int lo = 0;
        int hi = people.length - 1;
        while (lo <= hi) {
            /**
             * Choose at most two people under limit at the same time.
             */
            if (people[lo] + people[hi] <= limit) {
                lo++;
            }
            res++;
            hi--;
        }
        return res;
    }


    /**
     * Greedy Solution
     * Let's sort the input. We know that the maximum weight is at most limit.
     * The best way to choose the maximum weight first.
     * Then we check if we could include one more (the minimum one).
     * We can use two pointers to track the minimum one and the maximum one.
     *
     * e.g. people = [3,2,2,1], limit = 3
     * After sort: [1,2,2,3]
     * lo:0, hi:3, pick=people[3]=3, left=3-3=0, people[0]=1, res++:res:1, hi:2
     * lo:0, hi:2, pick=people[2]=2, left=3-2=1, people[0]=1, 1==left, lo++:lo:1, res++:res:2, hi:1
     * lo:1, hi:1, pick=people[1]=2, left=3-2=1, people[1]=2, res++:res:3, hi:0
     * lo:1, hi:0, while-loop-ended, return res:3
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0;
        int lo = 0;
        int hi = people.length - 1;
        while (lo <= hi) {
            int pick = people[hi];
            int left = limit - pick;
            if (people[lo] <= left) {
                lo++;
            }
            res++;
            hi--;
        }
        return res;
    }
}
