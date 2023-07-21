package org.ict.algorithm.leetcode.coupang;


/**
 * @author sniper
 * @date 21 Jul 2023
 * LC25
 */
public class ReverseNodesInTwoGroup {

    public ListNode reverseTwoGroup(ListNode head) {
        return reverseKGroup(head, 2);
    }


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

    private static class ListNode {
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
