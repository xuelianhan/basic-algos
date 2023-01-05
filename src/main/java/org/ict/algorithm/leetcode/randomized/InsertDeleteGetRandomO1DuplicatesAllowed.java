package org.ict.algorithm.leetcode.randomized;

import java.util.*;

/**
 * RandomizedCollection is a data structure that contains a collection of numbers,
 * possibly duplicates (i.e., a multiset). It should support inserting and removing specific elements,
 * and removing a random element.
 *
 * Implement the RandomizedCollection class:
 *
 * RandomizedCollection() Initializes the empty RandomizedCollection object.
 * bool insert(int val) Inserts an item val into the multiset, even if the item is already present.
 *                      Returns true if the item is not present, false otherwise.
 * bool remove(int val) Removes an item val from the multiset if present.
 *                      Returns true if the item is present, false otherwise.
 * Note that if val has multiple occurrences in the multiset, we only remove one of them.
 * int getRandom() Returns a random element from the current multiset of elements.
 * The probability of each element being returned is linearly related to the number of same values the multiset contains.
 * You must implement the functions of the class such that each function works on average O(1) time complexity.
 *
 * Note: The test cases are generated such that getRandom will only be called if there is at least one item in the RandomizedCollection.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
 * [[], [1], [1], [2], [], [1], []]
 * Output
 * [null, true, false, true, 2, true, 1]
 *
 * Explanation
 * RandomizedCollection randomizedCollection = new RandomizedCollection();
 * randomizedCollection.insert(1);   // return true since the collection does not contain 1.
 *                                   // Inserts 1 into the collection.
 * randomizedCollection.insert(1);   // return false since the collection contains 1.
 *                                   // Inserts another 1 into the collection. Collection now contains [1,1].
 * randomizedCollection.insert(2);   // return true since the collection does not contain 2.
 *                                   // Inserts 2 into the collection. Collection now contains [1,1,2].
 * randomizedCollection.getRandom(); // getRandom should:
 *                                   // - return 1 with probability 2/3, or
 *                                   // - return 2 with probability 1/3.
 * randomizedCollection.remove(1);   // return true since the collection contains 1.
 *                                   // Removes 1 from the collection. Collection now contains [1,2].
 * randomizedCollection.getRandom(); // getRandom should return 1 or 2, both equally likely.
 *
 *
 * Constraints:
 *
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 105 calls in total will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 * @author sniper
 * @date 05 Jan, 2023
 * LC381
 * Phone Interview Question.
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {


    /**
     * Your RandomizedCollection object will be instantiated and called as such:
     * RandomizedCollection obj = new RandomizedCollection();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     *
     * Intuition:
     * This problem requires each function works in average O(1) time complexity.
     * Which data structure provides O(1) on an insert, delete, and get operation?
     * Array, ArrayList, Map, Set.
     * we use ArrayList, HashMap and HashSet here to solve this problem.
     */
    class RandomizedCollection {

        private List<Integer> nums;

        private Map<Integer, Set<Integer>> map;

        private Random rand;

        public RandomizedCollection() {
            nums = new ArrayList<>();
            map = new HashMap<>();
            rand = new Random();
        }

        /**
         * Inserts an item val into the multiset,
         * even if the item is already present.
         *
         * e.g.nums:[3, 3, 3, 2, 2, 1]
         * map:(1, (5)), (2, (3, 4)), (3, (0, 1, 2))
         *
         * @param val
         * @return true if the item is not present, false otherwise.
         */
        public boolean insert(int val) {
            boolean exist = map.containsKey(val);
            if (!exist) {
                map.put(val, new HashSet<>());
            }
            map.get(val).add(nums.size());
            nums.add(val);
            return !exist;
        }

        /**
         * Removes an item val from the multiset if present.
         *
         * e.g.nums:[3, 3, 3, 2, 2, 1]
         * map:(1, (5)), (2, (3, 4)), (3, (0, 1, 2))
         *
         * remove(3)
         * idx:0, map.get(val).remove(idx) = map.get(3).remove(0)
         * map:(1, (5)), (2, (3, 4)), (3, (1, 2))
         *
         * idx:0, idx < 5, last = 1
         * nums.set(idx, last)
         * nums:[1, 3, 3, 2, 2, 1]
         *
         * map.get(last).add(idx) = map.get(1).add(0)
         * map:(1, (5, 0)), (2, (3, 4)), (3, (1, 2))
         *
         * map.get(last).remove(nums.size() - 1) = map.get(1).remove(5)
         * map:(1, (0)), (2, (3, 4)), (3, (1, 2))
         *
         * nums.remove(nums.size() - 1) = nums.remove(5)
         * nums:[1, 3, 3, 2, 2]
         *
         * @param val
         * @return true if the item is present, false otherwise.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            /**
             * Find one index of the val,
             * remove binding relation of val with this index.
             */
            int idx = map.get(val).iterator().next();
            map.get(val).remove(idx);
            /**
             * If val is not the last element, overwrite val at val's index of nums with the last element.
             * Add new binding relationship of last element in the map.
             * Remove old binding relationship of last element in the map.
             */
            if (idx < nums.size() - 1) {
                /**
                 * overwrite val with last element at val's idx
                 * Add new binding relationship of last element at idx
                 */
                int last = nums.get(nums.size() - 1);
                nums.set(idx, last);
                map.get(last).add(idx);

                /**
                 * Remove old binding relationship of last element at last position.
                 */
                map.get(last).remove(nums.size() - 1);
            }
            /**
             * If delete val has no index set, remove the key entry of val directly.
             */
            if (map.get(val).isEmpty()) {
                map.remove(val);
            }
            /**
             * Remove duplicated last element in the array.
             */
            nums.remove(nums.size() - 1);
            return true;
        }

        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }
}
