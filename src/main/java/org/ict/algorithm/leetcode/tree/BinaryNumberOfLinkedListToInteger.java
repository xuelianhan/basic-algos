package org.ict.algorithm.leetcode.tree;

/**
 * Given head which is a reference node to a singly-linked list.
 * The value of each node in the linked list is either 0 or 1.
 * The linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 *
 * Constraints:
 *
 * The Linked List is not empty.
 * Number of nodes will not exceed 30.
 * Each node's value is either 0 or 1.
 * @author sniper
 * @date 2021/12/7
 * LC1290
 */
public class BinaryNumberOfLinkedListToInteger {

    public int getDecimalValue(ListNode head) {
       return dfs(head, 0);
    }

    public int dfs(ListNode head, int val) {
        if (null == head) {
            return 0;
        }
        val = val * 2 + head.val;
        return head.next == null ? val : dfs(head.next, val);
    }
}
