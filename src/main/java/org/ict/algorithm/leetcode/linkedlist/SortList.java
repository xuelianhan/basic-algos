package org.ict.algorithm.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 *
 *
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 5 * 10^4].
 * -10^5 <= Node.val <= 10^5
 *
 *
 * Follow up: Can you sort the linked list in O(N*logN) time and O(1) memory (i.e. constant space)?
 * @author sniper
 * @date 20 Sep, 2022
 * LC148
 */
public class SortList {


    /**
     * Top-Down-Merge-Sort.
     *
     * e.g.1
     * 1-->2-->3-->4-->5-->null
     * fast:1, slow:1, prev:null
     * fast:3, slow:2, prev:1
     * fast:5, slow:3, prev:2
     * while-loop-ended
     * slow stay at the middle of the list.
     *
     * e.g.2
     * 1-->2-->3-->4-->null
     * fast:1,    slow:1, prev:null
     * fast:3,    slow:2, prev:1
     * fast:null, slow:3, prev:2
     * while-loop-ended
     * slow stay at the middle of the list.
     *
     *
     * @param head
     * @return
     */
    public ListNode sortListV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        /**
         * find the middle node of the list.
         */
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        /**
         * cut first half off the list.
         */
        prev.next = null;

        ListNode l1 = sortListV2(head);
        ListNode l2 = sortListV2(slow);

        /**
         * Merge sort.
         */
        return mergeSortedList(l1, l2);
    }

    public ListNode mergeSortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }


    /**
     * Sort based values in LisNode and copy values back to ListNodes.
     *
     * Time Complexity O(2N) + O(N*logN).
     * Space Complexity O(N).
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        Collections.sort(list);

        cur = head;
        int i = 0;
        while (cur != null) {
            cur.val = list.get(i);
            i++;
            cur = cur.next;
        }

        return head;
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
