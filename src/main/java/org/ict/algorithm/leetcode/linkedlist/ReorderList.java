package org.ict.algorithm.leetcode.linkedlist;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 * @author sniper
 * @date 16 Sep, 2022
 * LC143
 */
public class ReorderList {

    /**
     * 1.Cut from the middle and reverse the second half.
     * 2.Merge the first half and the second half.
     * @param head
     */
    public void reorderListV2(ListNode head) {
        /**
         * Corner cases
         */
        if (null == head || head.next == null || head.next.next == null) {
            return;
        }

        /**
         * Find the middle of the list.
         */
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /**
         * Cut from the middle and reverse the second half list.
         * e.g.
         * 1->2->3->4->5->6->null
         * f=5,s=3, reverseStart=4
         *
         * e.g.
         * 1->2->3->4->5->6->7->null
         * f=7,s=4, reverseStart=5
         */
        ListNode reverseStart = slow.next;
        slow.next = null;

        ListNode pre = null;
        while(reverseStart != null) {
            ListNode next = reverseStart.next;
            reverseStart.next = pre;
            pre = reverseStart;
            reverseStart = next;
        }

        /**
         * Merge two lists one by one.
         */
        ListNode l1 = head;
        ListNode l2 = pre;
        while (l1 != null && l2 != null) {
            ListNode l2Next = l2.next;

            l2.next = l1.next;
            l1.next = l2;

            l1 = l2.next;
            l2 = l2Next;
        }
    }

    public void reorderList(ListNode head) {
        /**
         * Corner cases
         */
        if (null == head || head.next == null || head.next.next == null) {
            return;
        }
        /**
         * Find the middle of the list.
         *
         * e.g.
         * 1->2->3->4->5->6->null
         * f=1,s=1
         * f=3,s=2
         * f=5,s=3
         *
         * e.g.
         * 1->2->3->4->5->6->7->null
         * f=1,s=1
         * f=3,s=2
         * f=5,s=3
         * f=7,s=4
         */
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /**
         * Reverse the half part of list after the middle.
         * The middle is the head, we need to reverse the nodes after the middle.
         *
         * input: 1->2->3->4->5->6->null
         * middle=3, reverseStart=4
         * output: 1->2->3->6->5->4->null
         *
         * input: 1->2->3->4->5->6->7->null
         * middle=4, reverseStart=5
         * output: 1->2->3->4->7->6->5->null
         */
        ListNode middle = slow;
        ListNode lastOne = slow.next;
        while (lastOne.next != null) {
            /**
             * 1.locate the node need to be reversed.
             * 2.mark the next node of the reverse node.
             * 3.link the reverse node's next to the middle's next
             * 4.link the middle node's next to the reverse node.
             */
            ListNode startReverse = lastOne.next;
            lastOne.next = startReverse.next;
            startReverse.next = middle.next;
            middle.next = startReverse;
        }

        /**
         * Start to reorder the list one by one
         * input: 1->2->3->6->5->4->null
         * middle=3, first=1, second=6
         *
         *
         * output: 1->6->2->5->3->4->null
         *
         *
         *
         * input: 1->2->3->4->7->6->5->null
         * middle=4, first=1, second=7
         *
         * output: 1->7->2->6->3->5->4->null
         */
        ListNode first = head;
        ListNode second = middle.next;
        while (first != middle) {
            /**
             * we need to insert second after first
             * 1.mark the next node of second;
             * 2.let second point at the next node of first;
             * 3.let next node of first point at second;
             * 4.move first to the next insert position;
             * 5.move second to the next node to be adjusted.
             */
            middle.next = second.next;
            second.next = first.next;
            first.next = second;

            first = second.next;
            second = middle.next;
        }
    }

    /**
     * This reverse operation has no head, so we need to use a previous pointer to mark the head.
     * We pick up nodes one by one from list, and insert the current node after the previous node.
     * After then, we move pre to current node, and move the current pointer to the next node.
     *
     * This method use three pointers: pre-current, current, next.
     * 1.current pointer point at the node picked up.
     * 2.next pointer mark the next node after the current node.
     * 3.pre-current mark the head of the list.
     * @param cur
     * @return
     */
    public static ListNode reverse(ListNode cur) {
        ListNode preCur = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = preCur;
            preCur = cur;
            cur = next;
        }
        return preCur;
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
