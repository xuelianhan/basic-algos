package org.ict.algorithm.leetcode.linkedlist;

/**
 * @author sniper
 * @date 02 Sep, 2022
 * LC19
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return null;
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
