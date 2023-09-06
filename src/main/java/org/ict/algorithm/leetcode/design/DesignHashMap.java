package org.ict.algorithm.leetcode.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the MyHashMap class:
 *
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap.
 * If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * Explanation
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 *
 *
 * Constraints:
 *
 * 0 <= key, value <= 10^6
 * At most 10^4 calls will be made to put, get, and remove.
 * @author sniper
 * @date 05 Sep 2023
 * LC706, Easy
 */
public class DesignHashMap {

    /**
     * Time Cost 17ms
     */
    static class MyHashMap {

        /**
         * At most 10^4 calls will be made to put, get, and remove.
         */
        private static final int kSize = 10000;
        /**
         * Each slot store (key, value) list
         */
        List<int[]>[] lists;

        public MyHashMap() {
            lists = new List[kSize];
            for (int i = 0; i < kSize; i++) {
                lists[i] = new ArrayList<>();
            }
        }

        /**
         * Value will always be non-negative
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            for (int[] pair : lists[key % kSize]) {
                if (pair[0] == key) {
                    pair[1] = value;
                    return;
                }
            }

            lists[key % kSize].add(new int[] {key, value});
        }

        /**
         * Returns the value to which the specified key is mapped,
         * or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            for (int[] pair : lists[key % kSize]) {
                if (pair[0] == key) {
                    return pair[1];
                }
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key
         * if this map contains a mapping for the key
         */
        public void remove(int key) {
            int mod = key % kSize;
            for (int i = 0; i < lists[mod].size(); i++) {
                if (lists[mod].get(i)[0] == key) {
                    lists[mod].remove(i);
                    return;
                }
            }
        }
    }
}
