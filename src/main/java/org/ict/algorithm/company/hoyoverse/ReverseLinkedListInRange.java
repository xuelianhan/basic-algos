package org.ict.algorithm.company.hoyoverse;

/**
 * Description
 * Inverts an interval from position m to position n of a chain table with size nodes.
 * requires time complexity O(n) and space complexity O(1).
 *
 * Example:
 * The given link table is
 * 1→2→3→4→5→NULL, m=2,n=4.
 * Returns 1→4→3→2→5→NULL.
 *
 * Data range: length of the linked table 0<size≤1000.
 * 0<m≤n≤size, the value of each node in the chain table satisfies
 * ∣val∣≤ 1000
 * Requirements: time complexity O(n), space complexity O(n)
 * Advanced: time complexity O(n), space complexity O(1)
 *
 * Example 1
 * Input: {1,2,3,4,5},2,4
 * Return value: {1,4,3,2,5}
 *
 * Example 2
 * Input: {5},1,1
 * Return value: {5}
 *
 * @author sniper
 * @date 21 Jun 2023
 * NC21,
 * LC92, Medium
 */
public class ReverseLinkedListInRange {


    public ListNode reverseBetween (ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        /**
         * keep pre not moving, change pre.next only.
         * for each loop, we make pre.next point at then and move then forward.
         */
        ListNode start = pre.next;
        ListNode then = start.next;
        for (int i = 1; i <= (n - m); i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
