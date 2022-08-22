package org.ict.algorithm.leetcode.linkedlist;

/**
 *
 * @author sniper
 * @date 2022/8/19
 * LC92
 */
public class ReverseLinkedListInRange {


    /**
     * First locate the node before the left-th node (pre) and the left-th node (cur).
     * Then move cur -> next to be after pre for (right - left) times.
     *
     * head
     * 1---->2---->3---->4---->5
     *
     * 1.initial:
     * pre  start  then
     * 1---->2---->3---->4---->5
     *
     * 2.start.next = then.next:
     * pre  start  then
     * 1---->2     3---->4---->5
     *       |           ^
     *       |           |
     *       -------------
     *
     *
     * 3.then.next = pre.next:
     *       --------
     *       |      |
     * pre  start  then
     * 1---->2     3     4---->5
     *       |           ^
     *       |           |
     *       -------------
     *
     * 4.pre.next = then:
     * --------------
     * |     |      |
     * pre  start  then
     * 1     2     3     4---->5
     *       |           ^
     *       |           |
     *       -------------
     *
     * 5.then = star.next:
     * prev      start  then
     * 1---->3---->2---->4---->5
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        /**
         * keep pre not moving, change pre.next only.
         * for each loop, we make pre.next point at then and move then forward.
         */
        ListNode start = pre.next;
        ListNode then = start.next;
        for (int i = 1; i <= (right - left); i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
