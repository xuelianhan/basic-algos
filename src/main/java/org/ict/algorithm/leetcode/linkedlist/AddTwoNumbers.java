package org.ict.algorithm.leetcode.linkedlist;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order(lower digit before, higher digit after), and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example1:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807 --> reverse operation --> 708
 *
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Input: l1 = [2,4,9], l2 = [5,6,4,9]
 * Output: [7,0,4,0,1]
 * 942 + 9465 = 10407 --> reverse operation --> 70401
 *
 *
 * Constraints:
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * @author sniper
 * @date 17 Aug, 2022
 * LC2
 * TOP100
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int sum = 0;
        ListNode first = null;
        ListNode oldFirst = null;
        /**
         * Because numbers are reverse order,
         * so we add the number one by one
         * from lower to higher digit.
         * This is the order of iteration the list.
         * So we don't need using stack here.
         */
        while (l1 != null || l2 != null) {
            int n1 = (l1 == null ? 0 : l1.val);
            int n2 = (l2 == null ? 0 : l2.val);
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            sum = n1 + n2 + carry;
            carry = sum / 10;
            int mod = sum % 10;
            /**
             * Insert node at the head
             */
            oldFirst = first;
            first = new ListNode(mod);
            first.next = oldFirst;
        }

        if (carry == 1) {
            oldFirst = first;
            first = new ListNode(1);
            first.next = oldFirst;
        }

        return reverse(first);
    }

    public ListNode reverse(ListNode head) {
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
