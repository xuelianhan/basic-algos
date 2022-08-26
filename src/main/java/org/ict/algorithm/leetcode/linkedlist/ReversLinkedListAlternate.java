package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given a linked list head
 * change 1->2->3->4->5 to 1->5->2->4->3
 *
 * Solution:
 * 1.split into two lists:
 * 1->2->3
 * 4->5
 *
 * 2.reverse second list
 * 1->2->3
 * 5->4
 *
 * 3.alternately append second list to first list, we get
 * 1->5->2->4->3
 *
 * @author sniper
 * @date 2022/8/26
 * @see <a href="https://www.geeksforgeeks.org/given-linked-list-reverse-alternate-nodes-append-end/"></a>
 * hulu coding interview
 *
 */
public class ReversLinkedListAlternate {


    public ListNode reverseAlternately(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        /**
         * If odd numbers of nodes, let slow move one step forward.
         * e.g.
         * 1--->2--->3--->4--->5--->null
         *           s         f
         *
         * even numbers nodes,e.g.
         * 1--->2--->3--->4--->null
         *           s         f
         */
        if (fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;
        /**
         * alternately append second list to first list
         */
        while (slow != null) {
           //todo
        }
        return null;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }


    private static class ListNode {
        int val;

        ListNode next;

        ListNode(){}

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }
}
