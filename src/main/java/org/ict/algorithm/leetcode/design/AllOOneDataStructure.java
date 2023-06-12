package org.ict.algorithm.leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1.
 * If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1.
 * If the count of key is 0 after the decrement, remove it from the data structure.
 * It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 *
 *
 * Constraints:
 *
 * 1 <= key.length <= 10
 * key consists of lowercase English letters.
 * It is guaranteed that for each call to dec, key is existing in the data structure.
 * At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.
 * @author sniper
 * @date 12 Jun 2023
 * LC432, Hard, frequency=17
 */
public class AllOOneDataStructure {

    class AllOneV1 {

        public AllOneV1() {
            //todo
        }

        public void inc(String key) {
            //todo
        }

        public void dec(String key) {
            //todo
        }

        public String getMaxKey() {
            //todo
            return null;
        }

        public String getMinKey() {
            //todo
            return null;
        }
    }

    /**
     * Time Cost 55ms
     *  node-root, 0,keys:[]
     *   ^
     *   |
     *   V
     *  node-1, 1, keys:[a, d, f]
     *   ^
     *   |
     *   V
     *  node-2, 2, keys:[b, e]
     *   ^
     *   |
     *   V
     *  node-3, 3, keys:[c]
     *
     * @author StefanPochmann
     * @see <a href="https://leetcode.com/problems/all-oone-data-structure/solutions/91398/c-solution-with-comments/"></a>
     */
    class AllOne {
        Node head = new Node();
        Map<String, Node> map = new HashMap<>();
        public AllOne() {
            head.next = head;
            head.prev = head;
        }

        public void inc(String key) {
            if (!map.containsKey(key)) {
                if (head.next == head || head.next.cnt > 1) {
                    /**
                     * There have not existed line with cnt equaling to 1
                     */
                    map.put(key, head.insert(new Node(key, 1)));
                } else {
                    /**
                     * There have existed line with cnt equaling to 1
                     */
                    head.next.keys.add(key);
                    map.put(key, head.next);
                }
            } else {
                Node cur = map.get(key);
                Node next = cur.next;
                if (next == head || next.cnt > cur.cnt + 1) {
                    map.put(key, cur.insert(new Node(key, cur.cnt + 1)));
                } else {
                    next.keys.add(key);
                    map.put(key, next);
                }
                cur.keys.remove(key);
                if (cur.keys.isEmpty()) {
                    cur.remove();
                }
            }
        }

        public void dec(String key) {
            Node cur = map.get(key);
            if (cur.cnt == 1) {
                map.remove(key);
            } else {
                Node prev = cur.prev;
                if (prev == head || prev.cnt < cur.cnt - 1) {
                    map.put(key, prev.insert(new Node(key, cur.cnt - 1)));
                } else {
                    prev.keys.add(key);
                    map.put(key, prev);
                }
            }
            cur.keys.remove(key);
            if (cur.keys.isEmpty()) {
                cur.remove();
            }
        }

        public String getMaxKey() {
            return head.prev.keys.iterator().next();
        }

        public String getMinKey() {
            return head.next.keys.iterator().next();
        }
    }

    static class Node {
        Node prev;
        Node next;
        int cnt;
        /**
         * Store the keys with the same cnt.
         */
        Set<String> keys = new HashSet<>();

        public Node() {
            this("", 0);
        }

        public Node(String key, int cnt) {
            this.cnt = cnt;
            keys.add(key);
        }

        public Node insert(Node node) {
            node.prev = this;
            node.next = this.next;
            node.prev.next = node;
            node.next.prev = node;
            return node;
        }

        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
}
