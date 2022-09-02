package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 *
 * Example 2:
 * Input: head = []
 * Output: []
 *
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 *
 * @author sniper
 * @date 02 Sep, 2022
 * LC24
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            /**
             * First and Second Nodes of the Pair
             */
            ListNode first = current.next;
            ListNode second = current.next.next;

            first.next = second.next;
            /**
             * second's next point at first which is current's next,
             * So the following using <code>second.next = first;</code> is ok too.
             */
            second.next = current.next;
            current.next = second;

            /**
             *  Move the pointer two nodes ahead
             */
            current = current.next.next;
        }
        return dummy.next;
    }

    private static class ListNode {

        private int val;

        private ListNode next;

        public ListNode() {}

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
