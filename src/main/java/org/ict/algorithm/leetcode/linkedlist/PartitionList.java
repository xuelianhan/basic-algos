package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a linked list and a value x,
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 * @author sniper
 * @date 06 Sep, 2022
 * LC86
 */
public class PartitionList {

    /**
     * The fundamental principles are to separate the list into 2 distinct lists
     * and link them afterwards.
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (null == head || null == head.next) {
            return head;
        }

        ListNode smallerHead = new ListNode(0);
        ListNode biggerHead  = new ListNode(0);
        ListNode smaller = smallerHead;
        ListNode bigger  = biggerHead;
        while (head != null) {
            if (head.val < x) {
                smaller.next = head;
                smaller = head;
            } else {
                bigger.next = head;
                bigger = head;
            }
            head = head.next;
        }
        /**
         * Join bigger after smaller.
         */
        smaller.next = biggerHead.next;
        bigger.next = null;
        return smallerHead.next;
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
