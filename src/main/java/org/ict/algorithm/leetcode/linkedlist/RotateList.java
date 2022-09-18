package org.ict.algorithm.leetcode.linkedlist;

import java.util.Iterator;

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
        /**
         * count the length of this link list.
         */
        int len = 0;
        while (cur.next != null) {
            len++;
            cur = cur.next;
        }
        if (k % len == 0) {
            return head;
        }
        /**
         * cur arrived at the tail of this link list,
         * circle the link, this step is very important.
         */
        cur.next = head;
        int mod = k % len;
        int step = len - mod;

        /**
         * move cur forward with len - mod steps.
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

    /**
     * http://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/
     * @author hanxuelian
     *
     */
    public static class SingleLinkedListAddition {

        public Result addLinkedlistV1(SwapNodesInPairs.SingleLinkedList<Integer> l1, SwapNodesInPairs.SingleLinkedList<Integer> l2, int value) {
            Result r = new Result();
            r.data = value % 10;
            if (l1 != null && l2 != null) {
                Iterator<Integer> iter1 = l1.iterator();
                Iterator<Integer> iter2 = l2.iterator();
                while (iter1.hasNext() && iter2.hasNext()) {
                    r.carry = (iter1.next() + iter2.next()) > 10 ? 1 :0;
                }
            }
            return r;
        }

        class Result {
            private Integer data;

            private Integer carry;

            public Integer getData() {
                return data;
            }

            public void setData(Integer data) {
                this.data = data;
            }

            public Integer getCarry() {
                return carry;
            }

            public void setCarry(Integer carry) {
                this.carry = carry;
            }
        }

    }
}
