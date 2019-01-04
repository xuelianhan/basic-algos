package org.ict.algorithm.leetcode.array;

import java.util.Map;
import java.util.HashMap;

/**
 * p1
 * tag:array
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, 
 * where index1 must be less than index2. 
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2 
 *
 *
 */
public class TwoSum {
  
  /**
   * O(n);
   *
   */
  public static int[] twoSum(int[] nums, int target) {
    if (nums.length == 0) {
        throw new IllegalArgumentException("input nums must not be empty");
    }
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();           
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement) && map.get(complement) != i) {
            return new int[]{i, map.get(complement)};
        }
        map.put(nums[i], i);
    }
    return new int[]{0};
  }

  public static int[] caculateTwoSumIndex(int[] array, int target) {
    int[] indexs;
    indexs = caculate1(array, target);
    return indexs;
  }

  /**
   * O(n^2)
   */
  private static int[] caculate1(int[] array, int target) {
    int[] indexs = {0, 0};
    int len = array.length;
    for (int i = 0; i < len; i++) {
      int j = i + 1;
      boolean find = false;
      if (array[i] > target) {
        continue;
      }
      while (j < len ) {
        if (array[j] > target) {
          j++;
          continue;
        }
        if ((array[j] + array[i]) != target) {
          j++;
        } else {
          indexs[0] = i + 1;
          indexs[1] = j + 1;
          find = true;
          break;
        }
      }
      if (find) {
        break;
      }
    }
    return indexs;
  }
  
  public static void main(String[] args) {
    int[] array = {200, 1, 3, 100, 31, 5, 7, 50, 51, 101, 9, 10, 28, 23}; 
    int target = 51;
    int[] indexs = caculateTwoSumIndex(array, target);
    System.out.println("indexs[0]:"+indexs[0]+",indexs[1]:"+indexs[1]);
  }
}
