package org.ict.algorithm.leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design a Skiplist without using any built-in libraries.
 *
 * A skiplist is a data structure that takes O(log(n)) time to add, erase and search.
 * Comparing with treap and red-black tree which has the same function and performance,
 * the code length of Skiplist can be comparatively short and the idea behind Skiplists is just simple linked lists.
 * For example, we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it.
 * The Skiplist works this way:
 * 4 30
 *   |
 * 3 30------------>50---------------------->null
 *   |              |
 * 2 30------------>50------->70-->80-------->null
 *   |              |         |    |
 * 1 30-->40-->45-->50-->60-->70-->80-->90-->null
 *
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 *
 * You can see there are many layers in the Skiplist.
 * Each layer is a sorted linked list.
 * With the help of the top layers, add, erase and search can be faster than O(n).
 * It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).
 *
 * See more about Skiplist: https://en.wikipedia.org/wiki/Skip_list
 *
 * Implement the Skiplist class:
 *
 * Skiplist() Initializes the object of the skiplist.
 * bool search(int target) Returns true if the integer target exists in the Skiplist or false otherwise.
 * void add(int num) Inserts the value num into the SkipList.
 * bool erase(int num) Removes the value num from the Skiplist and returns true.
 * If num does not exist in the Skiplist, do nothing and return false.
 * If there exist multiple num values, removing any one of them is fine.
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 *
 *
 *
 * Example 1:
 * Input
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * Output
 * [null, null, null, null, false, null, true, false, true, false]
 *
 * Explanation
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0); // return False
 * skiplist.add(4);
 * skiplist.search(1); // return True
 * skiplist.erase(0);  // return False, 0 is not in skiplist.
 * skiplist.erase(1);  // return True
 * skiplist.search(1); // return False, 1 has already been erased.
 *
 *
 * Constraints:
 *
 * 0 <= num, target <= 2 * 10^4
 * At most 5 * 10^4 calls will be made to search, add, and erase.
 * @author sniper
 * @date 21 Aug 2023
 * LC1206, Medium
 * @see java.util.concurrent.ConcurrentSkipListMap
 * @see java.util.concurrent.ConcurrentSkipListSet
 */
public class DesignSkipList {


    public static void main(String[] args) {
        //DesignSkipList instance = new DesignSkipList();
        Skiplist skiplist = new Skiplist();
        skiplist.add(30);
        skiplist.add(50);
        skiplist.add(70);
    }

    static class Skiplist {

        private Node dummy = new Node(-1, null, null);

        public Skiplist() {}

        /**
         * Inserts the value num into the SkipList.
         * @param target
         * @return
         */
        public boolean search(int target) {
            for (Node node = dummy;  node != null; node = node.down) {
                /**
                 * Find the last node where its val is less than target.
                 */
                node = advance(node, target);
                /**
                 * Then go down from node.
                 */
                if (node.next != null && node.next.val == target) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 4 30
         *   |
         * 3 30------------>50---------------------->null
         *   |              |
         * 2 30------------>50------->70-->80-------->null
         *   |              |         |    |
         * 1 30-->40-->45-->50-->60-->70-->80-->90-->null
         * -------------------------------------------------
         * Returns true if the integer target exists in the Skiplist or false otherwise.
         *
         * @param num
         */
        public void add(int num) {
            /**
             * Collect nodes that before the insertion point
             */
            Deque<Node> stack = new ArrayDeque<>();
            for (Node node = dummy; node != null; node = node.down) {
                node = advance(node, num);
                stack.push(node);
            }

            /**
             * Using random function to ensure the balance of the Skiplist.
             */
            Node down = null;
            boolean shouldInsert  = true;
            while (shouldInsert && !stack.isEmpty()) {
                Node prev = stack.pop();
                prev.next = new Node(num, prev.next, down);
                down = prev.next;
                shouldInsert = Math.random() < 0.5;
            }

            /**
             * Create a topmost new level dummy pointing to existing dummy.
             */
            if (shouldInsert) {
                dummy = new Node(-1, null, dummy);
            }
        }

        /**
         * Removes the value num from the Skiplist and returns true.
         * If num does not exist in the Skiplist, do nothing and return false.
         * If there exist multiple num values, removing any one of them is fine.
         * @param num
         * @return
         */
        public boolean erase(int num) {
            boolean found = false;
            for (Node node = dummy; node != null; node = node.down) {
                node = advance(node, num);
                if (node.next != null && node.next.val == num) {
                    found = true;
                    node.next = node.next.next;
                }
            }
            return found;
        }


        private Node advance(Node node, int target) {
            while (node.next != null && node.next.val < target) {
                node = node.next;
            }
            return node;
        }
    }

    static class Node {
        private int val;
        /**
         * Pointer goes right.
         */
        private Node next;

        /**
         * Pointer goes down.
         */
        private Node down;

        public Node(int val, Node next, Node down) {
            this.val = val;
            this.next = next;
            this.down = down;
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
}
