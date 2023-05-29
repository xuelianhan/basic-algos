package org.ict.algorithm.leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists.
 * Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10^4
 * 0 <= value <= 10^5
 * At most 2 * 10^5 calls will be made to get and put.
 * @author sniper
 * @date 18 Sep, 2022
 * LC146, Medium, frequency=286
 * Tag: Amazon,Apple,Bloomberg, ByteDance,Microsoft,Nvidia, Oracle, Paypal, Tiktok
 */
public class DesignLRUCache {

    /**
     * Understanding the following solution
     * --------------------------------------
     * class Node:
     *     def __init__(self, key = 0, val = 0):
     *         self.key = key
     *         self.val = val
     *         self.prev = None
     *         self.next = None
     *
     * class LRUCache:
     *
     *     def __init__(self, capacity: int):
     *         self.cache = {}
     *         self.head = Node()
     *         self.tail = Node()
     *         self.capacity = capacity
     *         self.size = 0
     *         self.head.next = self.tail
     *         self.tail.prev = self.head
     *
     *     def get(self, key: int) -> int:
     *         if key not in self.cache:
     *             return -1
     *         node = self.cache[key]
     *         #move to head
     *         self.move_to_head(node)
     *         return node.val
     *
     *     def put(self, key: int, value: int) -> None:
     *         if key in self.cache:
     *             node = self.cache[key]
     *             node.val = value
     *             # move to head
     *             self.move_to_head(node)
     *         else:
     *             node = Node(key, value)
     *             self.cache[key] = node
     *             # add to head
     *             self.add_to_head(node)
     *             self.size += 1
     *             if self.size > self.capacity:
     *                 # remove tail
     *                 node = self.remove_tail()
     *                 self.cache.pop(node.key)
     *                 self.size -= 1
     *
     *     def remove_node(self, node):
     *         node.prev.next = node.next
     *         node.next.prev = node.prev
     *
     *     def add_to_head(self, node):
     *         node.next = self.head.next
     *         self.head.next = node
     *         node.prev = self.head
     *         node.next.prev = node
     *
     *     def remove_tail(self):
     *         node = self.tail.prev
     *         self.remove_node(node)
     *         return node
     *
     *     def move_to_head(self, node):
     *         self.remove_node(node)
     *         self.add_to_head(node)
     */
    static class LRUCacheV1 {
        private Map<Integer, Node> cache = new HashMap<>();
        private Node head = new Node();
        private Node tail = new Node();
        private int capacity;
        private int size;

        public LRUCacheV1(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            /**
             * Move the node to the location after dummy head
             */
            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                Node node = cache.get(key);
                node.val = value;
                /**
                 * Move the node to the head.
                 */
                moveToHead(node);
            } else {
                Node node = new Node(key, value);
                cache.put(key, node);
                /**
                 * add the node to the head, and increment size
                 */
                addToHead(node);
                size++;
                if (size > capacity) {
                    /**
                     * Remove the tail node and its key, and decrement size
                     */
                    node = removeTail();
                    cache.remove(node.key);
                    size--;
                }
            }
        }

        private void removeNode(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        private Node removeTail() {
            /**
             * The tail is a dummy node, so the node before the tail is the last real node.
             */
            Node node = tail.prev;
            removeNode(node);
            return node;
        }

        /**
         * Add node after the dummy head node.
         * @param node
         */
        private void addToHead(Node node) {
            node.next = head.next;

            node.prev = head;
            head.next = node;

            node.next.prev = node;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }
    }

    static class Node {
        int key;
        int val;
        Node prev;
        Node next;
        Node() {}
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Understanding the following solution
     */
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
