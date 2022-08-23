package org.ict.algorithm.leetcode.linkedlist;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 *
 * Constraints:
 * The number of nodes in the list is in the range [1, 10^5].
 * 0 <= Node.val <= 9
 * Follow up: Could you do it in O(n) time and O(1) space?
 *
 * @author sniper
 * @date 2022/8/23
 * LC234
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        return false;
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
