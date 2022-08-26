package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a linked list and an integer val,
 * remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 *
 * Example 1:
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 *
 *
 * Example 2:
 * Input: head = [], val = 1
 * Output: []
 *
 *
 * Example 3:
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 10^4].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 * @author sniper
 * @date 2022/8/25
 * LC203
 */
public class RemoveLinkedListElements {


    public ListNode removeElementsV2(ListNode head, int val) {
        /**
         * The key to solve this problem
         * is using a helper node to track the head of the list.
         */
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public ListNode removeElements(ListNode head, int val) {
        /**
         * The key to solve this problem
         * is using a helper node to track the head of the list.
         */
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                /**
                 * Here if being writen as (cur = cur.next.next;) is OK too.
                 */
                ListNode next = cur.next;
                cur.next = next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
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
