package org.ict.algorithm.leetcode.array;

import java.util.*;
import java.util.stream.Stream;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 * @author sniper
 * @date 23 Sep, 2022
 * LC169
 */
public class MajorityElement {

    /**
     *
     * Boyer-Moore Majority Voting Algorithm
     *
     * Understanding this most effective solution!
     *
     * Here is an intuition behind this algorithm:
     * Since majority element occurs more than n/2 times,
     * its frequency is greater than all other elements combined.
     * Therefore, if we mark the occurrence of majority element as +1 and occurrence of any other element as -1,
     * then overall sum of them would be definitely greater than zero.
     *
     * Another interesting analogy to understand this algorithm:
     * Suppose we have n number of people, each holding one element of the array.
     * Then, whenever two people find each other that neither holds the same array element as the other,
     * they sit down.
     * Eventually, in the end, if anyone is left standing,
     * then that element is the majority element.
     * Since majority element occurs more than n/2 times,
     * we can guarantee that this approach will always find the majority element.
     *
     * Time Complexity O(N)
     * Space Complexity O(1)
     *
     *
     *
     * @see <a href="http://www.cs.utexas.edu/~moore/best-ideas/mjrty/"></a>
     * @see <a href="https://www.enjoyalgorithms.com/blog/find-the-majority-element-in-an-array"></a>
     * @param nums
     * @return
     */
    public int majorityElementV4(int[] nums) {
        Integer majorityCandidate = null;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                majorityCandidate = nums[i];
            }
            if (nums[i] == majorityCandidate) {
                count += 1;
            } else {
                count -= 1;
            }
        }
        return majorityCandidate;
    }

    /**
     * Improvement's of majorityElementV2
     * cost 33ms.
     *
     *
     * @param nums
     * @return
     */
    public int majorityElementV3(int[] nums) {
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            if (frequency.get(num) > nums.length/2) {
                return num;
            }
        }
        return res;
    }

    /**
     * What is the right output for the case as following?
     * e.g.
     * Input: nums=[1,1,1,1,2,2,2,2]
     * So in the above case, which on is the majority element? 1 or 2?
     *
     * Cost 37 ms
     * Time Complexity O(N)
     * Space Complexity O(N)
     * @param nums
     * @return
     */
    public int majorityElementV2(int[] nums) {
        int maxFrequency = 0;
        int res = Integer.MIN_VALUE;
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
            if (frequency.get(num) > maxFrequency) {
                maxFrequency = frequency.get(num);
                res = num;
            }
        }
        return res;
    }


    /**
     * Solution Using MinPQ(min-top-heap), cost 19 ms
     * @param nums
     * @return
     */
    public int majorityElementV1(int[] nums) {
        /**
         * 1.Counting frequency of each num in the array.
         */
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        /**
         * 2.Creating a MinPQ
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> frequency.get(o1) - frequency.get(o2)));

        /**
         * 3.Build a heap and keep the heap size at 1
         * Keep the most frequently element in the heap.
         */
        for (int num : frequency.keySet()) {
            queue.offer(num);
            if (queue.size() > 1) {
                queue.poll();
            }
        }
        return queue.peek();
    }


    /**
     * Very Slow, cost 39 ms, the following solution only for reinforcing the understanding of this question.
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        Stream<Map.Entry<Integer, Integer>> sorted = frequency
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(((o1, o2) -> {
                    if (o1 > o2) {
                        return -1;
                    } else if (o1 < o2) {
                        return 1;
                    } else {
                        return 0;
                    }
                })));

        Map.Entry<Integer, Integer> entry = sorted.findFirst().orElse(null);
        if (null == entry) {
            return Integer.MIN_VALUE;
        }
        return entry.getKey();
    }
}
