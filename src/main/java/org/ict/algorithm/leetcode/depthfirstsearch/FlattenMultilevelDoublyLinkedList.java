package org.ict.algorithm.leetcode.depthfirstsearch;

/**
 * You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer,
 * and an additional child pointer.
 * This child pointer may or may not point to a separate doubly linked list,
 * also containing these special nodes.
 * These child lists may have one or more children of their own, and so on,
 * to produce a multilevel data structure as shown in the example below.
 * Given the head of the first level of the list,
 * flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * Let curr be a node with a child list.
 * The nodes in the child list should appear after curr and before curr.next in the flattened list.
 *
 * Return the head of the flattened list.
 * The nodes in the list must have all of their child pointers set to null.
 *
 *
 *
 * Example 1:
 * Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * Output: [1,2,3,7,8,11,12,9,10,4,5,6]
 * Explanation: The multilevel linked list in the input is shown.
 * After flattening the multilevel linked list it becomes:
 *
 * Example 2:
 * Input: head = [1,2,null,3]
 * Output: [1,3,2]
 * Explanation: The multilevel linked list in the input is shown.
 * After flattening the multilevel linked list it becomes:
 *
 * Example 3:
 * Input: head = []
 * Output: []
 * Explanation: There could be empty list in the input.
 *
 *
 * Constraints:
 * The number of Nodes will not exceed 1000.
 * 1 <= Node.val <= 10^5
 * How the multilevel linked list is represented in test cases:
 *
 * We use the multilevel linked list from Example 1 above:
 *
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 * The serialization of each level is as follows:
 *
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * To serialize all levels together,
 * we will add nulls in each level to signify no node connects to the upper node of the previous level.
 * The serialization becomes:
 *
 * [1,    2,    3, 4, 5, 6, null]
 *              |
 * [null, null, 7,    8, 9, 10, null]
 *                    |
 * [            null, 11, 12, null]
 * Merging the serialization of each level and removing trailing nulls we obtain:
 *
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * @author sniper
 * @date 07 May 2023
 * LC430, Medium, frequency=28
 */
public class FlattenMultilevelDoublyLinkedList {


    public Node flattenV2(Node head) {

        return null;
    }

    /**
     * Iterative Solution
     * @param head
     * @return
     */
    public Node flattenV1(Node head) {

        return null;
    }

    /**
     * Depth-First-Search Recursive Solution
     * e.g.
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---9---10--NULL
     *             |
     *            11--12--NULL
     * expected:
     * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        Node cur = head;
        while (cur != null) {
            if (cur.child != null) {
                Node next = cur.next;
                cur.child = flatten(cur.child);
                Node tail = cur.child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                cur.next = cur.child;
                cur.next.prev = cur;

                cur.child = null;
                tail.next = next;
                if (next != null) {
                    next.prev = tail;
                }
            }
            cur = cur.next;
        }
        return head;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}
