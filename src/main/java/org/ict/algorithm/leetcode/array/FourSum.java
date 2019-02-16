package org.ict.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Given an array nums of n integers and an integer target, 
 * are there elements a, b, c, and d in nums such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 * Example:
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 *
 * LC18
 */
public class FourSum {

	/**
	 *  Basic idea is using subfunctions for 3sum and 2sum, 
	 *  and keeping throwing all impossible cases. 
	 *  O(n^3) time complexity, 
	 *  O(1) extra space complexity.
	 */
	public List<List<Integer>> fourSum(int[] num, int target) {
	    ArrayList<List<Integer>> ans = new ArrayList<>();
	    if(num.length<4)return ans;
	    Arrays.sort(num);
	    for(int i = 0; i < num.length-3; i++){
	        if(num[i] + num[i+1] + num[i+2] + num[i+3] > target) {
	        	break; //first candidate too large, search finished
	        }
	        if(num[i] + num[num.length-1] + num[num.length-2] + num[num.length-3] < target) {
	        	continue; //first candidate too small
	        }
	        if(i > 0 && num[i] == num[i-1]) {
	        	continue; //prevents duplicate result in ans list
	        }
	        for(int j = i+1; j < num.length-2; j++) {
	            if(num[i] + num[j] + num[j+1] + num[j+2] > target) {
	            	break; //second candidate too large
	            }
	            if(num[i] + num[j] + num[num.length-1] + num[num.length-2] < target) {
	            	continue; //second candidate too small
	            }
	            if(j > i+1 && num[j] == num[j-1]) {
	            	continue; //prevents duplicate results in ans list
	            }
	            int low = j+1, high = num.length-1;
	            while (low < high) {
	                int sum = num[i] + num[j] + num[low] + num[high];
	                if (sum == target) { 
	                    ans.add(Arrays.asList(num[i], num[j], num[low], num[high]));
	                    while(low < high && num[low] == num[low+1])low++; //skipping over duplicate on low
	                    while(low < high && num[high] == num[high-1])high--; //skipping over duplicate on high
	                    low++; 
	                    high--;
	                } else if (sum < target) {
	                	//move window
	                	low++; 
	                } else {
	                	//move window
	                	high--;
	                }
	            }
	        }
	    }
	    return ans;
	}
}

