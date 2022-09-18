package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * Follow up: Could you do this in one pass?
 * @author sniper
 * @date 02 Sep, 2022
 * LC19
 */
public class RemoveNthNodeFromEndOfList {

    /**
     * Two-Pointer Solution.
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     *
     * Dummy->1->2->3->4->5->null
     * fast:dummy, slow:dummy, step:0
     * fast:1, step==0, so slow:dummy, step:1
     * fast:2, step==1, so slow:dummy, step:2
     * fast:3, step==2, so slow:1, step:3
     * fast:4, step==3, so slow:2, step:4
     * fast:5, step==4, so slow:3, step:5
     * fast.next==null, while-loop ended.
     *
     * Input: head = [1], n = 1
     * Output: []
     * Dummy->1->null
     * fast:dummy, slow:dummy, step:0
     * fast:1, step==0, so slow:dummy, step:1
     * fast.next==null, while-loop ended.
     *
     *
     * Input: head = [1,2], n = 1
     * Output: [1]
     * Dummy->1->2->null
     * fast:dummy, slow:dummy, step:0
     * fast:1, step==0, so slow:dummy, step:1
     * fast:2, step==1, so slow:1, step:2
     * fast.next==null, while-loop ended.
     *
     *
     * slow point at the previous node before the node waiting to delete.
     * slow.next is 4 that the one we need to delete.
     * 1.Firstly, we mark the next node of slow.next.
     * 2.Then we link slow next to next node marked on above step.
     * 3.Finally, we return the head.
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        /**
         * Move fast n steps firstly, then we move slow forward
         * The gap between fast and slow is n steps.
         *
         * Why step start with zero?
         * Because both fast and slow are initialized with a dummy node.
         * we need to assure fast pointer forwards (n+1) steps than slow pointer.
         * You can change step value to 1, but need to change the comparable condition to step >= (n+1).
         */
        int step = 0;
        while (fast.next != null) {
            if (step >= n) {
                slow = slow.next;
            }
            fast = fast.next;
            step++;
        }
        /**
         * If head is null, Both fast and slow point at dummy.
         * dummy.next==null.
         */
        if (slow.next == null) {
            return dummy.next;
        }
        ListNode next = slow.next.next;
        slow.next = next;
        return dummy.next;
    }

    /**
     * A one pass solution can be done with two-pointers.
     * Move fast pointer (n+1) steps forward firstly.
     * Then move both pointers fast and slow with the same speed forward.
     * Finally, when fast pointer reaches at the end, the slow pointer stays at
     * the location before the node to be deleted.
     *
     * the n is in normal range here, so we don't need to check its legality.
     * The number of nodes in the list is sz.
     * 1 <= sz <= 30
     * 1 <= n <= sz.
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndV2(ListNode head, int n) {
        if (null == head || n <= 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        /**
         * Move fast n + 1 steps firstly.
         * because fast move one step over n, slow will move one step less.
         * At the end, fast point at the null,
         * slow point at the previous node before the node waiting to delete.
         */
        for (int i = 1; i <= (n + 1); i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode next = slow.next.next;
        slow.next = next;
        return dummy.next;
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
