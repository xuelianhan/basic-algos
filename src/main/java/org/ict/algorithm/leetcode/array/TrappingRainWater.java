package org.ict.algorithm.leetcode.array;

/**
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it is able to trap after raining.
 *
 * @see https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. 
 * In this case, 6 units of rain water (blue section) are being trapped. 
 * Thanks Marcos for contributing this image!
 * 
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * 
 * LC42
 */
public class TrappingRainWater {
	
	/**
	 * With left and right index.
     * Keep track of the already safe level and the total water so far.
     * In each step, process and discard the lower one of the leftmost or rightmost elevation.
	 * @param height
	 * @return
	 */
	public int trapV2(int[] height) {
	    int l = 0, r = height.length - 1, level = 0, water = 0;
	    while (l < r) {
	        int lower = height[height[l] < height[r] ? l++ : r--];
	        level = Math.max(level, lower);
	        water += level - lower;
	    }
	    return water;
	}

	public int trapV1(int[] height) {
        if (height == null || height.length == 0) {
        	return 0;
        }

        int l = 0, r = height.length - 1;
        int level = 0, water = 0;
        int lower;

        // keep track of the already safe level and the total water, already safe means encountering an elevation
        // that is bigger than the current level
        while (l < r) {
            if (height[l] < height[r]) {
                lower = height[l++];
            } else {
                lower = height[r--];
            }

            if (lower > level) {
                level = lower;
            }
            water += level - lower;
        }

        return water;
    }
}
