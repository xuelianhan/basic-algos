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

    /**
     * Fast-Slow Pointer
     * Solution provided by yavinci
     *
     * @param head
     * @return
     */
    public boolean isPalindromeV2(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        /**
         * If odd numbers of nodes, let slow move one step forward.
         * e.g.
         * 1--->2--->3--->4--->5--->null
         *           s         f
         *
         * even numbers nodes,e.g.
         * 1--->2--->3--->4--->null
         *           s         f
         */
        if (fast != null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;
        /**
         * here should use slow pointer, not fast
         * because slow may end before fast(fast started at head)
         */
        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public boolean isPalindromeV1(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val);
            cur = cur.next;
        }
        return isPalindrome(sb.toString(), 0, sb.length() - 1);
    }

    public boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low) != s.charAt(high)) {
                return false;
            } else {
                low++;
                high--;
            }
        }
        return true;
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
