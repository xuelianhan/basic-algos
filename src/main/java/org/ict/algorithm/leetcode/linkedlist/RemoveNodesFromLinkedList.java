package org.ict.algorithm.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given the head of a linked list.
 *
 * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
 *
 * Return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 * 5-->2-->13-->3-->8  ===> 13-->8
 *
 *
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * Example 2:
 *
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 *
 *
 * Constraints:
 *
 * The number of the nodes in the given list is in the range [1, 105].
 * 1 <= Node.val <= 10^5
 * @author sniper
 * @date 09 Aug 2023
 * LC2487, Medium
 */
public class RemoveNodesFromLinkedList {

    /**
     * Recursion Solution
     * @param head
     * @return
     */
    public ListNode removeNodesV2(ListNode head) {
        return null;
    }


    /**
     * Monotonic Stack Solution
     * Time Cost 23ms
     * @param head
     * @return
     */
    public ListNode removeNodesV1(ListNode head) {
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        Deque<ListNode> stack = new ArrayDeque<>();
        stack.push(dummy);

        while (head != null) {
            while (!stack.isEmpty() && head.val > stack.peek().val) {
                stack.pop();
            }
            if (stack.peek() != null) {
                stack.peek().next = head;
            }
            stack.push(head);
            head = head.next;
        }
        return dummy.next;
    }


    /**
     * Time Cost 6ms
     * Reverse List Solution
     *
     * We reverse the list,
     * and "eat" next elements if value is smaller.
     * Then, we reverse the list again.
     * @author votrubac
     * @see <a href="https://leetcode.com/problems/remove-nodes-from-linked-list/solutions/2851962/reverse-list-vs-stack/"></a>
     * @param head
     * @return
     */
    public ListNode removeNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head = reverse(head);
        for (ListNode cur = head; cur != null && cur.next != null;) {
            if (cur.val > cur.next.val) {
                // Eat the next value
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return reverse(head);
    }

    public ListNode reverse(ListNode cur) {
        ListNode newHead = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = next;
        }
        return newHead;
    }

    static class ListNode {
        int val;
        ListNode next;
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
