package org.ict.algorithm.leetcode.linkedlist;

/**
 *
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the k^th node from the beginning and the k^th node from the end (the list is 1-indexed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 10^5
 * 0 <= Node.val <= 100
 * @author sniper
 * @date 17 Sep, 2022
 * LC1721
 */
public class SwappingNodesInALinkedList {

    /**
     * Three Pointers solution.
     * Swap nodes values instead of changing nodes themselves.
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        ListNode prev = dummy;

        int step = 0;
        while (fast.next != null) {
            if (step < (k-1)) {
                prev = prev.next;
            }
            if (step >= k) {
                slow = slow.next;
            }
            fast = fast.next;
            step++;
        }

        /**
         * e.g.
         * Dummy->1->2->3->4->5->null, k=2
         * prev point at 1
         * slow point at 3
         * Due to no limit to swap values, so we can swap values directly.
         */
        int temp = prev.next.val;
        prev.next.val = slow.next.val;
        slow.next.val = temp;

        return dummy.next;
    }


    public ListNode swapNodesV2(ListNode head, int k) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        ListNode prev = dummy;

        int step = 0;
        while (fast.next != null) {
            if (step < (k-1)) {
                prev = prev.next;
            }
            if (step >= k) {
                slow = slow.next;
            }
            fast = fast.next;
            step++;
        }

        /**
         * Three cases need to be considered when we swap nodes directly.
         */
        ListNode t1 = prev.next;
        ListNode t1Next = prev.next.next;
        ListNode t2 = slow.next;
        ListNode t2Next = slow.next.next;

        if (prev.next == slow) {
            prev.next = t2;
            t2.next = t1;
            t1.next = t2Next;
        } else if (slow.next == prev) {
            slow.next = t1;
            t1.next = t2;
            t2.next = t1Next;
        } else {
            prev.next = t2;
            t2.next = t1Next;
            t1.next = t2Next;
            slow.next = t1;
        }

        return dummy.next;
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
