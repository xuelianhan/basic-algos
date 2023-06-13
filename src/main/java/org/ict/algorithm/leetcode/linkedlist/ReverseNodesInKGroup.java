package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a linked list, reverse the nodes of the list k at a time,
 * and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end, should remain as it is.
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 * 1--->2--->3--->4--->5
 * 2--->1--->4--->3--->5
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * @author sniper
 * @date 13 Jun 2023
 * LC25, Hard, frequency=15
 */
public class ReverseNodesInKGroup {

    /**
     * Understanding the following solution
     * Recursive Solution
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupV4(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverse(head, tail);
        head.next = reverseKGroupV4(tail, k);
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseKGroupV3(ListNode head, int k) {
        //todo
        return null;
    }

    public ListNode reverseKGroupV2(ListNode head, int k) {
        //todo
        return null;
    }

    public ListNode reverseKGroupV1(ListNode head, int k) {
        //todo
        return null;
    }

    /**
     * Understanding the following solution
     * -----------------------------------
     * e.g. 1--->2--->3--->4--->5, k = 2
     * -----------------------------------
     * dummy--->1--->2--->3--->4--->5
     *   ^           ^    ^
     *   |           |    |
     *  pre         cur  next
     * -----------------------------------
     * dummy--->1--->2    3--->4--->5
     *   ^      ^    ^    ^
     *   |      |    |    |
     *  pre  start  cur  next
     * -----------------------------------
     * dummy--->2--->1    3--->4--->5
     *   ^           ^    ^
     *   |           |    |
     *  pre        start  next
     * -----------------------------------
     * dummy--->2--->1--->3--->4--->5
     *   ^           ^    ^
     *   |           |    |
     *  pre        start  next
     * -----------------------------------
     * dummy--->2--->1--->3--->4--->5
     *               ^    ^
     *               |    |
     *             start next
     *              pre
     *              cur
     * -----------------------------------
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = dummy;

        while (cur.next != null) {
            for (int i = 0; i < k && cur != null; i++) {
                cur = cur.next;
            }
            /**
             * If the number of nodes is not a multiple of k then left out nodes in the end, should remain as it is.
             */
            if (cur == null) {
                return dummy.next;
            }

            ListNode next = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = reverseList(start);
            start.next = next;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
