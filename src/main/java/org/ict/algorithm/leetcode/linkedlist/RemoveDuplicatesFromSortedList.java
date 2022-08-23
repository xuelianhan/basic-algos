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
        ListNode curr = head;
        while (curr != null) {
            if (curr.next == null) {
                break;
            }
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode curr = head;
        ListNode next = curr.next;
        if (next == null) {
            return head;
        }
        while (next != null) {
            if (curr.val == next.val) {
                curr.next = next.next;
                next = curr.next;
            } else {
                curr = next;
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
