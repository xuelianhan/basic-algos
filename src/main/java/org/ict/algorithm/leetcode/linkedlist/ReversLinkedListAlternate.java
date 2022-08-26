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
 * hulu coding interview
 *
 */
public class ReversLinkedListAlternate {

    public ListNode reverseAlternately(ListNode head) {
        return null;
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
