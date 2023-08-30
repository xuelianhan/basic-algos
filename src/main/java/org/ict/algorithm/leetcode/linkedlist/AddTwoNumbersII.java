package org.ict.algorithm.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 *
 * Example 2:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 *
 * Example 3:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 *
 * Follow up: Could you solve it without reversing the input lists?
 * @author sniper
 * @date 28 Aug 2023
 * LC445, Medium
 */
public class AddTwoNumbersII {

    /**
     * Understanding the following solution
     * Two Stacks + add at head Solution
     * Time Cost 2ms
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersV3(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode dummy = new ListNode(0);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            sum += (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop());
            dummy.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = dummy;
            dummy = head;
            sum /= 10;
        }
        return dummy.val == 0 ? dummy.next : dummy;
    }

    /**
     * Two Stacks + add at head Solution
     * Time Cost 3ms
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + carry;
            int remainder = sum % 10;
            carry = sum / 10;
            ListNode newNode = new ListNode(remainder);
            addAtHead(sumNode, newNode);
        }
        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            addAtHead(sumNode, newNode);
        }
        return dummy.next;
    }

    private void addAtHead(ListNode head, ListNode newNode) {
        ListNode next = head.next;
        newNode.next = next;
        head.next = newNode;
    }


    /**
     * Two Stacks + add at head Solution
     * Time Cost 3ms
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersV1(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = (stack1.isEmpty() ? 0 : stack1.pop()) + (stack2.isEmpty() ? 0 : stack2.pop()) + carry;
            int remainder = sum % 10;
            carry = sum / 10;
            ListNode newNode = new ListNode(remainder);
            ListNode next = sumNode.next;
            newNode.next = next;
            sumNode.next = newNode;
        }
        if (carry > 0) {
            ListNode next = sumNode.next;
            ListNode newNode = new ListNode(carry);
            newNode.next = next;
            sumNode.next = newNode;
        }
        return dummy.next;
    }


    /**
     * Reverse Link List Solution
     * Time Cost 2ms
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newL1 = reverseList(l1);
        ListNode newL2 = reverseList(l2);
        return addList(newL1, newL2);
    }

    private ListNode addList(ListNode newL1, ListNode newL2) {
        ListNode dummy = new ListNode(0);
        ListNode sumNode = dummy;
        int carry = 0;
        while (newL1 != null || newL2 != null) {
            int sum = (newL1 == null ? 0 : newL1.val) + (newL2 == null ? 0 : newL2.val) + carry;
            int remainder = sum % 10;
            carry = sum / 10;
            ListNode newNode = new ListNode(remainder);
            addAtHead(sumNode, newNode);

            if (newL1 != null) {
                newL1 = newL1.next;
            }
            if (newL2 != null) {
                newL2 = newL2.next;
            }
        }
        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            addAtHead(sumNode, newNode);
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
