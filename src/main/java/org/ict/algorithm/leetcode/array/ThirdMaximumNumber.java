package org.ict.algorithm.leetcode.array;

import java.util.*;

/**
 * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 * Example 2:
 *
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third distinct maximum does not exist, so the maximum (2) is returned instead.
 * Example 3:
 *
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together since they have the same value).
 * The third distinct maximum is 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * Follow up: Can you find an O(n) solution?
 *
 * @author sniper
 * @date 22 Sep, 2022
 * LC414
 */
public class ThirdMaximumNumber {

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 1};
        int res = thirdMaxV1(nums);
        System.out.println(res);
    }


    public static int thirdMaxV3(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.pollFirst();

            }
        }
        return (set.size() < 3 ? set.pollLast() : set.pollFirst());
    }


    public static int thirdMaxV2(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<Integer> set = map.descendingKeySet();
        int res = 0;
        int i = 1;
        for (Integer key : set) {
            if (set.size() < 3) {
                res = key;
                break;
            } else {
                res = key;
                if (i == 3) {
                    break;
                }
                i++;
            }
        }
        return res;
    }

    public static int thirdMaxV1(int[] nums) {
        int max = 0;
        int k = 3;
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            if (num > max) {
                max = num;
            }
            set.add(num);
            queue.offer(num);

            if (queue.size() > k) {
                queue.poll();
            }
        }
        if (queue.size() == k) {
            return queue.peek();
        } else {
            return max;
        }
    }

    /**
     * Time Complexity is O(N);
     * Space Complexity is O(1)
     * @param nums
     * @return
     */
    public static int thirdMaxTuningVersion(int[] nums) {
        Integer firstMax = null;
        Integer secondMax = null;
        Integer thirdMax = null;
        for (Integer num : nums) {
            if (num.equals(firstMax) || num.equals(secondMax) || num.equals(thirdMax)) {
                continue;
            }
            if (firstMax == null || num > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = num;
            } else if (secondMax == null || num > secondMax) {
                thirdMax = secondMax;
                secondMax = num;
            } else if (thirdMax == null || num > thirdMax) {
                thirdMax = num;
            }
        }
        return thirdMax == null ? firstMax : thirdMax;
    }

    /**
     * Time Complexity O(N)
     * Input [1, 2] expected 2
     * Notice the constraint  -2^31 <= nums[i] <= 2^31 - 1
     * The range is Integer, So The minimum should initialize with Long.MIN_VALUE.
     * Another method is to Initialize with null which be showed in thirdMaxTuningVersion.
     *
     * Time Complexity is O(N);
     * Space Complexity is O(1)
     *
     * @param nums
     * @return
     */
    public static int thirdMax(int[] nums) {
        long min = Long.MIN_VALUE;
        long firstMax = min;
        long secondMax = min;
        long thirdMax = min;
        for (int num : nums) {
            if (num > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = num;
            } else if (num > secondMax && num < firstMax) {
                thirdMax = secondMax;
                secondMax = num;
            } else if (num > thirdMax && num < secondMax) {
                thirdMax = num;
            }
        }
        return (int)(thirdMax == min ? firstMax : thirdMax);
    }
}
