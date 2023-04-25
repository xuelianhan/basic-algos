package org.ict.algorithm.leetcode.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values,
 * you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * Follow up: What if only one of the vectors is sparse?
 * In this situation, we always choose the shorter vector to for-loop.
 *
 *
 * Example 1:
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 *
 * Example 2:
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 *
 * Example 3:
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 *
 * @author sniper
 * @date 14 Apr, 2023
 * LC1570, Medium, frequency=121
 */
public class DotProductOfTwoSparseVectors {

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 0, 0, 2, 0, 0};
        int[] nums2 = {1, 0, 0, 0, 3, 0, 4};
        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);

        SparseVectorV1 r1 = new SparseVectorV1(nums1);
        SparseVectorV1 r2 = new SparseVectorV1(nums2);

        int ans = v1.dotProduct(v2);
        int res = r1.dotProduct(r2);
        System.out.println("ans:" + ans + ", res:" + res);
    }

    /**
     * Your SparseVector object will be instantiated and called as such:
     * SparseVector v1 = new SparseVector(nums1);
     * SparseVector v2 = new SparseVector(nums2);
     * int ans = v1.dotProduct(v2);
     * ---------------------------------------
     * class SparseVector:
     *     def __init__(self, nums: List[int]):
     *         self.v = {}
     *         for i, num in enumerate(nums):
     *             if num != 0:
     *                 self.v[i] = num
     *
     *     # Return the dotProduct of two sparse vectors
     *     def dotProduct(self, vec: 'SparseVector') -> int:
     *         res = 0
     *         if len(self.v) > len(vec.v):
     *             self.v, vec.v = vec.v, self.v
     *         for i, num in self.v.items():
     *             if i not in vec.v:
     *                 continue
     *             res += num * vec.v[i]
     *         return res
     *
     */

    static class SparseVector {
        private Map<Integer, Integer> map;

        public SparseVector(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    map.put(i, nums[i]);
                }
            }
        }

        /**
         * Return the dotProduct of two sparse vectors
         * e.g. nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
         * map:(1, 1),(4, 2)
         * vec:(0, 1),(4, 3),(6, 4)
         * If two vectors multiply, for-loop the shorter one of them,
         * because this can speed up precess of calculation.
         * @param vec
         * @return
         */
        public int dotProduct(SparseVector vec) {
            int res = 0;
            if (map.size() > vec.map.size()) {
                //Follow-up question answer
                Map<Integer, Integer> temp = map;
                map = vec.map;
                vec.map = temp;
            }
            /**
             * The following for-loop this-map instead of vec-map
             * so the above assure the this-map is the shorter one.
             */
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int i = entry.getKey();
                int num = entry.getValue();

                res += num * vec.map.getOrDefault(i, 0);
            }
            return res;
        }

    }

    static class SparseVectorV1 {

        private Map<Integer, Integer> map = new HashMap<>();

        public SparseVectorV1(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    map.put(i, nums[i]);
                }
            }
        }

        /**
         * Return the dotProduct of two sparse vectors
         * e.g. nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
         * map:(1, 1),(4, 2)
         * vec:(0, 1),(4, 3),(6, 4)
         *
         * @param vec
         * @return
         */
        public int dotProduct(SparseVectorV1 vec) {
            if (map.size() < vec.map.size()) {
                //Follow-up question answer
                //switch this-map with vec-map if this-map is shorter than vec-map
                return vec.dotProduct(this);
            }
            /**
             * The following for-loop vec-map instead of this-map
             * so the above assure the vec-map passed in is the shorter one.
             */
            int res = 0;
            for (int index : vec.map.keySet()) {
                if (map.containsKey(index)) {
                    res += vec.map.get(index) * map.get(index);
                }
            }
            return res;
        }

    }
}
