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
     * slow point at the previous node before the node waiting to delete.
     * slow.next is 4 that the one we need to delete.
     * 1.Firstly, we mark the next node of slow.next
     * 2.Then we link slow next to next mark
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
         */
        int step = 0;
        while (fast.next != null) {
            if (step >= n) {
                slow = slow.next;
            }
            fast = fast.next;
            step++;
        }
        if (slow.next == null) {
            return dummy.next;
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
