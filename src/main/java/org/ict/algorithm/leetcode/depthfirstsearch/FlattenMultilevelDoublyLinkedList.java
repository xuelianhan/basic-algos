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

    /**
     *
     * ------------------------------------------------
     * e.g.
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---9---10--NULL
     *             |
     *            11--12--NULL
     * expected: [1,2,3,7,8,11,12,9,10,4,5,6]
     * @param head
     * @return
     */
    public Node flattenV4(Node head) {

        return null;
    }

    /**
     * Understanding the following solution
     * Iterative Solution
     * ------------------------------------------------
     * e.g. expected: [1,2,3,7,8,11,12,9,10,4,5,6]
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---9---10--NULL
     *             |
     *            11--12--NULL
     *
     * 1---2---3---7---8---9---10---4---5---6--NULL
     *                 |
     *                11--12--NULL
     *
     * 1---2---3---7---8---11---12---10---4---5---6--NULL
     * -----------------------------------------------
     * class Solution {
     * public:
     *     Node* flatten(Node* head) {
     *         for (Node* p = head; p; p = p->next) {
     *             if (p->child) {
     *                 Node* child = p->child;
     *                 Node* tail = child;
     *                 while (tail->next) {
     *                     tail = tail->next;
     *                 }
     *
     *                 Node* next = p->next;
     *                 p->next = child;
     *                 child->prev = p;
     *                 p->child = nullptr;
     *
     *                 tail->next = next;
     *                 if (next) {
     *                     next->prev = tail;
     *                 }
     *             }
     *         }
     *         return head;
     *     }
     * };
     * @param head
     * @return
     */
    public Node flattenV3(Node head) {
        for (Node p = head; p != null; p = p.next) {
            if (p.child != null) {
                Node child = p.child;
                Node tail = child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                Node next = p.next;
                p.next = child;
                child.prev = p;
                p.child = null;

                tail.next = next;
                if (next != null) {
                    next.prev = tail;
                }
            }
        }
        return head;
    }

    /**
     * Understanding the following solution
     * Iterative Solution
     *
     * The iterative writing method is the other way around.
     * The second layer is added to the first layer,
     * at this time there may be many layers under the second layer.
     * We ignore them temporarily,
     * then layer by layer to join the first layer while traversing them.
     * No matter which method, the list can be flattened finally.
     * You can imagine that the whole process likes
     * first layer absorbs the second layer, then the second layer absorbs the third layer, etc.
     * from top to bottom layer by layer.
     *
     * ------------------------------------------------
     * e.g. expected: [1,2,3,7,8,11,12,9,10,4,5,6]
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---9---10--NULL
     *             |
     *            11--12--NULL
     *
     * 1---2---3---7---8---9---10---4---5---6--NULL
     *                 |
     *                11--12--NULL
     *
     * 1---2---3---7---8---11---12---10---4---5---6--NULL
     *
     * @param head
     * @return
     */
    public Node flattenV2(Node head) {
        Node p = head;
        while (p != null) {
            if (p.child != null) {
                /**
                 * In recursive solution the following line is like this:
                 * Node child = flattenV1(p.child);
                 */
                Node child = p.child;
                Node tail = child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                Node next = p.next;
                p.next = child;
                child.prev = p;
                p.child = null;

                tail.next = next;
                if (next != null) {
                    next.prev = tail;
                }
            }
            p = p.next;
        }
        return head;
    }

    /**
     * Understanding the following solution
     * Recursive solution same as flatten
     *
     * The recursive method of writing is to start from the bottom of the operation,
     * first the bottom layer into the penultimate layer,
     * and then the mixed layer into the penultimate layer, and so on,
     * until they are integrated into the first layer.
     *
     * You can imagine that the whole process likes the bottom layer is merged into
     * the 2nd-layer from the bottom, then the 2nd-layer is merged into 3rd-layer from the bottom.etc.
     * from bottom to top layer by layer.
     * ------------------------------------------------
     * e.g. expected: [1,2,3,7,8,11,12,9,10,4,5,6]
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---9---10--NULL
     *             |
     *            11--12--NULL
     *
     * At first, the bottom-layer 11--12 is merged into 7--8 layer.
     * Then, the 7--8 layer is merged into the top layer 1--2.
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---11---12---9---10--NULL
     *
     * 1---2---3---7---8---11---12---10---4---5---6--NULL
     *
     * @param head
     * @return
     */
    public Node flattenV1(Node head) {
        Node p = head;
        while (p != null) {
            if (p.child == null) {
                p = p.next;
                continue;
            }

            Node child = flattenV1(p.child);
            Node tail = child;
            while (tail.next != null) {
                tail = tail.next;
            }

            Node next = p.next;
            p.next = child;
            child.prev = p;
            p.child = null;

            tail.next = next;
            if (next != null) {
                next.prev = tail;
            }

        }
        return head;
    }

    /**
     * Understanding the following solution
     * Recursive Solution
     * Time Cost 2ms
     * ---------------------------------------
     * e.g.expected: [1,2,3,7,8,11,12,9,10,4,5,6]
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---9---10--NULL
     *             |
     *            11--12--NULL
     *
     * 1---2---3---4---5---6--NULL
     *         |
     *         7---8---11---12---9---10--NULL
     *
     * 1---2---3---7---8---11---12---10---4---5---6--NULL
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        Node p = head;
        while (p != null) {
            if (p.child != null) {
                p.child = flatten(p.child);
                Node tail = p.child;
                while (tail.next != null) {
                    tail = tail.next;
                }

                Node next = p.next;
                p.next = p.child;
                p.next.prev = p;//Or using p.child.prev = p; is OK.
                p.child = null;

                tail.next = next;
                if (next != null) {
                    next.prev = tail;
                }
            }
            p = p.next;
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
