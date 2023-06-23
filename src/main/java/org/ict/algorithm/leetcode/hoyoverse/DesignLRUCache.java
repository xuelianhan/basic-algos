package org.ict.algorithm.leetcode.hoyoverse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design the LRU (Least Recently Used) cache structure, which is sized at construction time, assuming that the size is capacity and the number of operations is n, and has the following functions.
 * 1. solution(int capacity) initializes the LRU cache with a positive integer as capacity capacity
 * 2. get(key): If the keyword key exists in the cache, the value corresponding to the key is returned, otherwise -1 is returned
 * 3. set(key, value): insert the record (key, value) into the structure, if the key key already exists, change its data value, if not, insert the set of key-value into the cache, if the number of key-value exceeds capacity, pop up the longest unused key-value
 * Tip.
 * 1. Once the set or get operation of a key occurs, it is considered that the record of this key becomes the most frequently used, and then the cache is refreshed.
 * 2. When the size of the cache exceeds its capacity, the least frequently used records are removed.
 * 3. The returned value is expressed as a string, if it is set, it will output "null" to indicate (no need for the user to return, the system will automatically output), easy to observe
 * 4. set and get functions must be run in O(1) mode
 * 5. In order to facilitate the distinction between key and value in the cache, the following instructions in the cache key wrapped with "" sign
 * Data range.
 * 1≤capacity<=10^5
 * 0≤key,val≤2×10^9
 * 1≤n≤10^5
 *
 * Example 1
 * Input:
 * ["set", "set", "get", "set", "get", "set", "get", "get", "get", "get"],[[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]],2
 * Copy
 * Return value:
 * ["null", "null", "1", "null","-1", "null","-1", "3", "4"]
 * Copy
 * Description:
 * We think of the cache as a queue, and the last argument is 2 for capacity, so
 * Solution s = new Solution(2).
 * s.set(1,1); //insert (1,1) into the cache, the cache is {"1"=1}, the set operation returns "null"
 * s.set(2,2); //insert (2,2) into the cache, the cache is {"2"=2, "1"=1}, the set operation returns "null"
 * output=s.get(1);// because of the get(1) operation, the cache is updated, the cache is {"1"=1, "2"=2}, the get operation returns "1"
 * s.set(3,3); // insert (3,3) into the cache, the cache capacity is 2, so remove a certain tail of the key-value, the cache is {"3"=3, "1"=1}, the set operation returns "null"
 * output=s.get(2);// because get(2) operation, there is no corresponding key, so the get operation returns "-1"
 * s.set(4,4); // insert (4,4) into the cache, the cache capacity is 2, so remove a certain tail of the key-value, the cache is {"4"=4, "3"=3}, the set operation returns "null"
 * output=s.get(1);// because get(1) operation, there is no corresponding key, so the get operation returns "-1"
 * output=s.get(3);// because of get(3) operation, the cache is updated, the cache is {"3"=3, "4"=4}, the get operation returns "3"
 * output=s.get(4);//because of the get(4) operation, the cache is updated, the cache is {"4"=4, "3"=3}, and the get operation returns "4"
 * @author sniper
 * @date 21 Jun 2023
 * LC146, NC93
 */
public class DesignLRUCache {

    public ArrayList<String> LRUCache(ArrayList<String> operators, ArrayList<ArrayList<Integer>> param, int capacity) {
        //todo
        return null;
    }

    static class LRUCache {

        private final LinkedHashMap<Integer, Integer> cache;

        public LRUCache(int capacity) {
            cache = new LinkedHashMap<Integer, Integer>(16, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return this.size() > capacity;
                }
            };
        }

        public int get(int key) {
            return (cache.get(key) == null ? -1 : cache.get(key).intValue());
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }
}
