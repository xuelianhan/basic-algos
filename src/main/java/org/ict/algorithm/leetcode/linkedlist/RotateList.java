package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a linked list,
 * rotate the list to the right by k places.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 *
 * Example 2:
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 10^9
 *
 * @author sniper
 * @date 02 Sep, 2022
 * LC61
 */
public class RotateList {


    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        int len = 0;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        if (k % len == 0) {
            return head;
        }
        /**
         * circle the link, this step is very important.
         */
        cur.next = head;
        int mod = k % len;
        int step = len - mod;

        /**
         * move cur forward with steps.
         */
        int i = 0;
        while (cur != null) {
            if (i >= step) {
                break;
            }
            i++;
            cur = cur.next;
        }
        
        dummy.next = cur.next;
        cur.next = null;
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
