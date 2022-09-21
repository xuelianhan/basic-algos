package org.ict.algorithm.leetcode.array;

import org.springframework.data.redis.core.HashMapperProvider;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Given a non-empty array of integers nums,
 * every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * Each element in the array appears twice except for one element which appears only once.
 * @author sniper
 * @date 21 Sep, 2022
 * LC136
 *
 * Some tips comes from Krypto02.
 * His homepage is <a href="https://leetcode.com/Krypto02/"></>
 *
 *
 * The question simply asks us to find an element in the given array whose frequency is 1.
 * All the other elements have a frequency of 2.
 * We have to do so in :
 * 1.Linear Time
 * 2.Using Constant Space
 *
 */
public class SingleNumber {


    /**
     * Method XOR operation
     * Notice the condition:
     * Every element appears twice except for one.
     * Find that single one.
     *
     * We apply the extended version of this gate in our bitwise XOR operator.
     * If we do "a^b" , it means that we are applying the XOR gate on the 2 numbers in a bitwise fashion ( on each of the corresponding bits of the numbers).
     * Similarly , if we observe ,
     *
     * A^A=0
     * A^B^A=B
     * (A^A^B) = (B^A^A) = (A^B^A) = B This shows that position doesn't matter.
     * Similarly , if we see , a^a^a......... (even times)=0 and a^a^a........(odd times)=a
     *
     *
     * Time Complexity O(N).
     * Space Complexity O(1)
     *
     * @param nums
     * @return
     */
    public int singleNumberV4(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * Input: nums = [4,1,2,1,2]
     *
     * Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
     * int sum = integers.stream().mapToInt(e -> e).sum();
     *
     * array: A, B, C, B, C
     * set: A, B, C
     *
     * sum set and multiply 2, marked as 2*sum(set) = 2*(A+B+C)
     * sum array, marked as sum(array) = A+B+C+B+C = A + 2*(B+C)
     *
     * 2*sum(set) - sum(array) = 2*(A+B+C) - (A + 2*(B+C)) = A
     *
     * A is the element only appear once.
     *
     * @param nums
     * @return
     */
    public int singleNumberV3(int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int num : nums) {
            set.add(num);
            sum += num;
        }
        int x = set.stream().reduce(0, Integer::sum);
        res = 2*x - sum;
        return res;
    }

    public int singleNumberV2(int[] nums) {
        int res = 0;
        return res;
    }

    /**
     * Time Complexity O(N).
     * Space Complexity O(N).
     *
     * return map
     *       .entrySet()
     *       .stream()
     *       .filter(entry -> value.equals(entry.getValue()))
     *       .map(Map.Entry::getKey);
     *
     * @param nums
     * @return
     */
    public int singleNumberV1(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry: frequency.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

}
