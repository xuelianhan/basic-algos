package org.ict.algorithm.leetcode.linkedlist;

/**
 * You are given two linked lists: list1 and list2 of sizes n and m respectively.
 *
 * Remove list1's nodes from the ath node to the bth node, and put list2 in their place.
 *
 * The blue edges and nodes in the following figure indicate the result:
 *
 *
 * Build the result list and return its head.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
 * Output: [0,1,2,1000000,1000001,1000002,5]
 * Explanation: We remove the nodes 3 and 4 and put the entire list2 in their place.
 * The blue edges and nodes in the above figure indicate the result.
 * Example 2:
 *
 *
 * Input: list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
 * Output: [0,1,1000000,1000001,1000002,1000003,1000004,6]
 * Explanation: The blue edges and nodes in the above figure indicate the result.
 *
 *
 * Constraints:
 *
 * 3 <= list1.length <= 10^4
 * 1 <= a <= b < list1.length - 1
 * 1 <= list2.length <= 10^4
 * @author sniper
 * @date 07 Sep, 2022
 * LC1669
 */
public class MergeInBetweenLinkedLists {

    public ListNode mergeInBetweenV2(ListNode list1, int a, int b, ListNode list2) {
        /**
         * Two pointer
         * start point at the previous node before a
         * end point at the previous node before b
         */
        ListNode start = null;
        ListNode end = list1;
        /**
         * Get the previous node index before a and b;
         */
        for (int i = 0; i < b; i++, end = end.next) {
            if (i == (a - 1)) {
                start = end;
            }
        }
        /**
         * Connect start point to the list2
         */
        start.next = list2;
        /**
         * Find the last node of list2
         */
        while (list2.next != null) {
            list2 = list2.next;
        }
        /**
         * Connect list2 to the end next pointer of list1
         */
        list2.next = end.next;
        /**
         * Cut off nodes after end point from list1
         */
        end.next = null;
        return list1;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode dummy = new ListNode(0);
        dummy.next = list1;
        ListNode fast = dummy;
        ListNode slow = dummy;
        int x = 0;
        int y = 0;
        while (fast != null && slow != null) {
            if (x <= (b + 1)) {
                x++;
                fast = fast.next;
            } else {
                break;
            }
            if (y <= a) {
                y++;
                slow = slow.next;
            }
        }
        slow.next = list2;
        ListNode cur = list2;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        cur.next = fast.next;
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
