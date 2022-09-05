package org.ict.algorithm.leetcode.linkedlist;

/**
 *
 * Given the head of a sorted linked list,
 * delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 *
 *
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 *
 * @author sniper
 * @date 2022/8/23
 * LC83
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicatesV2(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.next == null) {
                break;
            }
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode cur = head;
        ListNode next = cur.next;
        if (next == null) {
            return head;
        }
        while (next != null) {
            if (cur.val == next.val) {
                cur.next = next.next;
                next = cur.next;
            } else {
                cur = next;
                next = next.next;
            }
        }
        return head;
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
