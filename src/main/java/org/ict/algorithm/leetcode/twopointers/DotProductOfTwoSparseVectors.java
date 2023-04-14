package org.ict.algorithm.leetcode.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sniper
 * @date 14 Apr, 2023
 * LC1570, Medium, frequency=121
 */
public class DotProductOfTwoSparseVectors {

    class SparseVectorV1 {

        private Map<Integer, Integer> indexToNum = new HashMap<>();

        SparseVectorV1(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    indexToNum.put(i, nums[i]);
                }
            }
        }

        /**
         * Return the dotProduct of two sparse vectors
         * @param vec
         * @return
         */
        public int dotProduct(SparseVectorV1 vec) {
            if (indexToNum.size() < vec.indexToNum.size()) {
                return vec.dotProduct(this);
            }
            int ans = 0;
            for (int index : vec.indexToNum.keySet()) {
                if (indexToNum.containsKey(index)) {
                    ans += vec.indexToNum.get(index) * indexToNum.get(index);
                }
            }
            return ans;
        }

    }
}
