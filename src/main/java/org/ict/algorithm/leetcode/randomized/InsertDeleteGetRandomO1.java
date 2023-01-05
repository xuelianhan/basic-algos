package org.ict.algorithm.leetcode.randomized;

import java.util.*;

/**
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 *
 *
 * Constraints:
 *
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 105 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 * @author sniper
 * @date 04 Jan, 2023
 * LC380
 */
public class InsertDeleteGetRandomO1 {


    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     *
     * Intuition:
     * This problem requires each function works in average O(1) time complexity.
     * Which data structure provides O(1) on an insert, delete, and get operation?
     * Array, ArrayList, Map, Set.
     * we use ArrayList and HashMap here to solve this problem.
     */

    class RandomizedSet {

        /**
         * Store the number
         */
        private List<Integer> nums;

        /**
         * Store the number and its index.
         */
        private Map<Integer, Integer> map;

        private Random rand;

        public RandomizedSet() {
            nums = new ArrayList<>();
            map = new HashMap<>();
            rand = new Random();
        }

        /**
         * Inserts an item val into the set if not present.
         * Returns true if the item was not present, false otherwise
         * e.g.
         * nums:
         * 0 1 2 3
         * 5 4 9 8
         * map:
         * (5, 0), (4, 1), (9, 2), (8, 3)
         *
         * @param val
         * @return
         */
        public boolean insert(int val) {
            /**
             * If val has existed, no need to insert.
             */
            if (map.containsKey(val)) {
                return false;
            }
            /**
             * A little tricky here, we put (val, nums.size()) first, then add val into list.
             * Or you can write as following:
             * nums.add(val);
             * map.put(val, nums.size() - 1);
             */
            map.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /**
         * Removes an item val from the set if present.
         * Returns true if the item was present, false otherwise.
         * e.g. remove(9)
         * idx = map.get(9) = 2
         * idx < 3
         * last = 8
         * nums[2] = 8
         * map.put(8, 2), so (8, 3) is overwrite by (8, 2)
         * nums:
         * 0 1 2 3
         * 5 4 8 8
         * map:
         * (5, 0), (4, 1), (8, 2), (9, 2)
         * map.remove(9)
         * map:(5, 0), (4, 1), (8, 2)
         * nums.remove(3)
         * nums:
         * 0 1 2
         * 5 4 8
         *
         * @param val
         * @return
         */
        public boolean remove(int val) {
            /**
             * If val not exist, no need to remove.
             */
            if (!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val);
            /**
             * if val is not the last element in nums,
             * then we copy the last element to val's index in nums, bind the val's index with the last element in map.
             * At last, we remove val's binding relation in map, and remove the last element in the nums array.
             */
            if (idx < nums.size() - 1) {
                int last = nums.get(nums.size() - 1);
                nums.set(idx, last);
                map.put(last, idx);
            }
            /**
             * Remove binding relationship of val and idx.
             */
            map.remove(val);
            /**
             * Remove duplicated last element in the array.
             */
            nums.remove(nums.size() - 1);
            return true;
        }

        /**
         * Returns a random element from the current set of elements
         * It's guaranteed that at least one element exists when this method is called.
         * Each element must have the same probability of being returned.
         * @return
         */
        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }

}
