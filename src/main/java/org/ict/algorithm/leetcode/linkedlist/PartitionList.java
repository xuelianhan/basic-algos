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
 * LC86, Medium
 */
public class PartitionList {

    /**
     * e.g.head = [1,4,3,2,5,2], x = 3
     * ---------------------------------------
     * dummy(-1)-->1-->4-->3-->2-->5-->2
     *  ^
     *  |
     * prev
     * ---------------------------------------
     * dummy(-1)--->1--->4--->3--->2--->5--->2
     *              ^
     *              |
     *             prev
     *             curr
     * ---------------------------------------
     * dummy(-1)-->1-->4--->3--->2--->5--->2
     *             ^   ^
     *             |   |
     *           prev curr
     * ---------------------------------------
     * temp = curr.next;
     * dummy(-1)-->1-->4--->3-->2-->5-->2
     *             ^   ^   ^
     *             |   |   |
     *           prev curr temp
     * ---------------------------------------
     * curr.next = temp.next;
     *                  --------
     *                 |       |
     *                 |       V
     * dummy(-1)-->1-->4   3-->2-->5-->2
     *             ^   ^   ^
     *             |   |   |
     *           prev curr temp
     * ---------------------------------------
     * temp.next = prev.next;
     *                  --------
     *                 |       |
     *                 |       V
     * dummy(-1)-->1-->4<--3   2-->5-->2
     *             ^   ^   ^
     *             |   |   |
     *           prev curr temp
     *
     * ---------------------------------------
     * prev.next = temp;
     *                  --------
     *                 |       |
     *                 |       V
     * dummy(-1)-->1   4<--3   2-->5-->2
     *             ^   ^   ^
     *             |   |   |
     *           prev curr temp
     *             |        ^
     *             |        |
     *             ---------
     *
     * dummy(-1)--->1--->3--->4--->2--->5--->2
     *              ^    ^    ^
     *              |    |    |
     *            prev  temp curr
     * ---------------------------------------
     * prev = prev.next;
     * dummy(-1)--->1--->3--->4--->2--->5--->2
     *                   ^    ^
     *                   |    |
     *                  temp curr
     *                  prev
     * ---------------------------------------
     * temp = curr.next;
     * curr.next = temp.next;
     *                        -----------
     *                        |         |
     *                        |         V
     * dummy(-1)--->1--->3--->4    2--->5--->2
     *                   ^    ^    ^
     *                   |    |    |
     *                 prev  curr temp
     *
     * temp.next = prev.next;
     * prev.next = temp;
     * prev = prev.next;
     * dummy(-1)--->1--->3--->2--->4--->5--->2
     *                        ^         ^
     *                        |         |
     *                       temp      curr
     *                       prev
     * ---------------------------------------
     * temp = curr.next;
     * curr.next = temp.next;
     *                                  ------> null
     *                                  |
     * dummy(-1)--->1--->3--->2--->4--->5    2
     *                             ^    ^    ^
     *                             |    |    |
     *                            prev curr  temp
     * temp.next = prev.next;
     * prev.next = temp;
     *                                  ------> null
     *                                  |
     * dummy(-1)--->1--->3--->2--->4    5<---2
     *                             ^    ^    ^
     *                             |    |    |
     *                            prev curr  temp
     *                             |         ^
     *                             |         |
     *                             -----------
     *
     * prev = prev.next:
     * dummy(-1)--->1--->3--->2--->4--->2--->5--->null
     *                                  ^    ^
     *                                  |    |
     *                                 temp curr
     *                                 prev
     * return dummy.next
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionV2(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr;
        /**
         * Find the first node that its value is greater than x:
         * prev.next >= x, while-loop-end.
         */
        while (prev.next != null && prev.next.val < x) {
            prev = prev.next;
        }
        curr = prev;
        /**
         * At first, curr.next points at the first node that its value is greater than or equals to x.
         * Then we move curr forward if curr.next is greater than or equals to x.
         * Otherwise, we pick off the curr.next, and append it after prev pointer.
         * At last, we move the prev pointer forward.
         */
        while (curr.next != null) {
            if (curr.next.val < x) {
                ListNode temp = curr.next;
                curr.next = temp.next;
                temp.next = prev.next;
                prev.next = temp;
                prev = prev.next;
            } else {
                curr = curr.next;
            }
        }
        return dummy.next;
    }

    /**
     * Understanding the following solution
     * Similar with partition, but using for-loop to replace while-loop
     * @param head
     * @param x
     * @return
     */
    public ListNode partitionV1(ListNode head, int x) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode smallerHead = new ListNode(0);
        ListNode biggerHead = new ListNode(0);
        ListNode smaller = smallerHead;
        ListNode bigger = biggerHead;
        for (; head != null; head = head.next) {
            if (head.val < x) {
                smaller.next = head;
                smaller = head;
            } else {
                bigger.next = head;
                bigger = head;
            }
        }
        bigger.next = null;
        smaller.next = biggerHead.next;
        return smallerHead.next;
    }

    /**
     * Understanding the following solution
     * The fundamental principles are to separate the list into 2 distinct lists
     * and link them afterwards.
     * ---------------------------------
     * # Definition for singly-linked list.
     * # class ListNode:
     * #     def __init__(self, val=0, next=None):
     * #         self.val = val
     * #         self.next = next
     * class Solution:
     *     def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
     *         smallerHead, biggerHead = ListNode(0), ListNode(0)
     *         smaller, bigger = smallerHead, biggerHead
     *
     *         while head:
     *             if head.val < x:
     *                 smaller.next = head
     *                 smaller = head
     *             else:
     *                 bigger.next = head
     *                 bigger = head
     *             head = head.next
     *
     *         bigger.next = None
     *         smaller.next = biggerHead.next
     *         return smallerHead.next
     * -----------------------------------------
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
        /**
         * cut off anything after bigger.
         */
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
