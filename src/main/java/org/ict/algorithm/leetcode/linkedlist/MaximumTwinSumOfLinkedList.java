package org.ict.algorithm.leetcode.linkedlist;

/**
 * In a linked list of size n, where n is even,
 * the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node,
 * if 0 <= i <= (n / 2) - 1.
 *
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
 * These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 *
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 * Example 2:
 *
 *
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 * Example 3:
 *
 *
 * Input: head = [1,100000]
 * Output: 100001
 * Explanation:
 * There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is an even integer in the range [2, 10^5].
 * 1 <= Node.val <= 10^5
 * @author sniper
 * @date 02 Sep, 2022
 * LC2130
 */
public class MaximumTwinSumOfLinkedList {

    /**
     * Fast-Slow pointer solution.
     * @param head
     * @return
     */
    public int pairSumV2(ListNode head) {
        if (head == null) {
            return 0;
        }
        if (head.next == null) {
            return head.next.val;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reverse(slow);
        fast = head;

        int sum = 0;
        while (slow != null) {
            sum = Math.max(sum, slow.val + fast.val);
            slow = slow.next;
            fast = fast.next;
        }
        return sum;
    }

    public int pairSum(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        /**
         * count the length of this link list.
         */
        int len = 0;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        cur = head;

        /**
         * find the middle node of this link list.
         */
        int stop = (len / 2);
        ListNode pre = head;
        int i = 0;
        while (pre != null) {
            if (i == stop) {
                break;
            }
            pre = pre.next;
            i++;
        }
        /**
         * reverse right part of link list from the middle.
         */
        ListNode midNode = reverse(pre);

        /**
         * calculate the sum and get the maximum value.
         */
        int sum = 0;
        while (midNode != null) {
            sum = Math.max(sum, cur.val + midNode.val);
            cur = cur.next;
            midNode = midNode.next;
        }
        return sum;
    }


    public static ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
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
